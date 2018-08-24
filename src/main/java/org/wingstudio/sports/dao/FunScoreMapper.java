package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.wingstudio.sports.domain.FunScore;
@Mapper
public interface FunScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FunScore record);

    int insertSelective(FunScore record);

    FunScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FunScore record);

    int updateByPrimaryKey(FunScore record);
}