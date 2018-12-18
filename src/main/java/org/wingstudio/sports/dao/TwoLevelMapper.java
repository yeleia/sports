package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.TwoLevel;

import java.util.List;

@Mapper
public interface TwoLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TwoLevel record);

    int insertSelective(TwoLevel record);

    TwoLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TwoLevel record);

    int updateByPrimaryKey(TwoLevel record);

    TwoLevel getBySportIdConsId(@Param("sportid") Integer sportid,@Param("contestantid") Integer contestantid, @Param("taketime") String taketime);

    TwoLevel getBySportIdConsIds(@Param("sportid") Integer sportid,@Param("contestantid") Integer contestantid, @Param("taketime") String taketime);

    List<TwoLevel> getByTaketime(@Param("taketime") String taketime);

    int isExsit(TwoLevel twoLevel);

}