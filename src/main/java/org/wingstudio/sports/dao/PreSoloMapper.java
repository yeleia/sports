package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.VO.PreSoloVO;
import org.wingstudio.sports.domain.PreSolo;

import java.util.List;

@Mapper
public interface PreSoloMapper {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(PreSolo record);

    int insertSelective(PreSolo record);

    PreSolo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PreSolo record);

    int updateByPrimaryKey(PreSolo record);

    int isPreSoloExsit(@Param("sportid") Integer sportid, @Param("contestantid") Integer contestantid,@Param("taketime") String taketime);

    List<PreSolo> getPreSoloByCheck(@Param("tempPage")Integer tempPage,@Param("pageCapacity")Integer pageCapacity,@Param("checked")Integer checked, @Param("taketime") String taketime);

    int getPreSoloByCheckNu(@Param("checked")Integer checked,@Param("taketime") String taketime);

    void updateCheck(@Param("id") Integer id,@Param("checked")Integer checked);


    List<PreSolo> getPreSoloBySportIdDesc(@Param("sportid") Integer sportid,@Param("taketime") String taketime,@Param("campus")String campus);

    List<PreSolo> getPreSoloBySportIdAsc(@Param("sportid") Integer sportid,@Param("taketime") String taketime,@Param("campus")String campus);

    List<PreSolo> getAllSolo(@Param("taketime") String taketime);


    PreSolo getByConId(@Param("contestantid") Integer contestantid);

    String getTop();

    List<PreSolo> getBySportId(@Param("sportid") Integer sportid, @Param("taketime")String taketime,@Param("campus")String campus);

    List<String> getCampus(@Param("taketime")String taketime);

    List<PreSolo> getSport(@Param("sportid") Integer sportid,@Param("taketime") String taketime);

    List<String> getClasses(@Param("campus") String campus,@Param("taketime") String taketime);


    List<PreSolo> getBySportIdAsc(@Param("sportid") Integer sportid,@Param("taketime") String taketime);

    List<PreSolo> getBySportIdDesc(@Param("sportid") Integer sportid,@Param("taketime") String taketime);
}