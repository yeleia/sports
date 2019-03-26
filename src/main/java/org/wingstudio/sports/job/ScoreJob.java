package org.wingstudio.sports.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.wingstudio.sports.constant.Common;
import org.wingstudio.sports.dao.*;
import org.wingstudio.sports.domain.*;
import org.wingstudio.sports.util.CampareUtil;
import org.wingstudio.sports.util.CountUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yelei
 * @date 2018/12/12
 */
@Component
public class ScoreJob {
    @Autowired
    private SportMapper sportMapper;
    @Autowired
    private PreSoloMapper preSoloMapper;
    @Autowired
    private PreRoleMapper preRoleMapper;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private TeamScoreMapper teamScoreMapper;
    @Autowired
    private TeamRoleMapper teamRoleMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private TwoLevelMapper twoLevelMapper;
    @Autowired
    private SoloMapper soloMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ContestantMapper contestantMapper;
    @Autowired
    private ClassesMapper classesMapper;


    @Scheduled(cron="0 */3 * * * ?")
    public void count(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("count"+df.format(new Date()));
        System.out.println("pre");
        countPreSolo();
        System.out.println("solo");
        countFinal();
        System.out.println("final");
        countAll();
    }
    //预赛加分
    public void countPreSolo(){
        //根据性别获取体育项目，分项目分性别分校区计算预赛单项成绩
        //查询男子组单项项目并计算
        Integer sex=Common.M;
        List<Sport> sportM=sportMapper.getSportBySex(sex);
        countPre(sportM);
        //计算男子组单项项目是否有破记录的
        record(sportM);
        //计算男子组单项项目是否有达到二级运动员标准的
        twoLevel(sportM);
        //查询女子组单项项目并计算
        Integer sex1=Common.W;
        List<Sport> sportW=sportMapper.getSportBySex(sex1);
        countPre(sportW);
        //计算女子组单项项目是否有破记录的
        record(sportW);
        //计算女子组单项项目是否有达到二级运动员标准的
        twoLevel(sportW);
        //集体项目成绩
        Integer sex2=Common.T;
        List<Sport> sportT=sportMapper.getSportBySex(sex2);
        countT(sportT);
    }
    //决赛加分
    public void countFinal(){
        //根据性别获取体育项目，分项目分性别分校区计算预赛单项成绩
        //查询男子组单项项目并计算
        Integer sex=Common.M;
        List<Sport> sportM=sportMapper.getSportBySex(sex);
        countFin(sportM);
        //计算男子组单项项目是否有破记录的
        recordFin(sportM);
        //计算男子组单项项目是否有达到二级运动员标准的
        twoLevelFin(sportM);
        //查询女子组单项项目并计算
        Integer sex1=Common.W;
        List<Sport> sportW=sportMapper.getSportBySex(sex1);
        countFin(sportW);
        //计算女子组单项项目是否有破记录的
        recordFin(sportW);
        //计算女子组单项项目是否有达到二级运动员标准的
        twoLevelFin(sportW);
    }
    //计算总成绩
    public void countAll(){
        //将决赛和预赛对比
        String takeTime=scoreMapper.getTop();
        List<Score> scores=scoreMapper.getAllScore(takeTime);
        for (int i = 0; i <scores.size() ; i++){
            if (!(StringUtils.isEmpty(scores.get(i).getPresoloscore()))&&!(StringUtils.isEmpty(scores.get(i).getSoloscore()))&&scores.get(i).getPresoloscore()>scores.get(i).getSoloscore()){
                scores.get(i).setFinalscore(scores.get(i).getPresoloscore());
            }else if (StringUtils.isEmpty(scores.get(i).getSoloscore())) {
                scores.get(i).setFinalscore(scores.get(i).getPresoloscore());
            }else{
                System.out.println();
                scores.get(i).setFinalscore(scores.get(i).getSoloscore());
            }
        }
        //插入表操作
        for (int i = 0; i <scores.size() ; i++) {
            System.out.println(",,,,,,,");
            System.out.println(scores.get(i));
            scoreMapper.updateByPrimaryKeySelective(scores.get(i));
        }
        //计算学院总分
        List<String> classes=contestantMapper.getClasses(takeTime);
        for (int i = 0; i <classes.size() ; i++) {
            List<Integer> contestants=contestantMapper.getIByClasses(classes.get(i),takeTime);
            Double count=0.0;
            for (int j = 0; j <contestants.size() ; j++) {
                List<Double> scoreList=scoreMapper.getByContestantId(contestants.get(j),takeTime);
                for (int k = 0; k <scoreList.size() ; k++) {
                    if (!StringUtils.isEmpty(scoreList.get(k))) {
                        count = count + scoreList.get(k);
                    }
                }
            }
            List<Double> teamScores=teamScoreMapper.getByClasses(classes.get(i),takeTime);
            for (int j = 0; j <teamScores.size() ; j++) {
                count=count+teamScores.get(j);
            }
            Classes classes1=new Classes();
            classes1.setTeketime(takeTime);
            classes1.setScore(count);
            classes1.setClasses(classes.get(i));
            if (classesMapper.getByClasses(classes.get(i),takeTime)!=null){
                //System.out.println("??uu");
               // System.out.println(classes1.getScore());
                classesMapper.updateClasses(classes1);
            }else {
                //System.out.println("????");
                classesMapper.insertSelective(classes1);
            }

        }
    }


