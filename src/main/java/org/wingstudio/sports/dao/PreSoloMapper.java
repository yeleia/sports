package org.wingstudio.sports.dao;

import org.wingstudio.sports.domain.PreSolo;

public interface PreSoloMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PreSolo record);

    int insertSelective(PreSolo record);

    PreSolo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PreSolo record);

    int updateByPrimaryKey(PreSolo record);
}