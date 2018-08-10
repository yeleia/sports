package org.wingstudio.sports.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wingstudio.sports.domain.User;
import org.wingstudio.sports.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api("后台操作相关的api")
public class UserController {
    @Autowired
    private UserService userService;


    @ApiOperation(value = "根据用户名和密码查询登陆信息", notes = "登陆操作")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,Object> userLogin(User user, HttpServletRequest request){
        return userService.login(user,request);
    }

}
