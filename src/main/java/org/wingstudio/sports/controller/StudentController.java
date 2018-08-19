package org.wingstudio.sports.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wingstudio.sports.domain.Contestant;
import org.wingstudio.sports.service.StudentService;
import org.wingstudio.sports.util.Page;

import java.util.List;
import java.util.Map;

@Api("学生报名操作api")
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @ApiOperation(value ="添加参赛信息",notes = "添加参赛者信息")
    @RequestMapping(value = "/addContestant",method = RequestMethod.POST)
    public Map<String,Object> addContestant(@ModelAttribute Contestant contestant){
        return studentService.addContestant(contestant);
    }
    @ApiOperation(value = "修改参赛者信息",notes = "修改参赛者信息")
    @RequestMapping(value = "/updateContestant",method = RequestMethod.POST)
    public Map<String,Object> updateContestant(@ModelAttribute Contestant contestant){
        return studentService.updateContestant(contestant);
    }
    @ApiOperation(value = "删除参赛者信息",notes = "删除参赛者信息")
    @RequestMapping(value = "/deleteContestant/{id}",method = RequestMethod.DELETE)
    public Map<String,Object> deleteContestant(@PathVariable("id")Integer id){
        return studentService.deleteContestant(id);
    }
    @ApiOperation(value = "查询参赛者信息列表",notes = "查询参赛者信息列表,含分页")
    @RequestMapping(value = "/getContestantList",method = RequestMethod.GET)
    public List<Contestant> getContestantList(@ModelAttribute Page page){
        return studentService.getContantList(page.getTempPage(),page.getPageCapacity());
    }
    @ApiOperation(value = "根据学号查询改参赛者信息",notes = "根据学号查询改参赛者信息")
    @RequestMapping(value = "/getContestantByNum/{stunumber}",method = RequestMethod.GET)
    public List<Contestant> getContestantByNum(@PathVariable("stunumber")String stunumber){
        return studentService.getContestantByNum(stunumber);
    }
}
