package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.FunScore;
@Mapper
public interface FunScoreMapper {
    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(FunScore record);

    int insertSelective(FunScore record);

    FunScore selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(FunScore record);

    int updateByPrimaryKey(FunScore record);
}