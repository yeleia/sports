package org.wingstudio.sports.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingstudio.sports.VO.PreSoloVO;
import org.wingstudio.sports.VO.SoloVO;
import org.wingstudio.sports.VO.TeamVO;
import org.wingstudio.sports.constant.Common;
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


    @Override
    public Map<String, Object> addPreSoloScore(PreSolo preSolo) {
        Sport sport = sportMapper.selectByPrimaryKey(preSolo.getSportid());
        //检查输入
        if (CheckUtil.checkScore(sport.getSortrule(), preSolo.getScore(), sport.getInmax(), sport.getInmin())) {
            //检查该成绩是否已添加
            if (preSoloMapper.isPreSoloExsit(preSolo.getSportid(), preSolo.getContestantid(), preSolo.getTaketime()) < 1) {
                //新添加的设置为未审核
                preSolo.setChecked(Common.NOCHECK);
                preSoloMapper.insertSelective(preSolo);
                return ReturnUtil.ret(true, "添加成功,待审核");
            } else {
                return ReturnUtil.ret(false, "添加失败，该成绩已经添加");
            }
        } else {
            return ReturnUtil.ret(false, "添加失败，检查输入是否合格，注意成绩的合理性，是否在规定范围内");
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
                return ReturnUtil.ret(true, "'修改成功");
            } else {
                //该记录是否存在
                if (preSoloMapper.isPreSoloExsit(preSolo.getSportid(), preSolo.getContestantid(), preSolo.getTaketime()) < 1) {
                    //该记录不存在
                    preSoloMapper.updateByPrimaryKeySelective(preSolo);
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
    public Map<String, Object> deletePreSoloScore(Integer id) {

            if (preSoloMapper.deleteByPrimaryKey(id) > 0) {
                return ReturnUtil.ret(true,"删除成功");
            } else {
               return ReturnUtil.ret(false,"删除失败");
            }

    }

    @Override
    public Map<String,Object> getPreSoloNoCheck(Integer tempPage, Integer pageCapacity, String taketime) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        int checked=Common.NOCHECK;
        List<PreSolo> preSolo=preSoloMapper.getPreSoloByCheck(tempPage*pageCapacity,pageCapacity,checked,taketime);
        resultMap.put("preSoloVO",getPreSoloVO(preSolo));
        resultMap.put("count",preSoloMapper.getPreSoloByCheckNu(checked,taketime));
        return resultMap;
    }

    @Override
    public Map<String, Object> getPreSoloChecked(Integer tempPage, Integer pageCapacity, String taketime) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        int checked=Common.CHECKED;
        List<PreSolo> preSolo=preSoloMapper.getPreSoloByCheck(tempPage*pageCapacity,pageCapacity,checked,taketime);
        resultMap.put("preSoloVO",getPreSoloVO(preSolo));
        resultMap.put("count",preSoloMapper.getPreSoloByCheckNu(checked,taketime));
        return resultMap;
    }

    @Override
    public Map<String, Object> addSoloScore(Solo solo) {
        Sport sport=sportMapper.selectByPrimaryKey(solo.getSportid());
        //检查输入
        if(CheckUtil.checkScore(sport.getSortrule(),solo.getScore(),sport.getInmax(),sport.getInmin())){
            //检查该成绩是否添加
            if(soloMapper.isSoloExsit(solo.getContestantid(),solo.getSportid(),solo.getTaketime())<1){
                solo.setChecked(Common.NOCHECK);
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
                return ReturnUtil.ret(true,"修改成功");
            }else {
                //该记录是否存在
                if (soloMapper.isSoloExsit(solo.getContestantid(),solo.getSportid(),solo.getTaketime())<1){
                    soloMapper.updateByPrimaryKeySelective(solo);
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
    public Map<String, Object> deleteSoloScore(Integer id) {
        if (soloMapper.deleteByPrimaryKey(id)>0){
            return ReturnUtil.ret(true,"删除成功");
        }else {
            return ReturnUtil.ret(false,"删除失败");
        }
    }

    @Override
    public Map<String, Object> getSoloScoreNoCheck(Integer tempPage, Integer pageCapacity, String taketime) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        Integer checked=Common.NOCHECK;
        List<Solo> solo=soloMapper.getSoloByCheck(tempPage*pageCapacity,pageCapacity,checked,taketime);
        resultMap.put("soloVO",getSoloVO(solo));
        resultMap.put("count",soloMapper.getPreSoloByCheckNu(checked,taketime));
        return resultMap;
    }



    @Override
    public Map<String, Object> getSoloScoreCheck(Integer tempPage, Integer pageCapacity, String taketime) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        Integer checked=Common.CHECKED;
        List<Solo> solo=soloMapper.getSoloByCheck(tempPage*pageCapacity,pageCapacity,checked,taketime);
        resultMap.put("soloVO",getSoloVO(solo));
        resultMap.put("count",soloMapper.getPreSoloByCheckNu(checked,taketime));
        return resultMap;
    }

    @Override
    public Map<String, Object> addTeamScore(TeamScore teamScore) {
        Sport sport=sportMapper.selectByPrimaryKey(teamScore.getSportid());
        //检查输入
        if (CheckUtil.checkScore(sport.getSortrule(),teamScore.getScore(),sport.getInmax(),sport.getInmin())){
            //该记录是否存在
            if (teamScoreMapper.isTeamScoreExsit(teamScore.getSportid(),teamScore.getTeamid(),teamScore.getTaketime())<1){
                teamScore.setChecked(Common.NOCHECK);
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
                return ReturnUtil.ret(true,"修改成功");
            }else {
                //检查是否存在
                if (teamScoreMapper.isTeamScoreExsit(teamScore.getSportid(),teamScore.getTeamid(),teamScore.getTaketime())<1){
                    teamScoreMapper.updateByPrimaryKeySelective(teamScore);
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
    public Map<String, Object> deleteTeamScore(Integer id) {
        if (teamScoreMapper.deleteByPrimaryKey(id)>0){
            return ReturnUtil.ret(true,"删除成功");
        }else {
            return ReturnUtil.ret(false,"删除失败");
        }
    }

    @Override
    public Map<String, Object> getTeamScoreNoCheck(Integer tempPage, Integer pageCapacity, String taketime) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        Integer checked=Common.NOCHECK;
        List<TeamScore> teamScores=teamScoreMapper.getTeamScoreByCheck(tempPage*pageCapacity,pageCapacity,checked,taketime);
        resultMap.put("soloVO",getTeamVO(teamScores));
        resultMap.put("count",teamScoreMapper.getTeamScoreByCheckNu(checked,taketime));
        return resultMap;
    }



    @Override
    public Map<String, Object> getTeamScoreCheck(Integer tempPage, Integer pageCapacity, String taketime) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        Integer checked=Common.CHECKED;
        List<TeamScore> teamScores=teamScoreMapper.getTeamScoreByCheck(tempPage*pageCapacity,pageCapacity,checked,taketime);
        resultMap.put("soloVO",getTeamVO(teamScores));
        resultMap.put("count",teamScoreMapper.getTeamScoreByCheckNu(checked,taketime));
        return resultMap;
    }

    @Override
    public Map<String, Object> checkPreSolo(Integer id) {
        //首先更新状态
        Integer checked=Common.CHECKED;
        preSoloMapper.updateCheck(id,checked);
        return ReturnUtil.ret(true,"审核成功");
    }

    @Override
    public Map<String, Object> checkSolo(Integer id) {
        //首先先更新状态
        Integer checked=Common.CHECKED;
        soloMapper.updateCheck(id,checked);
        return ReturnUtil.ret(true,"审核成功");
    }

    @Override
    public Map<String, Object> checkTeamScore(Integer id) {
        Integer checked=Common.CHECKED;
        teamScoreMapper.updateCheck(id,checked);
        return ReturnUtil.ret(true,"审核成功");
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
            preSoloVO.setGroups(preSolo.get(i).getGroups());
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



    @Override
    public Map<String, Object> countAll(String taketime) {
        return null;
    }



}
