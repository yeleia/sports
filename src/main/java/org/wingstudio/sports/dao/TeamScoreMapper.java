package org.wingstudio.sports.dao;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.Contestant;
import org.wingstudio.sports.domain.Score;
import org.wingstudio.sports.domain.Team;
import org.wingstudio.sports.domain.TeamScore;

import java.util.List;

@Mapper
public interface TeamScoreMapper {
    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(TeamScore record);

    int insertSelective(TeamScore record);

    TeamScore selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(TeamScore record);

    int updateByPrimaryKey(TeamScore record);

    TeamScore isTeamScoreExsit(@Param("sportid") Integer sportid, @Param("teamid") Integer teamid, @Param("taketime") String taketime);

    List<TeamScore> getTeamScoreByCheck(@Param("tempPage")Integer tempPage, @Param("pageCapacity")Integer pageCapacity, @Param("checked")Integer checked, @Param("taketime") String taketime);

    int getTeamScoreByCheckNu(@Param("checked")Integer checked,@Param("taketime") String taketime);

    List<TeamScore> getTeamBySportId(@Param("sportid") Integer sportid, @Param("taketime") String taketime);

    List<TeamScore> getTeamBySportIdAsc(@Param("sportid") Integer sportid, @Param("taketime") String taketime);

    void updateCheck(@Param("id") Integer id,@Param("checked")Integer checked);

    TeamScore getcount( @Param("taketime") String taketime, @Param("sportid") Integer sportid, @Param("teamid") Integer teamid);

    TeamScore getByTeamIdSpoid(@Param("teamid") Integer teamid);

    String getTop();

    List<String> getCampus(@Param("taketime")String taketime);

    List<Double> getByClasses(@Param("classes") String classes,@Param("taketime") String taketime);
}