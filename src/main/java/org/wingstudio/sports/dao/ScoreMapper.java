package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.Score;

import java.util.List;

@Mapper
public interface ScoreMapper {
    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);

    List<Score> getAllScore(@Param("taketime") String taketime);

    Score getcount(@Param("taketime") String taketime, @Param("sportid") Integer sportid,@Param("contestantid") Integer contestantid);

    int insertAndUpdate(List<Score> scores);

    Score isExsit(@Param("sportid") Integer sportid, @Param("contestantid") Integer contestantid,@Param("taketime") String taketime);

    Score isExsitAndGet(@Param("sportid") Integer sportid, @Param("contestantid") Integer contestantid,@Param("taketime") String taketime);

    String getTop();


    List<Double> getByContestantId(@Param("contestantid") Integer contestantid, @Param("taketime") String taketime);


    void updateById(@Param("presoloscore")Double presoloscore,@Param("id")Integer id);

}