package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.VO.SportVO;
import org.wingstudio.sports.domain.Sport;

import java.util.List;

@Mapper
public interface SportMapper {
    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(Sport record);

    int insertSelective(Sport record);

    Sport selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(Sport record);

    int updateByPrimaryKey(Sport record);

    int sportIsExist(@Param("project") String project, @Param("sex") Integer sex);

    List<Sport> getSportList(@Param("tempPage") Integer tempPage, @Param("pageCapacity") Integer pageCapacity);

    Integer countSportList();

    List<Sport> getSportIdList();

    List<SportVO> getSportName(@Param("sex") Integer sex);

    String getSportNameById(@Param("id") Integer id);

    List<Sport> getSportsBySex(@Param("tempPage") Integer tempPage, @Param("pageCapacity") Integer pageCapacity, @Param("sex")Integer sex);

    int getSportCount(@Param("sex")Integer sex);

    List<Sport> getSportBySex(@Param("sex")Integer sex);

   Sport selectByProSex(@Param("project")String project, @Param("sex")Integer sex);

    String  getTeamTop(@Param("sex")Integer sex);


    List<Sport> selectByPro(@Param("project")String project);

    String getSportSort(@Param("id") Integer id);

    Object getSportTeam();

}