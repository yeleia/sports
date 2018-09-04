package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.Score;
import org.wingstudio.sports.domain.Team;
import org.wingstudio.sports.domain.TeamScore;

import java.util.List;

@Mapper
public interface TeamScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TeamScore record);

    int insertSelective(TeamScore record);

    TeamScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeamScore record);

    int updateByPrimaryKey(TeamScore record);

    int isTeamScoreExsit(@Param("sportid") Integer sportid, @Param("teamid") Integer teamid, @Param("taketime") String taketime);

    List<TeamScore> getTeamScoreNoCheck(@Param("tempPage")Integer tempPage,@Param("pageCapacity")Integer pageCapacity, @Param("taketime") String taketime);

    int getTeamScoreNoCheckNu(@Param("taketime") String taketime);

    List<TeamScore> getTeamScoreChecked(@Param("tempPage")Integer tempPage,@Param("pageCapacity")Integer pageCapacity, @Param("taketime") String taketime);

    int getTeamScoreCheckNu(@Param("taketime") String taketime);

    List<TeamScore> getTeamBySportId(@Param("sportid") Integer sportid, @Param("taketime") String taketime);

    List<TeamScore> getTeamBySportIdAsc(@Param("sportid") Integer sportid, @Param("taketime") String taketime);

    void updateCheck(@Param("id") Integer id);

    TeamScore getcount( @Param("taketime") String taketime, @Param("sportid") Integer sportid, @Param("teamid") Integer teamid);
}