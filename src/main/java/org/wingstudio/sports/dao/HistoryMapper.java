package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.wingstudio.sports.domain.History;

import java.util.List;

@Mapper
public interface HistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(History record);

    int insertSelective(History record);

    History selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(History record);

    int updateByPrimaryKey(History record);
    List<History> getAllHistory();
}