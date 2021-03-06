package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.TwoLevel;

import java.util.List;

@Mapper
public interface TwoLevelMapper {
    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(TwoLevel record);

    int insertSelective(TwoLevel record);

    TwoLevel selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(TwoLevel record);

    int updateByPrimaryKey(TwoLevel record);

    TwoLevel getBySportIdConsId(@Param("sportid") Integer sportid,@Param("contestantid") Integer contestantid, @Param("taketime") String taketime);

    TwoLevel getBySportIdConsIds(@Param("sportid") Integer sportid,@Param("contestantid") Integer contestantid, @Param("taketime") String taketime);

    List<TwoLevel> getByTaketime(@Param("taketime") String taketime);

    int isExsit(TwoLevel twoLevel);

    void deleteAll(@Param("taketime") String taketime,@Param("mark") Integer mark);
}