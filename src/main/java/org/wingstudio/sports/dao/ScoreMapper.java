package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.Score;

import java.util.List;

@Mapper
public interface ScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);

    List<Score> getAllScore(@Param("taketime") String taketime);

    Score getcount(@Param("currentime") String currentime, @Param("sportid") Integer sportid,@Param("contestantid") Integer contestantid);

}