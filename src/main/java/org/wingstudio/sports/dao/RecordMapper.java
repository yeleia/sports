package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.Record;
import org.wingstudio.sports.domain.TwoLevel;

import java.util.List;

@Mapper
public interface RecordMapper {
    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

    Record getBySportIdConsId(@Param("sportid") Integer sportid, @Param("contestantid") Integer contestantid, @Param("taketime") String taketime);

    Record getBySportIdConsIds(@Param("sportid") Integer sportid, @Param("contestantid") Integer contestantid, @Param("taketime") String taketime);

    List<Record> getByTaketime(@Param("taketime") String taketime);

    int isExsit(Record record);


    void deleteAll(@Param("taketime") String taketime,@Param("mark")Integer mark);
}