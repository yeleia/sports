package org.wingstudio.sports.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wingstudio.sports.domain.Contestant;
import org.wingstudio.sports.domain.Team;
import org.wingstudio.sports.util.GetTimeUtil;
import org.wingstudio.sports.util.Page;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeamService teamService;

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
    @Test
    public void updatestant(){
        Contestant contestant=new Contestant();
        contestant.setId(1);
        contestant.setCampus("雅安");
        contestant.setClasses("信息工程学院");
        contestant.setProfession("计算机科学与技术");
        contestant.setStunumber("20158618");
        contestant.setStuname("叶磊");
        contestant.setSportid(4);
        System.out.println(studentService.updateContestant(contestant));
    }
    @Test
    public void deleteContestant(){
        Integer id=1;
        System.out.println(studentService.deleteContestant(id));
    }
    @Test
    public void getContestantList(){
        Page page=new Page();
        page.setTempPage(0);
        page.setPageCapacity(2);
        String currenTime="20188";
        //System.out.println(studentService.getContantList(page.getTempPage(),page.getPageCapacity()));
    }
    @Test
    public void getContestantByNum(){
        String stuNum="20158618";
        String currenTime="20188";
        //System.out.println(studentService.getContestantByNum(stuNum));
    }
    @Test
    public void addTeam(){
        Team team=new Team();
        team.setSportid(4);
        team.setSportname("100");
        team.setCampus("雅安");
        team.setClasses("信息工程学院");
        team.setProfession("计教");
        team.setCurrentime(GetTimeUtil.getTime());
        System.out.println(teamService.addTeam(team));
    }
    @Test
    public void updateTeam(){
        Team team=new Team();
        team.setId(1);
        team.setSportid(4);
        team.setSportname("100");
        team.setCampus("雅安");
        team.setClasses("信息工程学院");
        team.setProfession("计教02");
        team.setCurrentime(GetTimeUtil.getTime());
        System.out.println(teamService.updateTeam(team));
    }
    @Test
    public void deleteTeam(){
        Integer id=1;
        System.out.println(teamService.deleteTeam( id));

    }
    @Test
    public void getTeamListByPro(){
        Page page=new Page();
        page.setTempPage(0);
        page.setPageCapacity(2);
        //System.out.println(teamService.getTeamList(page.getTempPage(),page.getPageCapacity()));
    }
    @Test
    public void getCCP(){
        System.out.println(studentService.getCCP().get("campus"));

    }
}
