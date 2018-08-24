package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.wingstudio.sports.domain.TeamScore;
@Mapper
public interface TeamScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TeamScore record);

    int insertSelective(TeamScore record);

    TeamScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeamScore record);

    int updateByPrimaryKey(TeamScore record);
}