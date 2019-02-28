package org.wingstudio.sports.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wingstudio.sports.domain.*;
import org.wingstudio.sports.service.StudentService;
import org.wingstudio.sports.service.TeamService;
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
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeamService teamService;

    @ApiOperation(value = "根据用户名和密码查询登陆信息", notes = "登陆操作")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,Object> userLogin(User user, HttpServletRequest request){
        return userService.login(user,request);
    }


    @ApiOperation(value = "添加校运动会体育项目",notes = "添加体育项目操作，输入限制，校记录，二级运动员标准不为null")
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
    @RequestMapping(value = "/deleteRole/{id}",method = RequestMethod.DELETE)
    public Map<String,Object> deleteRole(@PathVariable("id")Integer id){
        return userService.deleteRole(id);
    }

    @ApiOperation(value = "查询该项目决赛加分规则",notes = "查询该项目的所有加分规则，需传入sportid")
    @RequestMapping(value = "/getRole/{sportid}",method = RequestMethod.GET)
    public List<Role> getRoleList(@PathVariable(value = "sportid")int sportid){
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
    public List<TeamRole> getTeamRoleList(@PathVariable(value = "sportid")Integer sportid){
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
    public Map<String,Object> delHistory(@PathVariable(value = "id")Integer id){
        return userService.delSportMeet(id);
    }
    @ApiOperation(value = "获取所有记录",notes = "获取所有记录")
    @RequestMapping(value = "/getAllSportMeet",method = RequestMethod.GET)
    public List<History> getAllSportMeet(){
        return userService.getAllSportMeet();
    }

    @ApiOperation(value = "查询参赛者信息列表(已审核）",notes = "tempPage(当前页数)，pageCapacity(每页显示的数据量),currentime(第几届）")
    @RequestMapping(value = "/getContestantList",method = RequestMethod.GET)
    public Map<String,Object> getContestantList(@ModelAttribute Page page,@RequestParam(value = "currentime")String currentime){
        return userService.getContantList(page.getTempPage(),page.getPageCapacity(),currentime);
    }
    @ApiOperation(value = "查询参赛者信息列表（未审核)",notes = "tempPage(当前页数)，pageCapacity(每页显示的数据量),currentime(第几届）")
    @RequestMapping(value = "/getContestantNoCheck",method = RequestMethod.GET)
    public Map<String,Object> getContestantListNoCheck(@ModelAttribute Page page,@RequestParam(value = "currentime")String currentime){
        return userService.getContantListNoCheck(page.getTempPage(),page.getPageCapacity(),currentime);
    }
    @ApiOperation(value = "查询参赛者修改信息列表",notes = "tempPage(当前页数)，pageCapacity(每页显示的数据量),currentime(第几届）")
    @RequestMapping(value = "/getContestantUp",method = RequestMethod.GET)
    public Map<String,Object> getContestantUp(@ModelAttribute Page page,@RequestParam(value = "currentime")String currentime){
        return userService.getContestantUp(page.getTempPage(),page.getPageCapacity(),currentime);
    }
    @ApiOperation(value = "审核参赛者报名和修改",notes = "id")
    @RequestMapping(value = "/checkContestant/{id}",method = RequestMethod.POST)
    public Map<String,Object> checkContestant(@PathVariable(value = "id")Integer id){
        return userService.checkContestant(id);
    }
    @ApiOperation(value = "审核参赛者修改报名信息",notes = "id")
    @RequestMapping(value = "/checkUpdate/{id}",method = RequestMethod.POST)
    public Map<String,Object> checkUpdate(@PathVariable(value = "id")Integer id){
        return userService.checkContestant(id);
    }
    @ApiOperation(value = "删除参赛者信息",notes = "id")
    @RequestMapping(value = "/deleteContestant/{id}",method = RequestMethod.DELETE)
    public Map<String,Object> deleteContestant(@PathVariable("id")Integer id){
        return studentService.deleteContestant(id);
    }
    @ApiOperation(value = "根据学号查询改参赛者信息",notes = "stunumber(学号),currentime(第几届)")
    @RequestMapping(value = "/getContestantByNum/{stunumber}/{currentime}",method = RequestMethod.GET)
    public List<Contestant> getContestantByNum(@PathVariable("stunumber")String stunumber,@PathVariable("currentime")String currentime){
        return studentService.getContestantByNum(stunumber,currentime);
    }
    @ApiOperation(value = "获取该届运动会所有参赛团队信息(已审核）",notes = "tempPage(当前页数)，pageCapacity(每页显示的数据量),currentime(第几届）")
    @RequestMapping(value = "/getTeamList",method = RequestMethod.GET)
    public Map<String,Object>getTeamList(@ModelAttribute Page page,@RequestParam(value = "currentime")String currentime){
        return teamService.getTeamList(page.getTempPage(),page.getPageCapacity(),currentime);
    }
    @ApiOperation(value = "获取该团队参赛信息",notes = "classes(学院），profession(专业），currentime(第几届),tempPage(当前页数)，pageCapacity(每页显示的数据量)")
    @RequestMapping(value = "/getTeam",method = RequestMethod.GET)
    public Map<String,Object> getTeam(@ModelAttribute Page page,@ModelAttribute Team team){
        return teamService.getTeam(page.getTempPage(),page.getPageCapacity(),team);
    }
    @ApiOperation(value = "获取未审核的团队信息",notes = "tempPage(当前页数)，pageCapacity(每页显示的数据量),currentime(第几届）" )
    @RequestMapping(value = "/getTeamNoCheck",method = RequestMethod.GET)
    public Map<String,Object> getTeamNoCheck(@ModelAttribute Page page,@RequestParam(value = "currentime")String currentime){
        return teamService.getTeamNoCheck(page.getTempPage(),page.getPageCapacity(),currentime);
    }
    @ApiOperation(value = "审核团队添加和修改信息",notes ="id" )
    @RequestMapping(value = "/checkedTeam/{id}",method = RequestMethod.POST)
    public Map<String,Object> checkedTeam(@PathVariable(value = "id")Integer id){
        return teamService.checkTeam(id);
    }
    @ApiOperation(value = "获取未审核修改的团队信息",notes = "tempPage(当前页数)，pageCapacity(每页显示的数据量),currentime(第几届）" )
    @RequestMapping(value = "/getTeamNoCheckUp",method = RequestMethod.GET)
    public Map<String,Object> getTeamNoCheckUp(@ModelAttribute Page page,@RequestParam(value = "currentime")String currentime){
        return teamService.getTeamNoCheckUp(page.getTempPage(),page.getPageCapacity(),currentime);
    }
    @ApiOperation(value = "添加届数",notes = "taketime(届数），theme（主题）")
    @RequestMapping(value = "/addHistory",method = RequestMethod.POST)
    public Map<String,Object> addHistory(@ModelAttribute History history){
        return userService.addHistory(history);
    }
    @ApiOperation(value = "修改主题",notes = "id,theme（主题）")
    @RequestMapping(value = "/updateTheme",method = RequestMethod.POST)
    public Map<String,Object> updateTheme(@ModelAttribute History history){
        return userService.updateTheme(history);
    }
    @ApiOperation(value = "删除届数",notes = "id")
    @RequestMapping(value = "/deleHistory/{id}",method = RequestMethod.POST)
    public Map<String,Object> deleteHis(@PathVariable(value = "id")Integer id){
        return userService.deleteHistory(id);
    }
    @ApiOperation(value = "获取届数")
    @RequestMapping(value = "/getAllHistory",method = RequestMethod.GET)
    public List<History> getAllHistory(){
        return userService.getAllHistory();
    }
}
