package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.User;
@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    //自定义
    User getByNamePass(User user);
}