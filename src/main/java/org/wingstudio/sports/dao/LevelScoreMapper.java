package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.wingstudio.sports.domain.LevelScore;
@Mapper
public interface LevelScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LevelScore record);

    int insertSelective(LevelScore record);

    LevelScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LevelScore record);

    int updateByPrimaryKey(LevelScore record);

    double selectPreScore();

    double selectScore();
}