package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.PreSolo;
import org.wingstudio.sports.domain.Solo;

import java.util.List;

@Mapper
public interface SoloMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Solo record);

    int insertSelective(Solo record);

    Solo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Solo record);

    int updateByPrimaryKey(Solo record);

    int isSoloExsit(@Param("contestantid") Integer contestantid,@Param("sportid") Integer sportid, @Param("taketime") String taketime);

    List<Solo> getSoloByCheck(@Param("tempPage")Integer tempPage,@Param("pageCapacity")Integer pageCapacity,@Param("checked")Integer checked,@Param("taketime") String taketime);

    int getPreSoloByCheckNu(@Param("checked")Integer checked,@Param("taketime") String taketime);


    List<Solo> getSoloBySportId(@Param("sportid") Integer sportid,@Param("taketime") String taketime);


    void updateCheck(@Param("id") Integer id,@Param("checked")Integer checked);

    Solo select(@Param("contestantid") Integer contestantid,@Param("sportid") Integer sportid,@Param("taketime") String taketime);

    Solo getByConId(@Param("contestantid") Integer contestantid);

    String getTop();

    List<String> getCampus(@Param("taketime") String taketime);

    List<Solo> getSoloBySportIdDesc(@Param("sportid") Integer sportid, @Param("taketime") String taketime, @Param("campus") String campus);

    List<Solo> getSoloBySportIdAsc(@Param("sportid") Integer sportid,@Param("taketime") String taketime, @Param("campus") String campus);

    List<Solo> getSport(@Param("sportid") Integer sportid,@Param("taketime") String taketime);
}