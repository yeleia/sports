package org.wingstudio.sports.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingstudio.sports.VO.*;
import org.wingstudio.sports.dao.*;
import org.wingstudio.sports.domain.*;
import org.wingstudio.sports.service.ShowService;
import org.wingstudio.sports.util.ShowCountUtil;

import java.util.ArrayList;
import java.util.List;

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
    @Override
    public List<String> getClasses(String campus) {
        return studentMapper.getClassesByCampus(campus);
    }

    @Override
    public List<SportVO> getSport(Integer sex) {
        return sportMapper.getSportName(sex);
    }

    @Override
    public List<PreSoloVO> getPreSoloVO(String campus, Integer sportId,String currentime) {
        Sport sport=sportMapper.selectByPrimaryKey(sportId);
        List<PreSolo> preSolos=new ArrayList<>();
        List<Contestant> contestants=contestantMapper.getByCamSpo(campus,sportId,currentime);
        for (int i = 0; i <contestants.size() ; i++) {
            PreSolo preSolo=preSoloMapper.getByConId(contestants.get(i).getId());
            preSolos.add(preSolo);
        }
        //排序  0 距离排序，1 时间排序
        List<PreSoloVO> preSoloVOS=ShowCountUtil.countPreSolo(preSolos,sport.getSortrule());
        for (int i=0;i<preSoloVOS.size();i++){
            Contestant contestant=contestantMapper.selectByPrimaryKey(preSoloVOS.get(i).getContestantid());
            preSoloVOS.get(i).setStunumber(contestant.getStunumber());
            preSoloVOS.get(i).setStuname(contestant.getStuname());
        }
        return preSoloVOS;
    }

    @Override
    public List<SoloVO> getSoloVO(Integer sportId, String currentime) {
        Sport sport=sportMapper.selectByPrimaryKey(sportId);
        List<Solo> solos=soloMapper.getSoloBySportId(sportId,currentime);
        //排序  0 距离排序，1 时间排序
        List<SoloVO> soloVOS=ShowCountUtil.countSolo(solos,sport.getSortrule());
        for (int i = 0; i <soloVOS.size(); i++) {
            Contestant contestant=contestantMapper.selectByPrimaryKey(soloVOS.get(i).getContestantid());
            soloVOS.get(i).setStunumber(contestant.getStunumber());
            soloVOS.get(i).setStuname(contestant.getStuname());
        }
        return soloVOS;
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
                preSolos.add(preSolo);
            }
            List<Solo> solos=new ArrayList<>();
            for (int i = 0; i < contestant.size(); i++) {
                Solo solo=soloMapper.getByConId(contestant.get(0).getId());
                solos.add(solo);
            }
            for (int i = 0; i <preSolos.size() ; i++) {
                QueryVO queryVO=new QueryVO();
                queryVO.setClasses(contestant.get(0).getClasses());
                queryVO.setCampus(contestant.get(0).getCampus());
                queryVO.setNature("预赛");
                queryVO.setStuName(contestant.get(0).getStuname());
                queryVO.setSportname(sportMapper.getSportNameById(preSolos.get(i).getSportid()));
                queryVOS.add(queryVO);
            }
            for (int i = 0; i <solos.size() ; i++) {
                QueryVO queryVO=new QueryVO();
                queryVO.setSportname(sportMapper.getSportNameById(solos.get(i).getSportid()));
                queryVO.setStuName(contestant.get(0).getStuname());
                queryVO.setNature("决赛");
                queryVO.setCampus(contestant.get(0).getCampus());
                queryVO.setClasses(contestant.get(0).getClasses());
                queryVO.setScore(solos.get(i).getScore());
                queryVOS.add(queryVO);
            }
        }
        return queryVOS;
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
            recordVO.setStuname(contestant.getStuname());
            recordVO.setScore(preSoloMapper.getByConId(record.get(i).getContestantid()).getScore());
            if (record.get(i).getMark()==0){
                recordVO.setNature("预赛");
            }else {
                recordVO.setNature("决赛");
            }
            if (sport.getSex()==0){
                recordVO.setSex("男");
            }else {
                recordVO.setSex("女");
            }
            recordVOS.add(recordVO);
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
            twoLevelVO.setScore(preSoloMapper.getByConId(twoLevels.get(i).getContestantid()).getScore());
            if (twoLevels.get(i).getMark()==0){
                twoLevelVO.setNature("预赛");
            }else {
                twoLevelVO.setNature("决赛");
            }
            if (sport.getSex()==0){
                twoLevelVO.setSex("男");
            }else {
                twoLevelVO.setSex("女");
            }
            twoLevelList.add(twoLevelVO);
        }
        return twoLevelList;
    }
//添加集体项目时，添加的是几等奖
    @Override
    public List<TeamVO> getTeamVO(String campus, Integer sportid, String taketime) {
        List<Team> teams=teamMapper.getCamSpoid(campus,sportid,taketime);
        List<TeamScore> teamScores=new ArrayList<>();
        for (int i = 0; i <teams.size() ; i++) {
            TeamScore teamScore=teamScoreMapper.getByTeamIdSpoid(teams.get(i).getId());
            teamScores.add(teamScore);
        }
        List<TeamVO> teamVOS=new ArrayList<>();
        for (int i = 0; i <teamScores.size() ; i++) {
            TeamVO teamVO=new TeamVO();
            teamVO.setScore(teamScores.get(i).getScore());
            teamVO.setSportname(sportMapper.selectByPrimaryKey(teamScores.get(i).getSportid()).getProject());
            Team team=teamMapper.selectByPrimaryKey(teamScores.get(i).getTeamid());
            teamVO.setProfession(team.getProfession());
            teamVO.setClasses(team.getClasses());
            teamVOS.add(teamVO);
        }
        return teamVOS;
    }
}
