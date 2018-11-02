package org.wingstudio.sports.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wingstudio.sports.domain.*;
import org.wingstudio.sports.service.UserService;
import org.wingstudio.sports.util.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
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


    @ApiOperation(value = "修改预赛加分规则",notes ="只能修改分数,需要传入的参数为 id，addscore")
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
    @RequestMapping(value = "/getPreRole/{sportid}",method = RequestMethod.GET)
    public List<PreRole> getPreRoleList(@PathVariable("sportid")Integer sportid){
        return userService.getPreRoleList(sportid);
    }

    @ApiOperation(value = "查询所有体育项目id",notes = "查询所有体育项目id和项目名，便于添加加分规则")
    @RequestMapping(value = "/getSportIdList",method = RequestMethod.GET)
    public List<Sport> getSportIdList(){
        return userService.getSportId();
    }

    @ApiOperation(value = "添加决赛规则",notes = "批量添加")
    @RequestMapping(value = "/addRoles",method = RequestMethod.POST)
    public Map<String,Object> addRoles(@RequestParam(value = "role")String role){
        return userService.addRoles(role);
    }

    @ApiOperation(value = "修改决赛加分规则",notes = "只能修改分数,需要传入的参数为 id，addscore")
    @RequestMapping(value = "/updateRole",method = RequestMethod.POST)
    public Map<String,Object> updateRole(@ModelAttribute Role role){
        return userService.updateRole(role);
    }

    @ApiOperation(value = "删除决赛加分规则",notes = "删除决赛加分规则，能不能只从最后一个名次删起走，需要参数为id")
    @RequestMapping(value = "/deleteRole",method = RequestMethod.DELETE)
    public Map<String,Object> deleteRole(@PathVariable("id")Integer id){
        return userService.deleteRole(id);
    }

    @ApiOperation(value = "查询该项目决赛加分规则",notes = "查询该项目的所有加分规则，需传入sportid")
    @RequestMapping(value = "/getRole/{sportid}",method = RequestMethod.GET)
    public List<Role> getRoleList(@PathVariable("sportid")int sportid){
        return userService.getRoleList(sportid);
    }

    @ApiOperation(value = "添加体育项目加分规则",notes = "sportid,sportname,rank,score,campus")
    @RequestMapping(value = "/addTeamRole",method = RequestMethod.POST)
    public Map<String,Object> addTeamRole(@ModelAttribute TeamRole teamRole){
        return userService.addTeamRole(teamRole);
    }
    @ApiOperation(value = "修改体育项目加分规则，只能修改加分分数",notes = "id,score")
    @RequestMapping(value = "/updateTeamRole",method = RequestMethod.POST)
    public Map<String,Object> updateTeamRole(@ModelAttribute TeamRole teamRole){
        return userService.updateTeamRole(teamRole);
    }
    @ApiOperation(value = "删除集体项目规则",notes = "id")
    @RequestMapping(value = "/deleteTeamRole/{id}",method = RequestMethod.DELETE)
    public Map<String,Object> deleteTeamRole(@PathVariable(value = "id")Integer id){
        return userService.deleteTeamRole(id);
    }
    @ApiOperation(value = "查询该集体项目的规则",notes = "查询该项目的规则，sporitid")
    @RequestMapping(value = "/getTeamRoleList/{sportid}",method = RequestMethod.GET)
    public List<TeamRole> getTeamRoleList(@PathVariable("sportid")Integer sportid){
        return userService.getTeamRoleList(sportid);
    }
    @ApiOperation(value = "添加新运动会",notes = "添加新运动会")
    @RequestMapping(value = "/addSportMeet",method = RequestMethod.POST)
    public Map<String,Object> addSportMeet(@ModelAttribute History history){
        return userService.addSportMeet(history);
    }
    @ApiOperation(value ="修改历史记录",notes = "修改运动会")
    @RequestMapping(value = "/updateHistory",method = RequestMethod.POST)
    public Map<String,Object> updateSportMeet(History history){
        return  userService.updateSportMeet(history);
    }
    @ApiOperation(value = "删除历史记录",notes = "删除历史记录")
    @RequestMapping(value = "/delHistory/{id}",method = RequestMethod.GET)
    public Map<String,Object> delHistory(@PathVariable("id")Integer id){
        return userService.delSportMeet(id);
    }
    @ApiOperation(value = "获取所有记录",notes = "获取所有记录")
    @RequestMapping(value = "/getAllSportMeet",method = RequestMethod.GET)
    public List<History> getAllSportMeet(){
        return userService.getAllSportMeet();
    }

}
