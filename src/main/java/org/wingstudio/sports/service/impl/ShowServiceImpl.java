package org.wingstudio.sports.service.impl;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wingstudio.sports.VO.*;
import org.wingstudio.sports.constant.Common;
import org.wingstudio.sports.dao.*;
import org.wingstudio.sports.domain.*;
import org.wingstudio.sports.service.ShowService;
import org.wingstudio.sports.util.ShowCountUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("showService")
public class ShowServiceImpl implements ShowService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private SportMapper sportMapper;
    @Autowired
    private ClassesMapper classesMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private ContestantMapper contestantMapper;
    @Autowired
    private PreSoloMapper preSoloMapper;
    @Autowired
    private TwoLevelMapper twoLevelMapper;
    @Autowired
    private SoloMapper soloMapper;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private TeamScoreMapper teamScoreMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public List<String> getClasses(String campus) {
        return studentMapper.getClassesByCampus(campus);
    }

    @Override
    public List<SportVO> getSport(Integer sex) {
        return sportMapper.getSportName(sex);
    }

    @Override
    public List<PreSoloVO> getPreSoloVO(String campus, String project,Integer sex,String currentime) {
             Sport sport = sportMapper.selectByProSex(project, sex);
             if (sport!=null){
            List<PreSolo> preSolos = new ArrayList<>();
            List<Contestant> contestants = contestantMapper.getByCamSpo(campus, sport.getId(), currentime);
            for (int i = 0; i < contestants.size(); i++) {
                PreSolo preSolo = preSoloMapper.getByConId(contestants.get(i).getId());
                if (preSolo!=null) {
                    preSolos.add(preSolo);
                }
            }

            //排序  0 距离排序，1 时间排序
            List<PreSoloVO> preSoloVOS = ShowCountUtil.countPreSolo(preSolos, sport.getSortrule());
            for (int i = 0; i < preSoloVOS.size(); i++) {
                Contestant contestant = contestantMapper.selectByPrimaryKey(preSoloVOS.get(i).getContestantid());
                preSoloVOS.get(i).setStunumber(contestant.getStunumber());
                preSoloVOS.get(i).setStuname(contestant.getStuname());
                preSoloVOS.get(i).setClasses(contestant.getClasses());
                preSoloVOS.get(i).setProfession(contestant.getProfession());
            }
            return preSoloVOS;
        }else {
            return null;
        }
    }

    @Override
    public List<SoloVO> getSoloVO(String  project, Integer sex,String currentime) {
        Sport sport=sportMapper.selectByProSex(project,sex);
        if (sport!=null) {
            List<Solo> solos = soloMapper.getSoloBySportId(sport.getId(), currentime);
            //排序  0 距离排序，1 时间排序
            List<SoloVO> soloVOS = ShowCountUtil.countSolo(solos, sport.getSortrule());
            for (int i = 0; i < soloVOS.size(); i++) {
                Contestant contestant = contestantMapper.selectByPrimaryKey(soloVOS.get(i).getContestantid());
                soloVOS.get(i).setStunumber(contestant.getStunumber());
                soloVOS.get(i).setStuname(contestant.getStuname());
                soloVOS.get(i).setProfession(contestant.getProfession());
                soloVOS.get(i).setClasses(contestant.getClasses());

            }
            return soloVOS;
        }else {
            return null;
        }
    }

    @Override
    public List<Classes> getAllClasses(String taketime) {
        List<Classes> classes=classesMapper.getAllClasses(taketime);
        return classes;
    }

    @Override
    public List<Classes> getClassesByC(String taketime) {
        List<Classes> classesList=new ArrayList<>();
        //List<String> classes=studentMapper.getClassesByCampus()
        String campus[]=new String[]{"雅安","温江","都江堰"};
        for (int i = 0; i <campus.length ; i++) {
            List<String> classes=studentMapper.getClassesByCampus(campus[i]);
            for(int j=0;j<classes.size();j++){
                Classes classes1=classesMapper.getByClasses(classes.get(j),taketime);
                classesList.add(classes1);
            }
        }
        return classesList;
    }

    @Override
    public List<QueryVO> getQuery(String stunu,String taketime) {
        List<Contestant> contestant=contestantMapper.getContestantByNum(stunu,taketime);
        List<QueryVO> queryVOS=new ArrayList<>();
        if (contestant!=null){
            List<PreSolo> preSolos=new ArrayList<>();
            for (int i = 0; i <contestant.size() ; i++) {
                PreSolo preSolo=preSoloMapper.getByConId(contestant.get(0).getId());
                if (preSolo!=null) {
                    preSolos.add(preSolo);
                }
            }
            List<Solo> solos=new ArrayList<>();
            for (int i = 0; i < contestant.size(); i++) {
                Solo solo=soloMapper.getByConId(contestant.get(0).getId());
                if (solo!=null) {
                    solos.add(solo);
                }
            }
            for (int i = 0; i <preSolos.size() ; i++) {
                String sort=null;
                if (preSolos.get(i)!=null) {
                    sort = sportMapper.getSportSort(preSolos.get(i).getSportid());
                }
                List<PreSolo> preSoloes=new ArrayList<>();
                if (sort!=null&&sort.equals(Common.TIMESORT)){
                    preSoloes=preSoloMapper.getBySportIdDesc(preSolos.get(i).getSportid(),taketime);
                }else if (sort!=null){
                    preSoloes=preSoloMapper.getBySportIdAsc(preSolos.get(i).getSportid(),taketime);
                }
                QueryVO queryVO=new QueryVO();
                queryVO.setClasses(contestant.get(0).getClasses());
                queryVO.setCampus(contestant.get(0).getCampus());
                queryVO.setNature("预赛");
                queryVO.setScore(preSolos.get(i).getScore());
                queryVO.setStuName(contestant.get(0).getStuname());
                queryVO.setSportname(sportMapper.getSportNameById(contestant.get(0).getSportid()));
                if (preSoloes!=null) {
                    queryVO.setRank(countRankPre(preSoloes, contestant.get(0).getId()));
                    queryVOS.add(queryVO);
                }
            }
            if (solos.size()!=0) {
                for (int i = 0; i < solos.size(); i++) {
                    String sort = sportMapper.getSportSort(solos.get(i).getSportid());
                    List<Solo> solos1 = new ArrayList<>();
                    if (sort.equals(Common.TIMESORT)) {
                        solos1 = soloMapper.getBySportIdDesc(solos.get(i).getSportid(), taketime);

                    } else {
                        solos1 = soloMapper.getBySportIdAsc(solos.get(i).getSportid(), taketime);
                    }
                    QueryVO queryVO = new QueryVO();
                    queryVO.setRank(countRank(solos1, contestant.get(0).getId()));
                    queryVO.setSportname(sportMapper.getSportNameById(solos.get(i).getSportid()));
                    queryVO.setStuName(contestant.get(0).getStuname());
                    queryVO.setNature("决赛");
                    queryVO.setCampus(contestant.get(0).getCampus());
                    queryVO.setClasses(contestant.get(0).getClasses());
                    queryVO.setScore(solos.get(i).getScore());
                    queryVOS.add(queryVO);
                }
            }
        }
        return queryVOS;
    }

    private String countRank(List<Solo> solos, Integer contestantId) {
        int n=1;
        for (int i = 0; i <solos.size() ; i++) {
            if (i!=0&&solos.get(i).getScore()==solos.get(i-1).getScore()){
                n--;
                solos.get(i).setTemp(n);
            }else {
                solos.get(i).setTemp(n);
                n++;
            }
        }
        String ret="";
        for (int j = 0; j < solos.size(); j++) {
            if (solos.get(j).getContestantid()==contestantId){
                ret = String.valueOf(solos.get(j).getTemp());
            }
        }
        return ret;

    }

    //计算查询排名
    private String  countRankPre(List<PreSolo> preSolos,Integer contestantId) {
        int n=1;
        for (int i = 0; i <preSolos.size() ; i++) {
            if (i!=0&&preSolos.get(i).getScore()==preSolos.get(i-1).getScore()){
                n--;
                preSolos.get(i).setTemp(n);
            }else {
                preSolos.get(i).setTemp(n);
                n++;
            }
        }
        String ret="";
        for (int j = 0; j < preSolos.size(); j++) {
            if (preSolos.get(j).getContestantid()==contestantId){
                ret = String.valueOf(preSolos.get(j).getTemp());
            }
        }
        return ret;

    }

    @Override
    public List<RecordVO> getRecord(String taketime) {
        List<Record> record=recordMapper.getByTaketime(taketime);
        List<RecordVO> recordVOS=new ArrayList<>();
        for (int i = 0; i <record.size() ; i++) {
            RecordVO recordVO=new RecordVO();
            Sport sport=sportMapper.selectByPrimaryKey(record.get(i).getSportid());
            recordVO.setSportname(sport.getProject());
            Contestant contestant=contestantMapper.selectByPrimaryKey(record.get(i).getContestantid());
            recordVO.setClasses(contestant.getClasses());
            recordVO.setProfession(contestant.getProfession());
            recordVO.setStunumber(contestant.getStunumber());
            recordVO.setStuname(contestant.getStuname());
            if (record.get(i).getMark()==0){
                recordVO.setNature("预赛");
            }else {
                recordVO.setNature("决赛");
            }
            if (sport.getSex()==Common.M){
                recordVO.setSex("男");
            }else {
                recordVO.setSex("女");
            }
            PreSolo preSolo=null;
            Solo solo=null;
            if (record.get(i).getMark()==0){
                preSolo=preSoloMapper.getByConId(record.get(i).getContestantid());
                if (!StringUtils.isEmpty(record.get(i).getContestantid())&&preSolo!=null&&!StringUtils.isEmpty(preSolo.getScore())){
                    recordVO.setScore(preSoloMapper.getByConId(record.get(i).getContestantid()).getScore());
                }
            }else {
                solo=soloMapper.getByConId(record.get(i).getContestantid());
                if (!StringUtils.isEmpty(record.get(i).getContestantid())&&solo!=null&&!StringUtils.isEmpty(solo.getScore())){
                    recordVO.setScore(soloMapper.getByConId(record.get(i).getContestantid()).getScore());
                }
            }

            if (preSolo!=null||solo!=null) {
                recordVOS.add(recordVO);
            }
        }
        return recordVOS;
    }

    @Override
    public List<TwoLevelVO> getTwoLevel(String taketime) {
        List<TwoLevel> twoLevels=twoLevelMapper.getByTaketime(taketime);
        List<TwoLevelVO> twoLevelList=new ArrayList<>();
        for (int i = 0; i <twoLevels.size() ; i++) {
            TwoLevelVO twoLevelVO=new TwoLevelVO();
            Sport sport=sportMapper.selectByPrimaryKey(twoLevels.get(i).getSportid());
            twoLevelVO.setSportname(sport.getProject());
            Contestant contestant=contestantMapper.selectByPrimaryKey(twoLevels.get(i).getContestantid());
            twoLevelVO.setClasses(contestant.getClasses());
            twoLevelVO.setStuname(contestant.getStuname());
            twoLevelVO.setStunumber(contestant.getStunumber());
            twoLevelVO.setProfession(contestant.getProfession());
            PreSolo solo=null;
            Solo solos=null;
            if (twoLevels.get(i).getMark()==0){
                twoLevelVO.setNature("预赛");
            }else {
                twoLevelVO.setNature("决赛");
            }
            if (sport.getSex()==Common.M){
                twoLevelVO.setSex("男");
            }else {
                twoLevelVO.setSex("女");
            }
            if (twoLevels.get(i).getMark()==0){
                solo=preSoloMapper.getByConId(twoLevels.get(i).getContestantid());
                if (!StringUtils.isEmpty(solo)) {
                    twoLevelVO.setScore(solo.getScore());
                }
            }else {
                solos=soloMapper.getByConId(twoLevels.get(i).getContestantid());
                if (!StringUtils.isEmpty(solos)) {
                    twoLevelVO.setScore(solos.getScore());
                }
            }
            System.out.println("abcs"+twoLevelVO);
            twoLevelList.add(twoLevelVO);
        }

        return twoLevelList;
    }
