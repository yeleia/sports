package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.Contestant;
@Mapper
public interface ContestantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Contestant record);

    int insertSelective(Contestant record);

    Contestant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Contestant record);

    int updateByPrimaryKey(Contestant record);

    int contestantIsExist(@Param("sportid") Integer sportid,@Param("stunumber") String stunumber);
}