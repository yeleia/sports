package org.wingstudio.sports.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wingstudio.sports.domain.PreRole;
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


    @ApiOperation(value = "添加预赛加分规则",notes = "添加预赛加分规则，操作为批量添加")
    @RequestMapping(value = "/addPreRoles",method = RequestMethod.POST)
    public Map<String,Object> addPreRoles(@RequestParam(value = "preRole")String preRole){
        return userService.addPreRoles(preRole);
    }


    @ApiOperation(value = "修改预赛加分规则",notes ="修改预赛加分规则操作，只能修改分数，返回id，addscore")
    @RequestMapping(value = "/updatePreRole",method = RequestMethod.POST)
    public Map<String,Object> updatePreRole(@ModelAttribute PreRole role){
        return userService.updatePreRole(role);
    }


    @ApiOperation(value = "删除预赛加分规则",notes = "删除预赛加分规则，能不能只从最后一个名次删起走")
    @RequestMapping(value = "/deletePreRole/{id}",method = RequestMethod.DELETE)
    public Map<String,Object> deletePreRole(@PathVariable("id")Integer id){
        return userService.deletePreRole(id);
    }

    @ApiOperation(value = "获得某个体育项目的预赛加分规则",notes = "这里时查询的是某个体育项目的预赛加分规则，需传入一个体育项目id(sportid)")
    @RequestMapping(value = "/getPreRoleList/{sportid}",method = RequestMethod.GET)
    public List<PreRole> getPreRoleList(@PathVariable("sportid")Integer sportid){
        return userService.getPreRoleList(sportid);
    }

    @ApiOperation(value = "查询所有体育项目id",notes = "查询所有体育项目id和项目名，便于添加加分规则")
    @RequestMapping(value = "/getSportIdList",method = RequestMethod.GET)
    public List<Sport> getSportIdList(){
        return userService.getSportId();
    }

}
