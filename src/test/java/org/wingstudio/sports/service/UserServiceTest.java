package org.wingstudio.sports.service;
import io.swagger.models.auth.In;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.wingstudio.sports.domain.PreRole;
import org.wingstudio.sports.domain.Sport;
import org.wingstudio.sports.util.Page;

import java.util.ArrayList;
import java.util.List;

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
    @Test
    public void addPrerole(){
        String role="";
        List<PreRole> preRoles=new ArrayList<>();
        PreRole preRole=new PreRole();
        preRole.setSportid(4);
        preRole.setRank(1);
        preRole.setAddscore(8.0);
        preRoles.add(preRole);
        PreRole preRole1=new PreRole();
        preRole1.setSportid(4);
        preRole1.setRank(2);
        preRole1.setAddscore(7.0);
        preRoles.add(preRole1);
        JSONArray array = JSONArray.fromObject(preRoles);
        role=array.toString();
        System.out.println(role);
        System.out.println(userService.addPreRoles(role));
    }
    @Test
    public void updatePreRole(){
        PreRole role=new PreRole();
        role.setId(1);
        role.setAddscore(9.0);
        System.out.println(userService.updatePreRole(role));
    }
    @Test
    public void deletePreRole(){
        Integer id=2;
        System.out.println(userService.deletePreRole(id));
    }
    @Test
    public void getPreRoleList(){
        System.out.println(userService.getPreRoleList(4));
    }
    @Test
    public void getSportId(){
        System.out.println(userService.getSportId().size());
    }

}
