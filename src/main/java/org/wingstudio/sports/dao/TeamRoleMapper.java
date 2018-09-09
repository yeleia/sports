package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.TeamRole;

import java.util.List;
@Mapper
public interface TeamRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TeamRole record);

    int insertSelective(TeamRole record);

    TeamRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeamRole record);

    int updateByPrimaryKey(TeamRole record);

    List<TeamRole> getRoleBySportId(@Param("sportid") Integer sportid,@Param("campus") String campus);

    int isTeamRoleExsit(TeamRole teamRole);

    List<TeamRole> getBySportid(@Param("sportid") Integer sportid);
}