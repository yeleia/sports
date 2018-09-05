package org.wingstudio.sports.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wingstudio.sports.domain.Message;
import org.wingstudio.sports.service.MessageService;

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
}
