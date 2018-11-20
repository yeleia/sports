package org.wingstudio.sports.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wingstudio.sports.domain.Message;
import org.wingstudio.sports.service.MessageService;

import java.util.List;
import java.util.Map;

@Api("展示页面api")
@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    private MessageService messageService;
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

}
