package org.wingstudio.sports.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wingstudio.sports.domain.Contestant;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Test
    public void addContestant(){
        Contestant contestant=new Contestant();
        contestant.setCampus("雅安");
        contestant.setClasses("信息工程学院");
        contestant.setProfession("计算机科学与技术");
        contestant.setStunumber("20158618");
        contestant.setStuname("叶磊");
        contestant.setSportid(4);
        System.out.println(studentService.addContestant(contestant));
    }
}
