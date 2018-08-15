package org.wingstudio.sports.service;

import org.wingstudio.sports.domain.Contestant;

import java.util.Map;

public interface StudentService {
    /**
     * 学生报名参加某项项目
     * @param contestant
     * @return
     */
    Map<String,Object> addContestant(Contestant contestant);
}