    private void twoLevelFin(List<Sport> sport) {
        String taketime="";
        taketime=soloMapper.getTop();
        twoLevelMapper.deleteAll(taketime,Common.FIN);
        for (int i = 0; i <sport.size() ; i++) {
            List<Solo> solos=soloMapper.getSport(sport.get(i).getId(),taketime);
            for (int j = 0; j <solos.size(); j++) {
                if (CampareUtil.eqRecord(solos.get(j).getScore(),sport.get(i).getTwolevel(),sport.get(i).getSortrule())){
                    int rank=Common.TWOLEVEL;
                    Double addScore=roleMapper.getScoreByRank(sport.get(i).getId(),solos.get(j).getCampus(),rank);
                    solos.get(j).setScore(solos.get(j).getScore()+addScore);
                    TwoLevel twoLevel=new TwoLevel();
                    twoLevel.setContestantid(solos.get(j).getContestantid());
                    twoLevel.setMark(Common.FIN);
                    twoLevel.setSportid(sport.get(i).getId());
                    twoLevel.setTaketime(taketime);
                    twoLevelMapper.insertSelective(twoLevel);
                }
            }
        }
    }
    private void twoLevel(List<Sport> sport) {
        String taketime="";
        taketime=preSoloMapper.getTop();
        twoLevelMapper.deleteAll(taketime,Common.PRE);
        for (int i = 0; i <sport.size() ; i++) {
            List<PreSolo> solos=preSoloMapper.getSport(sport.get(i).getId(),taketime);
            for (int j = 0; j <solos.size() ; j++) {
                if (CampareUtil.eqRecord(solos.get(j).getScore(),sport.get(i).getTwolevel(),sport.get(i).getSortrule())){
                    int rank=Common.TWOLEVEL;
                    Double addScore=preRoleMapper.getScoreByRank(sport.get(i).getId(),solos.get(j).getCampus(),rank);
                    solos.get(j).setScore(solos.get(j).getScore()+addScore);
                    TwoLevel twoLevel=new TwoLevel();
                    twoLevel.setTaketime(taketime);
                    twoLevel.setSportid(sport.get(i).getId());
                    twoLevel.setMark(Common.PRE);
                    twoLevel.setContestantid(solos.get(j).getContestantid());
                    twoLevelMapper.insertSelective(twoLevel);
                }
            }
        }
    }

