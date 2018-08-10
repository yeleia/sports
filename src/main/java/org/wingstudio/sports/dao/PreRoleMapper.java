package org.wingstudio.sports.dao;

import org.wingstudio.sports.domain.PreRole;

public interface PreRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PreRole record);

    int insertSelective(PreRole record);

    PreRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PreRole record);

    int updateByPrimaryKey(PreRole record);
}