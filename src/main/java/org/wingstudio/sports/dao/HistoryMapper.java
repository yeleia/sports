package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.History;

import java.util.List;

@Mapper
public interface HistoryMapper {
    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(History record);

    int insertSelective(History record);

    History selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(History record);

    int updateByPrimaryKey(History record);
    List<History> getAllHistory();
}