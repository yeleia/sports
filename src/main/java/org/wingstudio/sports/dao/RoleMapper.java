package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.Role;

import java.util.List;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);


    List<Role> getRoleList(@Param("sportid")Integer sportid);

    int isExist(Role role);

    List<Role> getRoleBySportId(@Param("sportid") Integer sportid,@Param("campus") String campus);

    Double getScoreByRank(@Param("sportid") Integer sportid, @Param("campus") String campus,@Param("rank") int rank);
}