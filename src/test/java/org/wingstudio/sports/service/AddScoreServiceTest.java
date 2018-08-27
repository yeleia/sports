package org.wingstudio.sports.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wingstudio.sports.domain.PreSolo;
import org.wingstudio.sports.domain.Solo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddScoreServiceTest {
    @Autowired
    private ScoreService scoreService;

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

}