//添加集体项目时，添加的是几等奖
    @Override
    public List<TeamVO> getTeamVO(String campus, String project, String taketime) {
        Sport sport = sportMapper.selectByProSex(project, Common.T);
        if (sport == null) {
            return null;
        } else {
            List<Team> teams = teamMapper.getCamSpoid(campus, sport.getId(), taketime);
            List<TeamScore> teamScores = new ArrayList<>();
            for (int i = 0; i < teams.size(); i++) {
                TeamScore teamScore = teamScoreMapper.getByTeamIdSpoid(teams.get(i).getId());
                if (teamScore != null) {
                    teamScores.add(teamScore);
                }
            }
            List<TeamVO> teamVOS = new ArrayList<>();
            for (int i = 0; i < teamScores.size(); i++) {
                TeamVO teamVO = new TeamVO();
                teamVO.setScore(teamScores.get(i).getScore());
                teamVO.setSportname(sportMapper.selectByPrimaryKey(teamScores.get(i).getSportid()).getProject());
                Team team = teamMapper.selectByPrimaryKey(teamScores.get(i).getTeamid());
                teamVO.setProfession(team.getProfession());
                teamVO.setClasses(team.getClasses());
                teamVOS.add(teamVO);
            }
            return teamVOS;
        }
    }

    @Override
    public Map<String, Object> getIndex(String taketime) {
        Map<String,Object> all=new LinkedHashMap<>();
        //获取学院总成绩
        List<Classes> classesAll=classesMapper.getClasese(taketime);
        /*List<String> campus=preSoloMapper.getCampus(taketime);
        for (int i = 0; i <campus.size() ; i++) {
            List<String> classes=preSoloMapper.getClasses(campus.get(i),taketime);
            for (int j = 0; j <classes.size() ; j++) {
                classesAll.add(classesMapper.getByClasses(classes.get(j),taketime));
            }
        }*/

        all.put("classes",classesAll);
        //学院成绩排名
        all.put("classesSort",classesSort(classesMapper.getAllClasses(taketime)));
        //破校记录名单
        all.put("record",getRecord(taketime));
        //达到二级运动员标准
        all.put("twoLevel",getTwoLevel(taketime));
        //预赛一百米男生
        all.put("presolo",getPreSoloVO("雅安校区","100米",Common.M,taketime));
        //决赛一百米男生
        all.put("solo",getSoloVO("100米",Common.M,taketime));
        //集体项目
        all.put("team",getTeamVO("雅安校区",sportMapper.getTeamTop(Common.T),taketime));
        //查询留言
        all.put("message",messageMapper.getCheck());
        //获取单项体育项目
        all.put("sport",sportMapper.getSportBySex(Common.M));
        //获取集体项目
        all.put("teamSport",sportMapper.getSportTeam());
        return all;
    }

    private List<Classes> classesSort(List<Classes> classesAll) {
        int temp=1;
        for (int i = 0; i < classesAll.size(); i++) {
            if (i==0){
                classesAll.get(i).setId(temp);
            }else if (classesAll.get(i).getScore().equals(classesAll.get(i-1).getScore())){
                classesAll.get(i).setId(temp--);
            }else {
                classesAll.get(i).setId(temp);
            }
            temp++;
        }
        return classesAll;
    }
}
