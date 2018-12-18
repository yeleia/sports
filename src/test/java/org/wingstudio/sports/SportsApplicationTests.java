package org.wingstudio.sports;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wingstudio.sports.domain.User;
import org.wingstudio.sports.service.ShowService;
import org.wingstudio.sports.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SportsApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
    }
    @Test
    public void loginTest(HttpServletRequest request){
        User user=new User();
        user.setUsername("sicau");
        user.setUsername("sicau");
        Assert.assertNull(userService.login(user,request));
    }


}
