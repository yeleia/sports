package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;
import org.wingstudio.sports.domain.PreRole;

import java.util.List;

@Mapper
public interface PreRoleMapper {
    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(PreRole record);

    int insertSelective(PreRole record);

    PreRole selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(PreRole record);

    int updateByPrimaryKey(PreRole record);


    int updatePreRole(PreRole role);

    List<PreRole> getPreRoleList(@Param("sportid")Integer sportId);

    int isexist(PreRole role);

    List<PreRole> getPreRoleBySportId(@Param("sportid") Integer sportid,@Param("campus")String campus);

    Double getScoreByRank(@Param("sportid") Integer sportid,@Param("campus")String campus,@Param("rank")Integer rank);
}