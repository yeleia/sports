package org.wingstudio.sports.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingstudio.sports.dao.PreSoloMapper;
import org.wingstudio.sports.domain.PreSolo;
import org.wingstudio.sports.service.ScoreService;

import java.util.Map;

@Service("scoreService")
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private PreSoloMapper preSoloMapper;
    @Override
    public Map<String, Object> addPreSoloScore(PreSolo preSolo) {

        return null;
    }
}
