package org.wingstudio.sports.service;

import org.wingstudio.sports.domain.PreSolo;

import java.util.Map;

public interface ScoreService {
    /**
     * 添加预赛单项成绩
     * @param preSolo
     * @return
     */
    Map<String,Object> addPreSoloScore(PreSolo preSolo);
}
