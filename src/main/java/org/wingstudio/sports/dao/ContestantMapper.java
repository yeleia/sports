package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.Contestant;

import java.util.List;

@Mapper
public interface ContestantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Contestant record);

    int insertSelective(Contestant record);

    Contestant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Contestant record);

    int updateByPrimaryKey(Contestant record);


    int contestantIsExist(@Param("sportid") Integer sportid, @Param("stunumber") String stunumber,@Param("currentime") String currentime);

    List<Contestant> getContestList(@Param("tempPage")Integer tempPage,@Param("pageCapacity")Integer pageCapacity,@Param("currentime") String currentime);

    List<Contestant> getContestantByNum(@Param("stunumber") String stunumber,@Param("currentime") String currentime);

    int count(@Param("currentime") String currentime);


    List<Contestant> getByClasses(@Param("classes") String classes, @Param("currentime") String currentime);

    List<Contestant> getByCamSpo(@Param("campus") String campus, @Param("sportId") Integer sportId,@Param("currentime") String currentime);

}