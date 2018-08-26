package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import org.wingstudio.sports.domain.PreRole;

import java.util.List;

@Mapper
public interface PreRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PreRole record);

    int insertSelective(PreRole record);

    PreRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PreRole record);

    int updateByPrimaryKey(PreRole record);


    int updatePreRole(PreRole role);

    List<PreRole> getPreRoleList(Integer sportId);

    int isexist(PreRole role);
}