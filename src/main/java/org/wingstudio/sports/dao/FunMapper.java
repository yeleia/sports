package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.Fun;
@Mapper
public interface FunMapper {
    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(Fun record);

    int insertSelective(Fun record);

    Fun selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(Fun record);

    int updateByPrimaryKey(Fun record);
}