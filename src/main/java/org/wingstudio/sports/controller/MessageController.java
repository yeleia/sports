package org.wingstudio.sports.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wingstudio.sports.domain.Message;
import org.wingstudio.sports.service.MessageService;

import java.util.List;
import java.util.Map;

@Api(value = "留言api")
@RestController

@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @ApiOperation(value = "审核留言",notes = "id")
    @RequestMapping(value = "/checkMessage/{id}",method = RequestMethod.POST)
    public Map<String,Object> checkMessage(@PathVariable("id")Integer id){
        return messageService.checkMessage(id);
    }
    @ApiOperation(value = "删除该留言",notes = "id")
    @RequestMapping(value = "/deleteMessage/{id}",method = RequestMethod.DELETE)
    public Map<String,Object> deleteMessage(@PathVariable("id")Integer id){
        return messageService.deleteMessage(id);
    }
    @ApiOperation(value = "查询未审核的留言")
    @RequestMapping(value = "/getNocheck",method = RequestMethod.GET)
    public List<Message> getNocheck(){
        return messageService.getNocheck();
    }
    @ApiOperation(value = "查询已经审核的留言")
    @RequestMapping(value = "/getCheck",method = RequestMethod.GET)
    public List<Message> getCheck(){
        return messageService.getCheck();
    }
}
