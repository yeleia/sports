package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.wingstudio.sports.domain.Score;
@Mapper
public interface ScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);
}