package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.wingstudio.sports.domain.Role;

import java.util.List;
@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int addRoles(List<Role> roleList);

    List<Role> getRoleList(int sportid);
}