package org.wingstudio.sports.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wingstudio.sports.domain.Sport;
import org.wingstudio.sports.domain.User;
import org.wingstudio.sports.service.UserService;
import org.wingstudio.sports.util.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(value = "后台操作相关的api",description = "后台操作")
public class UserController {
    @Autowired
    private UserService userService;


    @ApiOperation(value = "根据用户名和密码查询登陆信息", notes = "登陆操作")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,Object> userLogin(User user, HttpServletRequest request){
        return userService.login(user,request);
    }


    @ApiOperation(value = "添加校运动会体育项目",notes = "添加体育项目操作，输入限制，校记录，二级运动员标准可以为null")
    @RequestMapping(value = "/addSport",method = RequestMethod.POST)
    public Map<String,Object> addSport(Sport sport){
        return userService.addSport(sport);
    }


    @ApiOperation(value = "更新运动会体育项目信息",notes ="更新体育项目操作，只更新输入限制，校记录和二级运动员标准，但其余数据也要全部返回" )
    @RequestMapping(value = "/updateSport",method = RequestMethod.POST)
    public Map<String,Object> updateSport(@ModelAttribute Sport sport){
        return userService.updateSport(sport);
    }

    @ApiOperation(value = "查询所有体育项目",notes = "查询所有体育项目，并分页")
    @RequestMapping(value = "/getSportList",method = RequestMethod.GET)
    public Map<String,Object> getSportList(@ModelAttribute Page page){
        Map<String,Object> result=new LinkedHashMap<>();
        result.put("sport", userService.getSportList(page.getTempPage(),page.getPageCapacity()));
        result.put("count",userService.countSportList());
        return result;
    }


}
