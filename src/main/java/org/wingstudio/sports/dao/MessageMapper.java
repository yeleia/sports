package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.Message;

import java.util.List;

@Mapper
public interface MessageMapper {
    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    void addMessage(Message message);

    void updateById(@Param("id")Integer id);

    List<Message> getNocheck();

    List<Message> getCheck();
}