package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.PreSolo;
@Mapper
public interface PreSoloMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PreSolo record);

    int insertSelective(PreSolo record);

    PreSolo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PreSolo record);

    int updateByPrimaryKey(PreSolo record);

    int isPreSoloExsit(@Param("sportid") Integer sportid, @Param("contestantid") Integer contestantid,@Param("taketime") String taketime);
}