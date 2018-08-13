package org.wingstudio.sports.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.wingstudio.sports.domain.Sport;
import org.wingstudio.sports.util.Page;

@RunWith(SpringRunner.class)
@SpringBootTest

public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    @Transactional
    public void addSportTest(){
        Sport sport=new Sport();
        sport.setProject("100");
        sport.setSortrule(1);
        sport.setSex(0);
        sport.setInmax("1:22.5");
        sport.setInmin("50.1");
        System.out.println(userService.addSport(sport));
        //Assert.assertEquals(,1);
    }
    @Test
    @Transactional
    public void updateSport(){
        Sport sport=new Sport();
        sport.setId(4);
        sport.setProject("100");
        sport.setSortrule(1);
        sport.setSex(0);
        sport.setRecord("45.1");
        sport.setTwolevel("60.2");
        userService.updateSport(sport);
    }
    @Test
    public void getSportList(){
        Page page=new Page();
        page.setTempPage(0);
        page.setPageCapacity(2);
        System.out.println(userService.getSportList(page.getTempPage(), page.getPageCapacity()).size());
    }

}
