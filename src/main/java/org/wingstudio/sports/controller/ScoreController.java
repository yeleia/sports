package org.wingstudio.sports.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.wingstudio.sports.service.ScoreService;

@RestController
@Api(value = "成绩输入计算api")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

}
