package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.Contestant;
import org.wingstudio.sports.domain.Team;

import java.util.List;

@Mapper
public interface TeamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Team record);

    int insertSelective(Team record);

    Team selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Team record);

    int updateByPrimaryKey(Team record);

    int isTeamExist(@Param("sportid") Integer sportid, @Param("profession") String profession,@Param("currentime")String currentime);

    List<Team> getTeamList(@Param("tempPage")Integer tempPage,@Param("pageCapacity")Integer pageCapacity,@Param("currentime") String currentime);

    int count(@Param("currentime") String currentime);

    List<Team> getByClasses(@Param("classes") String classes, @Param("currentime") String currentime);
}