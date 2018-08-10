package org.wingstudio.sports.dao;

import org.wingstudio.sports.domain.Contestant;

public interface ContestantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Contestant record);

    int insertSelective(Contestant record);

    Contestant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Contestant record);

    int updateByPrimaryKey(Contestant record);
}