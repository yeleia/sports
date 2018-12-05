package org.wingstudio.sports.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wingstudio.sports.domain.LevelScore;
import org.wingstudio.sports.domain.PreSolo;
import org.wingstudio.sports.domain.Solo;
import org.wingstudio.sports.domain.TeamScore;
import org.wingstudio.sports.service.ScoreService;
import org.wingstudio.sports.util.Page;

import java.util.Map;

@RestController
@Api(value = "成绩输入计算api")
@RequestMapping("/add")

public class ScoreController {
    @Autowired
    private ScoreService scoreService;
    @ApiOperation(value = "添加预赛单项成绩",notes = "sportid,contestantid,gate,remark,group,score,taketime")
    @RequestMapping(value = "/addPreSoloScore",method = RequestMethod.POST)
    public Map<String,Object> addPreSoloScore(@ModelAttribute PreSolo preSolo){
        return scoreService.addPreSoloScore(preSolo);
    }
    @ApiOperation(value = "修改预赛单项成绩",notes = "id,sportid,contestantid,gate,remark,group,score,taketime")
    @RequestMapping(value = "/updatePreSoloScore",method = RequestMethod.POST)
    public Map<String,Object> updatePreSoloScore(@ModelAttribute PreSolo preSolo){
        return scoreService.updatePreSoloScore(preSolo);
    }
    @ApiOperation(value = "删除预赛成绩",notes = "id,checked")
    @RequestMapping(value = "/deletePreSoloScore/{id}",method = RequestMethod.DELETE)
    public Map<String,Object> deletePreSoloScore(@PathVariable("id")Integer id,@RequestParam("checked")Integer checked){
        return scoreService.deletePreSoloScore(id,checked);

    }
    @ApiOperation(value = "查询没有审核的预赛单赛成绩",notes = "tempPage,pageCapacity,taketime")
    @RequestMapping(value = "/getPreSoloNoCheck",method = RequestMethod.GET)
    public Map<String,Object> getPreSoloNoCheck(@ModelAttribute Page page, @RequestParam("taketime") String taketime){
        return scoreService.getPreSoloNoCheck(page.getTempPage(),page.getPageCapacity(),taketime);
    }
    @ApiOperation(value = "查询已经审核的预赛单项",notes = "")
    @RequestMapping(value = "/getPreSoloChecked",method = RequestMethod.GET)
    public Map<String,Object> getPreSoloChecked(@ModelAttribute Page page,@RequestParam("taketime")String taketime){
        return scoreService.getPreSoloChecked(page.getTempPage(),page.getPageCapacity(),taketime);
    }
    @ApiOperation(value = "添加决赛单项成绩",notes = "sportid,contestantid,gate,remark,score,taketime")
    @RequestMapping(value = "/addSoloScore",method = RequestMethod.POST)
    public Map<String,Object> addSoloScore(@ModelAttribute Solo solo){
        return scoreService.addSoloScore(solo);
    }
    @ApiOperation(value = "更新决赛单项成绩",notes = "id,sportid,contestantid,gate,remark,score,taketime")
    @RequestMapping(value = "/updateSoloScore",method = RequestMethod.POST)
    public Map<String,Object> updateSoloScore(@ModelAttribute Solo solo){
        return scoreService.updateSoloScore(solo);
    }
    @ApiOperation(value = "删除决赛单项成绩",notes = "id,checked")
    @RequestMapping(value = "/deleteSoloScore/{id}",method = RequestMethod.DELETE)
    public Map<String,Object> deleteSoloScore(@PathVariable("id")Integer id, @RequestParam("checked")Integer checked){
        return scoreService.deleteSoloScore(id,checked);
    }
    @ApiOperation(value = "查询决赛未审核的成绩",notes = "tempPage,pageCapacity,taketime")
    @RequestMapping(value = "/getSoloScoreNoCheck",method = RequestMethod.GET)
    public Map<String,Object> getSoloScoreNoCheck(@ModelAttribute Page page,@RequestParam("taketime")String taketime){
        return scoreService.getSoloScoreNoCheck(page.getTempPage(),page.getPageCapacity(),taketime);
    }
    @ApiOperation(value = "查询决赛已经审核过的成绩",notes = "tempPage,pageCapacity,taketime")
    @RequestMapping(value = "getSoloScoreCheck",method = RequestMethod.GET)
    public Map<String,Object> getSoloScoreCheck(@ModelAttribute Page page,@RequestParam("taketime")String taketime){
        return scoreService.getSoloScoreCheck(page.getTempPage(),page.getPageCapacity(),taketime);
    }
    @ApiOperation(value = "添加集体项目成绩",notes = "sportid,teamid,score,taketime")
    @RequestMapping(value = "/addTeamScore",method = RequestMethod.POST)
    public Map<String,Object> addTeamScore(@ModelAttribute TeamScore teamScore){
        return scoreService.addTeamScore(teamScore);
    }
    @ApiOperation(value = "修改集体项目的成绩",notes = "id,sportid,teamid,score,taketime")
    @RequestMapping(value = "/updateTeamScore",method = RequestMethod.POST)
    public Map<String,Object> updateTeamScore(@ModelAttribute TeamScore teamScore){
        return scoreService.updateTeamScore(teamScore);
    }
    @ApiOperation(value = "删除集项目的成绩",notes = "id,checked")
    @RequestMapping(value = "/deleteTeamScore/{id}",method = RequestMethod.DELETE)
    public Map<String,Object> deleteTeamScore(@PathVariable("id")Integer id,@RequestParam("checked")Integer checked){
        return scoreService.deleteTeamScore(id,checked);
    }
    @ApiOperation(value = "查询集体项目未审核成绩",notes = "tempPage,pageCapacity,taketime")
    @RequestMapping(value = "/getTeamScoreNoCheck",method = RequestMethod.GET)
    public Map<String,Object> getTeamScoreNoCheck(@ModelAttribute Page page,@RequestParam("taketime")String taketime){
        return scoreService.getTeamScoreNoCheck(page.getTempPage(),page.getPageCapacity(),taketime);
    }
    @ApiOperation(value = "查询集体项目已经审核的",notes = "tempPage,pageCapacity,taketime")
    @RequestMapping(value = "/getTeamScoreCheck",method = RequestMethod.GET)
    public Map<String,Object> getTeamScoreCheck(@ModelAttribute Page page ,@RequestParam("taketime")String taketime){
        return scoreService.getTeamScoreCheck(page.getTempPage(),page.getPageCapacity(),taketime);
    }
    @ApiOperation(value = "审核预赛成绩",notes = "id")
    @RequestMapping(value = "/checkPreSolo/{id}",method = RequestMethod.POST)
    public Map<String,Object> checkPreSolo(@PathVariable("id")Integer id){
        return scoreService.checkPreSolo(id);
    }
    @ApiOperation(value = "审核决赛成绩",notes = "id")
    @RequestMapping(value = "/checkSolo/{id}",method = RequestMethod.POST)
    public Map<String,Object> checkSolo(@PathVariable("id")Integer id){
        return scoreService.checkSolo(id);
    }
    @ApiOperation(value = "审核集体项目成绩",notes = "id")
    @RequestMapping(value = "/checkTeamScore/{id}",method = RequestMethod.POST)
    public Map<String,Object> checkTeamScore(@PathVariable("id")Integer id){
        return scoreService.checkTeamScore(id);
    }
    @ApiOperation(value = "计算单项总分",notes = "再审核，更新，删除页面页面添加一个按钮，刷新成绩,taketime")
    @RequestMapping(value = "/countAll",method = RequestMethod.GET)
    public Map<String,Object> countAll(@PathVariable("taketime")String taketime){
        return scoreService.countAll(taketime);
    }
    @ApiOperation(value = "更新二级运动员和破记录加分规则",notes = "id,twolevel,recordlevel")
    @RequestMapping(value = "updateTwoScore",method = RequestMethod.POST)
    public Map<String,Object> updateTwoScore(@ModelAttribute LevelScore levelScore){
        return scoreService.updateTwoScore(levelScore);
    }



}