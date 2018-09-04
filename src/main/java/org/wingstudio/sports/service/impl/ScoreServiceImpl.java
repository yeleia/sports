package org.wingstudio.sports.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingstudio.sports.VO.PreSoloVO;
import org.wingstudio.sports.VO.SoloVO;
import org.wingstudio.sports.VO.TeamVO;
import org.wingstudio.sports.dao.*;
import org.wingstudio.sports.domain.*;
import org.wingstudio.sports.service.ScoreService;
import org.wingstudio.sports.util.CampareUtil;
import org.wingstudio.sports.util.CheckUtil;
import org.wingstudio.sports.util.CountUtil;
import org.wingstudio.sports.util.ReturnUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("scoreService")
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private PreSoloMapper preSoloMapper;
    @Autowired
    private SportMapper sportMapper;
    @Autowired
    private SoloMapper soloMapper;
    @Autowired
    private TeamScoreMapper teamScoreMapper;
    @Autowired
    private ContestantMapper contestantMapper;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private PreRoleMapper preRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private TwoLevelMapper twoLevelMapper;
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public Map<String, Object> addPreSoloScore(PreSolo preSolo) {
        Sport sport = sportMapper.selectByPrimaryKey(preSolo.getSportid());
        //检查输入
        if (CheckUtil.checkScore(sport.getSortrule(), preSolo.getScore(), sport.getInmax(), sport.getInmin())) {
            //检查该成绩是否已添加
            if (preSoloMapper.isPreSoloExsit(preSolo.getSportid(), preSolo.getContestantid(), preSolo.getTaketime()) < 1) {
                preSolo.setChecked(0);
                preSoloMapper.insertSelective(preSolo);
                return ReturnUtil.ret(true, "添加成功,待审核");
            } else {
                return ReturnUtil.ret(false, "添加失败，该成绩已经添加");
            }
        } else {
            return ReturnUtil.ret(false, "添加失败，检查输入是否合格");
        }
    }

    @Override
    public Map<String, Object> updatePreSoloScore(PreSolo preSolo) {
        Sport sport = sportMapper.selectByPrimaryKey(preSolo.getSportid());
        PreSolo preSolo1 = preSoloMapper.selectByPrimaryKey(preSolo.getId());
        //检查输入格式
        if (CheckUtil.checkScore(sport.getSortrule(), preSolo.getScore(), sport.getInmax(), sport.getInmin())) {
            //检查是否只改变除sportid,Contestantid,taketime
            if (preSolo.getSportid().equals(preSolo1.getSportid()) && preSolo.getContestantid().equals(preSolo1.getContestantid()) && preSolo.getTaketime().equals(preSolo1.getTaketime())) {
                //直接修改
                preSoloMapper.updateByPrimaryKeySelective(preSolo);
                if (preSolo.getChecked()==1){
                    TwoLevel twoLevel0=twoLevelMapper.getBySportIdConsId(preSolo.getSportid(),preSolo.getContestantid(),preSolo.getTaketime());
                    //是否达到二级运动员标准
                    TwoLevel twoLevel=twoLevel(preSolo,sport);
                    if (twoLevel!=null&&twoLevel0==null){
                        twoLevelMapper.insertSelective(twoLevel);
                    }else if (twoLevel==null&& twoLevel0!=null){
                        twoLevelMapper.deleteByPrimaryKey(twoLevel0.getId());
                    }
                    //是否创新记录
                    Record record0=recordMapper.getBySportIdConsId(preSolo.getSportid(),preSolo.getContestantid(),preSolo.getTaketime());
                    Record record=records(preSolo,sport);
                    if (record!=null&& record0==null){
                        recordMapper.insertSelective(record);
                    }else if (record==null&& record0!=null){
                        recordMapper.deleteByPrimaryKey(record0.getId());
                    }
                    //该成绩已经审核，故需要重新计算成绩
                    String campus=contestantMapper.selectByPrimaryKey(preSolo.getContestantid()).getCampus();
                    List<Score> scores= count(preSolo.getSportid(),preSolo.getTaketime(),campus,sport.getSortrule());
                    for (int i = 0; i <scores.size() ; i++) {
                        scoreMapper.updateByPrimaryKeySelective(scores.get(i));
                    }
                }
                return ReturnUtil.ret(true, "'修改成功");
            } else {
                //该记录是否存在
                if (preSoloMapper.isPreSoloExsit(preSolo.getSportid(), preSolo.getContestantid(), preSolo.getTaketime()) < 1) {
                    //该记录不存在
                    preSoloMapper.updateByPrimaryKeySelective(preSolo);
                    if (preSolo.getChecked()==1){
                        TwoLevel twoLevel0=twoLevelMapper.getBySportIdConsId(preSolo.getSportid(),preSolo.getContestantid(),preSolo.getTaketime());
                        //是否达到二级运动员标准
                        TwoLevel twoLevel=twoLevel(preSolo,sport);
                        if (twoLevel!=null&&twoLevel0==null){
                            twoLevelMapper.insertSelective(twoLevel);
                        }else if (twoLevel==null&& twoLevel0!=null){
                            twoLevelMapper.deleteByPrimaryKey(twoLevel0.getId());
                        }
                        //是否创新记录
                        Record record0=recordMapper.getBySportIdConsId(preSolo.getSportid(),preSolo.getContestantid(),preSolo.getTaketime());
                        Record record=records(preSolo,sport);
                        if (record!=null&& record0==null){
                            recordMapper.insertSelective(record);
                        }else if (record==null&& record0!=null){
                            recordMapper.deleteByPrimaryKey(record0.getId());
                        }
                        //该成绩已经审核，故需要重新计算
                        String campus=contestantMapper.selectByPrimaryKey(preSolo.getContestantid()).getCampus();
                        List<Score> scores= count(preSolo.getSportid(),preSolo.getTaketime(),campus,sport.getSortrule());
                        for (int i = 0; i <scores.size() ; i++) {
                            scoreMapper.updateByPrimaryKeySelective(scores.get(i));
                        }
                    }
                    return ReturnUtil.ret(true, "修改成功");
                } else {
                    return ReturnUtil.ret(false, "修改失败，该记录已经存在");
                }
            }
        } else {
            return ReturnUtil.ret(false, "请检查输入格式");
        }
    }

    @Override
    public Map<String, Object> deletePreSoloScore(Integer id, Integer check) {
            PreSolo preSolo=preSoloMapper.selectByPrimaryKey(id);
            Sport sport=sportMapper.selectByPrimaryKey(preSolo.getSportid());
            if (preSoloMapper.deleteByPrimaryKey(id) > 0) {
                //同时删除记录和二级达标
                if (check==1){
                    Record record=recordMapper.getBySportIdConsId(preSolo.getSportid(),preSolo.getContestantid(),preSolo.getTaketime());
                    if (record!=null){
                        recordMapper.deleteByPrimaryKey(record.getId());
                    }
                    TwoLevel twoLevel=twoLevelMapper.getBySportIdConsId(preSolo.getSportid(),preSolo.getContestantid(),preSolo.getTaketime());
                    if (twoLevel!=null){
                        twoLevelMapper.deleteByPrimaryKey(twoLevel.getId());
                    }
                    //该成绩已经审核，故需要重新计算
                    String campus=contestantMapper.selectByPrimaryKey(preSolo.getContestantid()).getCampus();
                    List<Score> scores= count(preSolo.getSportid(),preSolo.getTaketime(),campus,sport.getSortrule());
                    for (int i = 0; i <scores.size() ; i++) {
                        scoreMapper.updateByPrimaryKeySelective(scores.get(i));
                    }                }
                return ReturnUtil.ret(true,"删除成功");
            } else {
               return ReturnUtil.ret(false,"删除失败");
            }

    }

    @Override
    public Map<String,Object> getPreSoloNoCheck(Integer tempPage, Integer pageCapacity, String taketime) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        List<PreSolo> preSolo=preSoloMapper.getPreSoloNoCheck(tempPage*pageCapacity,pageCapacity,taketime);
        resultMap.put("preSoloVO",getPreSoloVO(preSolo));
        resultMap.put("count",preSoloMapper.getPreSoloNoCheckNu(taketime));
        return resultMap;
    }

    @Override
    public Map<String, Object> getPreSoloChecked(Integer tempPage, Integer pageCapacity, String taketime) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        List<PreSolo> preSolo=preSoloMapper.getPreSoloChecked(tempPage*pageCapacity,pageCapacity,taketime);
        resultMap.put("preSoloVO",getPreSoloVO(preSolo));
        resultMap.put("count",preSoloMapper.getPreSoloCheckNu(taketime));
        return resultMap;
    }

    @Override
    public Map<String, Object> addSoloScore(Solo solo) {
        Sport sport=sportMapper.selectByPrimaryKey(solo.getSportid());
        //检查输入
        if(CheckUtil.checkScore(sport.getSortrule(),solo.getScore(),sport.getInmax(),sport.getInmin())){
            //检查该成绩是否添加
            if(soloMapper.isSoloExsit(solo.getContestantid(),solo.getSportid(),solo.getTaketime())<1){
                solo.setChecked(0);
                soloMapper.insertSelective(solo);
                return ReturnUtil.ret(true,"添加成功");
            }else {
                return ReturnUtil.ret(false,"该成绩已经添加");
            }
        }
        return ReturnUtil.ret(false,"添加失败，请检查输入是否符合要求");
    }

    @Override
    public Map<String, Object> updateSoloScore(Solo solo) {
        Sport sport=sportMapper.selectByPrimaryKey(solo.getSportid());
        Solo solo1=soloMapper.selectByPrimaryKey(solo.getId());
        //检查输入格式
        if (CheckUtil.checkScore(sport.getSortrule(),solo.getScore(),sport.getInmax(),sport.getInmin())){
            //检查是否只改变除sportid,Contestantid,taketime
            if (solo.getSportid().equals(solo1.getSportid())&&solo.getContestantid().equals(solo1.getContestantid())&&solo.getTaketime().equals(solo1.getTaketime())){
                //直接修改
                soloMapper.updateByPrimaryKeySelective(solo);
                if (solo.getChecked()==1){
                    TwoLevel twoLevel0=twoLevelMapper.getBySportIdConsIds(solo.getSportid(),solo.getContestantid(),solo.getTaketime());
                    //是否达到二级运动员标准
                    TwoLevel twoLevel=twoLevel(solo,sport);
                    if (twoLevel!=null&&twoLevel0==null){
                        twoLevelMapper.insertSelective(twoLevel);
                    }else if (twoLevel==null&& twoLevel0!=null){
                        twoLevelMapper.deleteByPrimaryKey(twoLevel0.getId());
                    }
                    //是否创新记录
                    Record record0=recordMapper.getBySportIdConsIds(solo.getSportid(),solo.getContestantid(),solo.getTaketime());
                    Record record=records(solo,sport);
                    if (record!=null&& record0==null){
                        recordMapper.insertSelective(record);
                    }else if (record==null&& record0!=null){
                        recordMapper.deleteByPrimaryKey(record0.getId());
                    }
                    //重新计算
                    String campus=contestantMapper.selectByPrimaryKey(solo.getContestantid()).getCampus();
                    List<Score> scores=countSolo(solo.getSportid(),solo.getTaketime(),campus,sport.getSortrule());
                    for (int i = 0; i <scores.size() ; i++) {
                        scoreMapper.updateByPrimaryKeySelective(scores.get(i));
                    }
                }
                return ReturnUtil.ret(true,"修改成功");
            }else {
                //该记录是否存在
                if (soloMapper.isSoloExsit(solo.getContestantid(),solo.getSportid(),solo.getTaketime())<1){
                    soloMapper.updateByPrimaryKeySelective(solo);
                    if (solo.getChecked()==1){
                        TwoLevel twoLevel0=twoLevelMapper.getBySportIdConsIds(solo.getSportid(),solo.getContestantid(),solo.getTaketime());
                        //是否达到二级运动员标准
                        TwoLevel twoLevel=twoLevel(solo,sport);
                        if (twoLevel!=null&&twoLevel0==null){
                            twoLevelMapper.insertSelective(twoLevel);
                        }else if (twoLevel==null&& twoLevel0!=null){
                            twoLevelMapper.deleteByPrimaryKey(twoLevel0.getId());
                        }
                        //是否创新记录
                        Record record0=recordMapper.getBySportIdConsIds(solo.getSportid(),solo.getContestantid(),solo.getTaketime());
                        Record record=records(solo,sport);
                        if (record!=null&& record0==null){
                            recordMapper.insertSelective(record);
                        }else if (record==null&& record0!=null){
                            recordMapper.deleteByPrimaryKey(record0.getId());
                        }
                        //重新计算
                        String campus=contestantMapper.selectByPrimaryKey(solo.getContestantid()).getCampus();
                        List<Score> scores=countSolo(solo.getSportid(),solo.getTaketime(),campus,sport.getSortrule());
                        for (int i = 0; i <scores.size() ; i++) {
                            scoreMapper.updateByPrimaryKeySelective(scores.get(i));
                        }

                    }
                    return ReturnUtil.ret(true,"修改成功");
                }else {
                    return ReturnUtil.ret(false,"该记录已经存在");
                }
            }
        }else {
            return ReturnUtil.ret(false,"修改失败，输入不符合规则");
        }
    }

    @Override
    public Map<String, Object> deleteSoloScore(Integer id, Integer check) {
        if (soloMapper.deleteByPrimaryKey(id)>0){
            if (check==1){
                Solo solo=soloMapper.selectByPrimaryKey(id);
                Sport sport=sportMapper.selectByPrimaryKey(solo.getSportid());
                Record record=recordMapper.getBySportIdConsId(solo.getSportid(),solo.getContestantid(),solo.getTaketime());
                if (record!=null){
                    recordMapper.deleteByPrimaryKey(record.getId());
                }
                TwoLevel twoLevel=twoLevelMapper.getBySportIdConsId(solo.getSportid(),solo.getContestantid(),solo.getTaketime());
                if (twoLevel!=null){
                    twoLevelMapper.deleteByPrimaryKey(twoLevel.getId());
                }
                String campus=contestantMapper.selectByPrimaryKey(solo.getContestantid()).getCampus();
                List<Score> scores=countSolo(solo.getSportid(),solo.getTaketime(),campus,sport.getSortrule());
                for (int i = 0; i <scores.size() ; i++) {
                    scoreMapper.updateByPrimaryKeySelective(scores.get(i));
                }
            }
            return ReturnUtil.ret(true,"删除成功");
        }else {
            return ReturnUtil.ret(false,"删除失败");
        }
    }

    @Override
    public Map<String, Object> getSoloScoreNoCheck(Integer tempPage, Integer pageCapacity, String taketime) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        List<Solo> solo=soloMapper.getSoloNoCheck(tempPage*pageCapacity,pageCapacity,taketime);
        resultMap.put("soloVO",getSoloVO(solo));
        resultMap.put("count",soloMapper.getPreSoloNoCheckNu(taketime));
        return resultMap;
    }



    @Override
    public Map<String, Object> getSoloScoreCheck(Integer tempPage, Integer pageCapacity, String taketime) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        List<Solo> solo=soloMapper.getSoloChecked(tempPage*pageCapacity,pageCapacity,taketime);
        resultMap.put("soloVO",getSoloVO(solo));
        resultMap.put("count",soloMapper.getPreSoloCheckNu(taketime));
        return resultMap;
    }

    @Override
    public Map<String, Object> addTeamScore(TeamScore teamScore) {
        Sport sport=sportMapper.selectByPrimaryKey(teamScore.getSportid());
        //检查输入
        if (CheckUtil.checkScore(sport.getSortrule(),teamScore.getScore(),sport.getInmax(),sport.getInmin())){
            //该记录是否存在
            if (teamScoreMapper.isTeamScoreExsit(teamScore.getSportid(),teamScore.getTeamid(),teamScore.getTaketime())<1){
                teamScore.setChecked(0);
                teamScoreMapper.insert(teamScore);
                return ReturnUtil.ret(true,"添加成功");
            }else {
                return ReturnUtil.ret(false,"该记录已经存在");
            }
        }else {
            return ReturnUtil.ret(false,"添加失败，请检查输入是否符合规则");
        }
    }

    @Override
    public Map<String, Object> updateTeamScore(TeamScore teamScore) {
        Sport sport=sportMapper.selectByPrimaryKey(teamScore.getSportid());
        TeamScore teamScore1=teamScoreMapper.selectByPrimaryKey(teamScore.getId());
        String campus=teamMapper.selectByPrimaryKey(teamScore.getTeamid()).getCampus();
        if (CheckUtil.checkScore(sport.getSortrule(),teamScore.getScore(),sport.getInmax(),sport.getInmin())) {
            if (teamScore.getTaketime().equals(teamScore1.getTaketime()) && teamScore.getTeamid().equals(teamScore1.getTeamid()) && teamScore.getSportid().equals(teamScore1.getSportid())) {
                teamScoreMapper.updateByPrimaryKeySelective(teamScore);
                if (teamScore.getChecked() == 1) {
                    //重新计算
                    List<TeamScore> teamScores=countTeam(teamScore.getSportid(),teamScore.getTaketime(),campus,sport.getSortrule());
                    for (int i=0;i<teamScores.size();i++){
                        teamScoreMapper.updateByPrimaryKeySelective(teamScores.get(i));
                    }
                }
                return ReturnUtil.ret(true,"修改成功");
            }else {
                //检查是否存在
                if (teamScoreMapper.isTeamScoreExsit(teamScore.getSportid(),teamScore.getTeamid(),teamScore.getTaketime())<1){
                    teamScoreMapper.updateByPrimaryKeySelective(teamScore);
                    if (teamScore.getChecked()==1){
                        //重新计算
                        List<TeamScore> teamScores=countTeam(teamScore.getSportid(),teamScore.getTaketime(),campus,sport.getSortrule());
                        for (int i=0;i<teamScores.size();i++){
                            teamScoreMapper.updateByPrimaryKeySelective(teamScores.get(i));
                        }

                    }
                    return ReturnUtil.ret(true,"修改成功");
                }else {
                    return ReturnUtil.ret(false,"删除失败,该记录已经存在");
                }
            }
        }else {
            return ReturnUtil.ret(false,"修改失败，请检查输入");
        }
    }

    @Override
    public Map<String, Object> deleteTeamScore(Integer id, Integer check) {
        TeamScore teamScore=teamScoreMapper.selectByPrimaryKey(id);
        Sport sport=sportMapper.selectByPrimaryKey(teamScore.getSportid());
        String campus=teamMapper.selectByPrimaryKey(teamScore.getTeamid()).getCampus();
        if (teamScoreMapper.deleteByPrimaryKey(id)>0){
            if (check==1){
                List<TeamScore> teamScores=countTeam(teamScore.getSportid(),teamScore.getTaketime(),campus,sport.getSortrule());
                for (int i=0;i<teamScores.size();i++){
                    teamScoreMapper.updateByPrimaryKeySelective(teamScores.get(i));
                }
            }
            return ReturnUtil.ret(true,"删除成功");
        }else {
            return ReturnUtil.ret(false,"删除失败");
        }
    }

    @Override
    public Map<String, Object> getTeamScoreNoCheck(Integer tempPage, Integer pageCapacity, String taketime) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        List<TeamScore> teamScores=teamScoreMapper.getTeamScoreNoCheck(tempPage*pageCapacity,pageCapacity,taketime);
        resultMap.put("soloVO",getTeamVO(teamScores));
        resultMap.put("count",teamScoreMapper.getTeamScoreNoCheckNu(taketime));
        return resultMap;
    }



    @Override
    public Map<String, Object> getTeamScoreCheck(Integer tempPage, Integer pageCapacity, String taketime) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        List<TeamScore> teamScores=teamScoreMapper.getTeamScoreChecked(tempPage*pageCapacity,pageCapacity,taketime);
        resultMap.put("soloVO",getTeamVO(teamScores));
        resultMap.put("count",teamScoreMapper.getTeamScoreCheckNu(taketime));
        return resultMap;
    }

    @Override
    public Map<String, Object> checkPreSolo(Integer id) {
        //首先更新状态
        preSoloMapper.updateCheck(id);
        PreSolo preSolo=preSoloMapper.selectByPrimaryKey(id);
        Sport sport=sportMapper.selectByPrimaryKey(preSolo.getSportid());
        String campus=contestantMapper.selectByPrimaryKey(preSolo.getContestantid()).getCampus();
        int sortrule=sport.getSortrule();
        //是否到二级，创记录
        TwoLevel twoLevel=twoLevel(preSolo,sport);
        if (twoLevel!=null){
            twoLevelMapper.insertSelective(twoLevel);
        }
        Record record=records(preSolo,sport);
        if (record!=null){
            recordMapper.insertSelective(record);
        }
        //计算，查询已经审核的
        List<Score> scores=count(preSolo.getSportid(),preSolo.getTaketime(),campus,sortrule);
        for (int i = 0; i <scores.size() ; i++) {
            scoreMapper.updateByPrimaryKeySelective(scores.get(i));
        }
        return ReturnUtil.ret(true,"审核成功");
    }

    @Override
    public Map<String, Object> checkSolo(Integer id) {
        //首先先更新状态
        soloMapper.updateCheck(id);
        Solo solo=soloMapper.selectByPrimaryKey(id);
        String campus=contestantMapper.selectByPrimaryKey(solo.getContestantid()).getCampus();
        int sortRole=sportMapper.selectByPrimaryKey(solo.getSportid()).getSortrule();
        //计算，查询已经审核过的
        List<Score> scores=countSolo(solo.getSportid(),solo.getTaketime(),campus,sortRole);
        for (int i = 0; i <scores.size() ; i++) {
            scoreMapper.updateByPrimaryKeySelective(scores.get(i));
        }
        return ReturnUtil.ret(true,"审核成功");
    }

    @Override
    public Map<String, Object> checkTeamScore(Integer id) {
        teamScoreMapper.updateCheck(id);
        TeamScore teamScore=teamScoreMapper.selectByPrimaryKey(id);
        String campus=teamMapper.selectByPrimaryKey(teamScore.getTeamid()).getCampus();
        int sortRole=sportMapper.selectByPrimaryKey(teamScore.getSportid()).getSortrule();
        //计算，查询已经审核过的
        List<TeamScore> scores=countTeam(teamScore.getSportid(),teamScore.getTaketime(),campus,sortRole);
        for (int i = 0; i <scores.size() ; i++) {
            teamScoreMapper.updateByPrimaryKeySelective(scores.get(i));
        }
        return ReturnUtil.ret(true,"审核成功");
    }


    /**
     * 计算预赛
     * @param sportid
     * @param taketime
     */
    private List<Score> count(Integer sportid, String taketime,String campus,int sortrole) {
        List<PreSolo> preSolos=new ArrayList<>();
        if (sortrole==1) {
             preSolos = preSoloMapper.getPreSoloBySportId(sportid, taketime);
        }else {
             preSolos = preSoloMapper.getPreSoloBySportIdAsc(sportid, taketime);
        }
        List<PreRole> preRoles=preRoleMapper.getPreRoleBySportId(sportid,campus);
        List<Score> scores=CountUtil.count(preSolos,preRoles);
       return scores;
    }
    /**
     * 计算决赛
     * @param sportid
     * @param taketime
     */
    private List<Score> countSolo(Integer sportid, String taketime,String campus,int sortrole) {
        List<Solo> solos=new ArrayList<>();
        if (sortrole==1) {
            solos = soloMapper.getSoloBySportId(sportid, taketime);
        }else {
            solos = soloMapper.getSoloBySportIdAsc(sportid, taketime);
        }
        List<Role> roles=roleMapper.getRoleBySportId(sportid,campus);
        List<Score> scores=CountUtil.countSolo(solos,roles);
        return scores;
    }
    /**
     * 计算集体项目
     * @param sportid
     * @param taketime
     */
    private List<TeamScore> countTeam(Integer sportid, String taketime,String campus,int sortrole) {
        List<TeamScore> teams=new ArrayList<>();
        if (sortrole==1) {
            teams = teamScoreMapper.getTeamBySportId(sportid, taketime);
        }else {
            teams = teamScoreMapper.getTeamBySportIdAsc(sportid, taketime);
        }
        List<PreRole> roles=preRoleMapper.getPreRoleBySportId(sportid,campus);
        List<TeamScore> scores=CountUtil.countTeam(teams,roles);
        return scores;
    }

    public  List<PreSoloVO> getPreSoloVO(List<PreSolo> preSolo){
        List<PreSoloVO> preSoloVOS=new ArrayList<>();
        for (int i=0;i<preSolo.size();i++){
            PreSoloVO preSoloVO=new PreSoloVO();
            Contestant contestant=contestantMapper.selectByPrimaryKey(preSolo.get(i).getContestantid());
            preSoloVO.setContestantid(contestant.getId());
            preSoloVO.setSportname(contestant.getSportname());
            preSoloVO.setStuname(contestant.getStuname());
            preSoloVO.setStunumber(contestant.getStunumber());
            preSoloVO.setId(preSolo.get(i).getId());
            preSoloVO.setGate(preSolo.get(i).getGate());
            preSoloVO.setGroup(preSolo.get(i).getGroup());
            preSoloVO.setRemark(preSolo.get(i).getRemark());
            preSoloVO.setScore(preSolo.get(i).getScore());
            preSoloVOS.add(preSoloVO);
        }
        return preSoloVOS;
    }
    public  List<SoloVO> getSoloVO(List<Solo> solo) {
        List<SoloVO> soloVOS=new ArrayList<>();
        for (int i = 0; i <solo.size() ; i++) {
            SoloVO soloVO=new SoloVO();
            Contestant contestant=contestantMapper.selectByPrimaryKey(solo.get(i).getContestantid());
            soloVO.setContestantid(contestant.getId());
            soloVO.setSportname(contestant.getSportname());
            soloVO.setStuname(contestant.getStuname());
            soloVO.setStunumber(contestant.getStunumber());
            soloVO.setId(solo.get(i).getId());
            soloVO.setGate(solo.get(i).getGate());
            soloVO.setRemark(solo.get(i).getRemark());
            soloVO.setScore(solo.get(i).getScore());
            soloVOS.add(soloVO);
        }
        return soloVOS;
    }
    private Object getTeamVO(List<TeamScore> teamScores) {
        List<TeamVO> teamVOS=new ArrayList<>();
        for (int i=0;i<teamScores.size();i++){
            TeamVO teamVO=new TeamVO();
            Team team=teamMapper.selectByPrimaryKey(teamScores.get(i).getTeamid());
            teamVO.setClasses(team.getClasses());
            teamVO.setProfession(team.getProfession());
            teamVO.setId(teamScores.get(i).getId());
            teamVO.setSportname(team.getSportname());
            teamVO.setScore(teamScores.get(i).getScore());
            teamVOS.add(teamVO);
        }
        return teamVOS;
    }

    /***
     * 预赛是否达二级运动员标准
     * @param preSolo
     */
    public static TwoLevel twoLevel(PreSolo preSolo,Sport sport){
            if (CampareUtil.eqTwoLevel(preSolo.getScore(),sport.getTwolevel(),sport.getSortrule())){
                TwoLevel twoLevel=new TwoLevel();
                twoLevel.setContestantid(preSolo.getContestantid());
                twoLevel.setMark(0);
                twoLevel.setSportid(sport.getId());
                twoLevel.setTaketime(preSolo.getTaketime());
                return twoLevel;
            }else {
                return null;
            }
        }
    public static TwoLevel twoLevel(Solo solo,Sport sport){
        if (CampareUtil.eqTwoLevel(solo.getScore(),sport.getTwolevel(),sport.getSortrule())){
            TwoLevel twoLevel=new TwoLevel();
            twoLevel.setContestantid(solo.getContestantid());
            twoLevel.setMark(1);
            twoLevel.setSportid(sport.getId());
            twoLevel.setTaketime(solo.getTaketime());
            return twoLevel;
        }else {
            return null;
        }
    }

    /**
     * 预赛是否破纪录
     * @param preSolo
     * @param sport
     * @return
     */
    public static Record records(PreSolo preSolo,Sport sport){
        if (CampareUtil.eqRecord(preSolo.getScore(),sport.getRecord(),sport.getSortrule())){
            Record record=new Record();
            record.setContestantid(preSolo.getContestantid());
            record.setMark(0);
            record.setSportid(preSolo.getSportid());
            record.setTaketime(preSolo.getTaketime());
            return record;
        }
        return null;
    }
    public static Record records(Solo solo,Sport sport){
        if (CampareUtil.eqRecord(solo.getScore(),sport.getRecord(),sport.getSortrule())){
            Record record=new Record();
            record.setContestantid(solo.getContestantid());
            record.setMark(1);
            record.setSportid(solo.getSportid());
            record.setTaketime(solo.getTaketime());
            return record;
        }
        return null;
    }

}
