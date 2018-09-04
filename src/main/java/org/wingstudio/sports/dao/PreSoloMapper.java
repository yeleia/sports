package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.VO.PreSoloVO;
import org.wingstudio.sports.domain.PreSolo;

import java.util.List;

@Mapper
public interface PreSoloMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PreSolo record);

    int insertSelective(PreSolo record);

    PreSolo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PreSolo record);

    int updateByPrimaryKey(PreSolo record);

    int isPreSoloExsit(@Param("sportid") Integer sportid, @Param("contestantid") Integer contestantid,@Param("taketime") String taketime);

    List<PreSolo> getPreSoloNoCheck(@Param("tempPage")Integer tempPage,@Param("pageCapacity")Integer pageCapacity, @Param("taketime") String taketime);

    int getPreSoloNoCheckNu(@Param("taketime") String taketime);

    List<PreSolo> getPreSoloChecked(@Param("tempPage")Integer tempPage,@Param("pageCapacity")Integer pageCapacity, @Param("taketime") String taketime);

    int getPreSoloCheckNu(@Param("taketime") String taketime);

    void updateCheck(Integer id);


    List<PreSolo> getPreSoloBySportId(@Param("sportid") Integer sportid,@Param("taketime") String taketime);

    List<PreSolo> getPreSoloBySportIdAsc(@Param("sportid") Integer sportid,@Param("taketime") String taketime);
}