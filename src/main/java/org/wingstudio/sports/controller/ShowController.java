package org.wingstudio.sports.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wingstudio.sports.VO.PreSoloVO;
import org.wingstudio.sports.VO.QueryVO;
import org.wingstudio.sports.VO.SoloVO;
import org.wingstudio.sports.VO.TeamVO;
import org.wingstudio.sports.domain.History;
import org.wingstudio.sports.domain.Message;
import org.wingstudio.sports.service.MessageService;
import org.wingstudio.sports.service.ShowService;
import org.wingstudio.sports.service.UserService;

import java.util.List;
import java.util.Map;

@Api("展示页面api")
@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShowService showService;

    @ApiOperation(value = "添加留言",notes ="message,stunum,stuname" )
    @RequestMapping(value = "/addMessage",method = RequestMethod.POST)
    public Map<String,Object> addMessage(@ModelAttribute Message message){
        return messageService.addMessage(message);
    }
    @ApiOperation(value = "查询已经审核的留言",notes = "id")
    @RequestMapping(value = "/getCheck",method = RequestMethod.GET)
    public List<Message> getCheck(){
        return messageService.getCheck();
    }
    @ApiOperation(value = "获取所有届数")
    @RequestMapping(value = "/getAllNu")
    public List<History> getAllNu(){
        return userService.getAllHistory();
    }
    @ApiOperation(value = "获取首页信息",notes = "参数（taketime）返回内容（classes：学院总成绩，classesSort：学院成绩排名，record：破校记录名单，twoLevel：达到二级运动员标准，presolo：预赛一百米男生，solo：决赛一百米男生，team：集体项目，message：查询留言，sport：获取单项体育项目（选项框)，teamSport：获取集体项目（选项框)")
    @RequestMapping(value = "/index/{taketime}",method = RequestMethod.GET)
    public Map<String,Object> getIndex(@PathVariable(value = "taketime")String taketime){
        return showService.getIndex(taketime);
    }
    @ApiOperation(value = "根据项目和性别查询决赛成绩",notes = "project,sex,taketime")
    @RequestMapping(value = "/querySolo",method = RequestMethod.GET)
    public List<SoloVO> getSoloByQuery(@RequestParam("project")String project,@RequestParam("sex")Integer sex,@RequestParam("taketime")String taketime){
        return showService.getSoloVO(project,sex,taketime);
    }
    @ApiOperation(value = "根据校区，项目和性别查询预赛成绩",notes = "campus,project,sex,taketime")
    @RequestMapping(value = "/queryPreSolo",method = RequestMethod.GET)
    public List<PreSoloVO> getSoloByQuery(@RequestParam("campus")String campus, @RequestParam("project")String project, @RequestParam("sex")Integer sex, @RequestParam("taketime")String taketime){
        return showService.getPreSoloVO(campus,project,sex,taketime);
    }
    @ApiOperation(value = "根据校区和项目查询集体项目成绩",notes = "campus,project,taketime")
    @RequestMapping(value = "/queryTeam",method = RequestMethod.GET)
    public List<TeamVO> getTeamVO(@RequestParam("campus")String campus, @RequestParam("project")String project, @RequestParam("sex")Integer sex, @RequestParam("taketime")String taketime){
        return showService.getTeamVO(campus,project,taketime);
    }
    @ApiOperation(value = "根据学号查询",notes = "stunum,taketime")
    @RequestMapping(value = "queryNu",method = RequestMethod.GET)
    public List<QueryVO> getQuery(@RequestParam("stunu")String stunu,@RequestParam("taketime")String taketime){
        return showService.getQuery(stunu,taketime);
    }




}
