package org.wingstudio.sports.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wingstudio.sports.dao.ScoreMapper;
import org.wingstudio.sports.domain.PreSolo;
import org.wingstudio.sports.domain.Score;
import org.wingstudio.sports.domain.Solo;
import org.wingstudio.sports.domain.Sport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddScoreServiceTest {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private ShowService showService;
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private UserService userService;

    @Test
    public void addPreScore(){
        PreSolo preSolo=new PreSolo();
        preSolo.setScore("11.2");
        preSolo.setContestantid(1);
        preSolo.setTaketime("19");
        preSolo.setContestantid(1);
        preSolo.setSportid(4);
        System.out.println(scoreService.addPreSoloScore(preSolo));
    }
    @Test
    public void updatePreScore(){
        PreSolo preSolo=new PreSolo();
        System.out.println(scoreService.updatePreSoloScore(preSolo));
    }
    @Test
    public void deletePreScore(){
        Integer id=0;
       // System.out.println(scoreService.deletePreSoloScore(id));
    }
    @Test
    public void addSoloScore(){
        Solo solo=new Solo();
        System.out.println(scoreService.addSoloScore(solo));
    }
    @Test
    public void test(){
        Map<String,Object> resultMap=scoreService.getPreSoloNoCheck(0,2,"39");

        System.out.println(resultMap.get("preSoloVO"));
        System.out.println(resultMap.get("count"));
    }
    @Test
    public void test1(){
        showService.getSport(0);
    }
    @Test
    public void patch(){
        List<Score> scoreList=new ArrayList<>();
        Score score1=new Score();
        score1.setSportid(1);
        score1.setPresoloscore(2.3);
        Score score2=new Score();
        score1.setSportid(1);
        score2.setPresoloscore(2.3);
        Score score3=new Score();
        score1.setSportid(1);
        score3.setPresoloscore(2.3);
        scoreList.add(score1);
        scoreList.add(score2);
        scoreList.add(score3);
        scoreMapper.insertAndUpdate(scoreList);
    }
    @Test
    public void addSport(){
        Sport sport=new Sport();
        sport.setProject("800米");
        sport.setSex(0);
        sport.setTwolevel("20");
        sport.setRecord("12");
        sport.setInmin("19");
        sport.setInmax("20");
        sport.setSortrule(0);
        System.out.println(userService.addSport(sport));

    }

}
