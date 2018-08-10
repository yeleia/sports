package org.wingstudio.sports.dao;

import org.wingstudio.sports.domain.TeamScore;

public interface TeamScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TeamScore record);

    int insertSelective(TeamScore record);

    TeamScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeamScore record);

    int updateByPrimaryKey(TeamScore record);
}