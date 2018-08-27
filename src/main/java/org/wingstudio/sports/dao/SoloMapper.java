package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.Solo;
@Mapper
public interface SoloMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Solo record);

    int insertSelective(Solo record);

    Solo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Solo record);

    int updateByPrimaryKey(Solo record);

    int isSoloExsit(@Param("contestantid") Integer contestantid,@Param("sportid") Integer sportid, @Param("taketime") String taketime);
}