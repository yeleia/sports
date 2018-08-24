package org.wingstudio.sports.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wingstudio.sports.domain.Contestant;
import org.wingstudio.sports.domain.Team;
import org.wingstudio.sports.service.StudentService;
import org.wingstudio.sports.service.TeamService;
import org.wingstudio.sports.util.Page;

import java.util.List;
import java.util.Map;

@Api("学生报名操作api")
@RestController
@RequestMapping("/admin")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeamService teamService;
    @ApiOperation(value ="添加参赛信息",notes = "sportid，sportname，campus，classes，profession，stunumber，stuname，currentime")
    @RequestMapping(value = "/addContestant",method = RequestMethod.POST)
    public Map<String,Object> addContestant(@ModelAttribute Contestant contestant){
        return studentService.addContestant(contestant);
    }
    @ApiOperation(value = "修改参赛者信息",notes = "id，sportid，sportname，campus，classes，profession，stunumber，stuname，currentime")
    @RequestMapping(value = "/updateContestant",method = RequestMethod.POST)
    public Map<String,Object> updateContestant(@ModelAttribute Contestant contestant){
        return studentService.updateContestant(contestant);
    }
    @ApiOperation(value = "删除参赛者信息",notes = "id")
    @RequestMapping(value = "/deleteContestant/{id}",method = RequestMethod.DELETE)
    public Map<String,Object> deleteContestant(@PathVariable("id")Integer id){
        return studentService.deleteContestant(id);
    }
    @ApiOperation(value = "查询参赛者信息列表",notes = "tempPage(当前页数)，pageCapacity(每页显示的数据量),currentime(第几届）")
    @RequestMapping(value = "/getContestantList",method = RequestMethod.GET)
    public Map<String,Object> getContestantList(@ModelAttribute Page page,@PathVariable("currentime")String currentime){
        return studentService.getContantList(page.getTempPage(),page.getPageCapacity(),currentime);
    }
    @ApiOperation(value = "根据学号查询改参赛者信息",notes = "stunumber(学号),currentime(第几届)")
    @RequestMapping(value = "/getContestantByNum/{stunumber}/{currentime}",method = RequestMethod.GET)
    public List<Contestant> getContestantByNum(@PathVariable("stunumber")String stunumber,@PathVariable("currentime")String currentime){
        return studentService.getContestantByNum(stunumber,currentime);
    }
    @ApiOperation(value = "添加团队报名信息",notes = "sportid,sportname,campus,classes,profession,currentime")
    @RequestMapping(value = "/addTeam",method = RequestMethod.POST)
    public Map<String,Object> addTeam(@ModelAttribute Team team){
        return teamService.addTeam(team);
    }
    @ApiOperation(value = "修改团队信息",notes = "id,sportid,sportname,campus,classes,profession,currentime")
    @RequestMapping(value = "/updateTeam",method = RequestMethod.POST)
    public Map<String,Object> updateTeam(@ModelAttribute Team team){
        return teamService.updateTeam(team);
    }
    @ApiOperation(value = "删除团队信息",notes = "id")
    @RequestMapping(value = "/deleteTeam/{id}",method = RequestMethod.DELETE)
    public Map<String,Object> deleteTeam(@PathVariable("id")Integer id){
        return teamService.deleteTeam(id);
    }
    @ApiOperation(value = "获取该届运动会所有参赛团队信息",notes = "tempPage(当前页数)，pageCapacity(每页显示的数据量),currentime(第几届）")
    public Map<String,Object>getTeamList(@ModelAttribute Page page,@PathVariable("currentime")String currentime){
        return teamService.getTeamList(page.getTempPage(),page.getPageCapacity(),currentime);
    }
}
