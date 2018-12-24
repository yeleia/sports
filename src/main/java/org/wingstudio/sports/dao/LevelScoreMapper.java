package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.LevelScore;
@Mapper
public interface LevelScoreMapper {
    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(LevelScore record);

    int insertSelective(LevelScore record);

    LevelScore selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(LevelScore record);

    int updateByPrimaryKey(LevelScore record);

    double selectPreScore();

    double selectScore();
}