    private void recordFin(List<Sport> sport) {
        String taketime="";
        taketime=soloMapper.getTop();
        recordMapper.deleteAll(taketime,Common.FIN);
        for (int i = 0; i <sport.size() ; i++) {
            List<Solo> solos=soloMapper.getSport(sport.get(i).getId(),taketime);
            for (int j = 0; j <solos.size() ; j++) {
                if (CampareUtil.eqRecord(solos.get(j).getScore(),sport.get(i).getRecord(),sport.get(i).getSortrule())){
                    //rank等于0表示超过
                    int rank=Common.RECORD;
                    Double addscore=roleMapper.getScoreByRank(sport.get(i).getId(),solos.get(j).getCampus(),rank);
                    solos.get(j).setScore(solos.get(j).getScore()+addscore);
                    Record record=new Record();
                    record.setTaketime(taketime);
                    record.setSportid(sport.get(i).getId());
                    record.setContestantid(solos.get(j).getContestantid());
                    record.setMark(Common.FIN);
                    if (recordMapper.isExsit(record)<1){
                        recordMapper.insertSelective(record);
                    }
                    recordMapper.updateByPrimaryKeySelective(record);
                }
            }
        }
    }
    private void record(List<Sport> sport) {
        String taketime="";
        taketime=preSoloMapper.getTop();
        recordMapper.deleteAll(taketime,Common.PRE);
        for (int i = 0; i <sport.size() ; i++) {
            List<PreSolo> solos=preSoloMapper.getSport(sport.get(i).getId(),taketime);
            for (int j = 0; j <solos.size() ; j++) {
                if (CampareUtil.eqRecord(solos.get(j).getScore(),sport.get(i).getRecord(),sport.get(i).getSortrule())){
                    //rank等于0表示超过
                    int rank=Common.RECORD;
                    Double addscore=preRoleMapper.getScoreByRank(sport.get(i).getId(),solos.get(j).getCampus(),rank);
                    solos.get(j).setScore(solos.get(j).getScore()+addscore);
                    Record record=new Record();
                    record.setTaketime(taketime);
                    record.setSportid(sport.get(i).getId());
                    record.setContestantid(solos.get(j).getContestantid());
                    record.setMark(Common.PRE);
                    recordMapper.insertSelective(record);

                }
            }
        }
    }
    private void countFin(List<Sport> sport) {
        //获取最新录入的届数
        String taketime="";
        taketime=soloMapper.getTop();
        //获取校区
        List<String> campus=soloMapper.getCampus(taketime);
        for (int i = 0; i <sport.size(); i++) {
            //分校区计算
            for (int j = 0; j <campus.size() ; j++) {
                List<Solo> solos = new ArrayList<>();
                if (sport.get(i).getSortrule().equals(Common.DISTANCESORT)) {
                    // //距离根据成绩降序排序
                    solos = soloMapper.getSoloBySportIdDesc(sport.get(i).getId(), taketime, campus.get(j));
                } else {
                    //时间根据成绩升序排序
                    solos = soloMapper.getSoloBySportIdAsc(sport.get(i).getId(), taketime, campus.get(j));
                }
                List<Role> roleList = roleMapper.getRoleBySportId(sport.get(i).getId(), campus.get(j));
                if (solos != null) {
                    List<Score> scores = CountUtil.countSolo(solos, roleList);
                    for (int k = 0; k < scores.size(); k++) {
                        Score score = scoreMapper.isExsitAndGet(scores.get(k).getSportid(), scores.get(k).getContestantid(), scores.get(k).getTaketime());
                        if (score == null) {
                            scoreMapper.insertSelective(scores.get(k));
                        } else {
                            scores.get(k).setId(score.getId());
                            scoreMapper.updateByPrimaryKeySelective(scores.get(k));
                        }
                    }
                }
            }
        }
    }
    public void countPre(List<Sport> sports){
        //获取最新录入的届数
        String taketime="";
        taketime=preSoloMapper.getTop();
        //获取校区
        List<String> campus=preSoloMapper.getCampus(taketime);
        for (int i = 0; i <sports.size() ; i++) {
            //分校区计算
            for (int j = 0; j <campus.size() ; j++) {
                List<PreSolo> preSolos = new ArrayList<>();
                if (sports.get(i).getSortrule().equals(Common.DISTANCESORT)) {
                    //距离根据成绩降序排序
                    preSolos = preSoloMapper.getPreSoloBySportIdDesc(sports.get(i).getId(), taketime, campus.get(j));
                } else {
                    //时间根据成绩升序排序
                    preSolos = preSoloMapper.getPreSoloBySportIdAsc(sports.get(i).getId(), taketime, campus.get(j));
                }

                List<PreRole> preRoles = preRoleMapper.getPreRoleBySportId(sports.get(i).getId(), campus.get(j));
                if (preSolos .size()!=0 && preRoles.size()!=0) {
                    List<Score> scores = CountUtil.count(preSolos, preRoles);
                    for (int k = 0; k < scores.size(); k++) {
                        Score score=scoreMapper.isExsit(scores.get(k).getSportid(), scores.get(k).getContestantid(), scores.get(k).getTaketime());
                        if ( score==null) {
                            scoreMapper.insertSelective(scores.get(k));
                        } else {
                            scoreMapper.updateById(scores.get(k).getPresoloscore(),score.getId());
                        }
                    }
                }
            }

        }
    }



    public void countT(List<Sport> sports){
        String takeTime="";
        takeTime=teamScoreMapper.getTop();
        List<String> campus=teamScoreMapper.getCampus(takeTime);
        //分项目
        for (int i = 0; i <sports.size() ; i++) {
            //分校区
            for (int j = 0; j <campus.size() ; j++) {
                List<TeamRole> teamRoles=teamRoleMapper.getRoleBySportId(sports.get(i).getId(),campus.get(j));
                List<TeamScore> teamScores=teamScoreMapper.getTeamBySportId(sports.get(i).getId(),takeTime);
                List<TeamScore> teamScoreList=CountUtil.countTeam(teamScores,teamRoles);
                for (int k = 0; k <teamScoreList.size() ; k++) {
                    TeamScore teamScore=teamScoreMapper.isTeamScoreExsit(teamScoreList.get(k).getSportid(),teamScoreList.get(k).getTeamid(),takeTime);
                    if (teamScore==null){
                        teamScoreMapper.insertSelective(teamScoreList.get(k));
                    }else {
                        teamScoreMapper.updateByPrimaryKeySelective(teamScoreList.get(k));
                    }
                }
            }
        }
    }

}
