package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.Classes;

import java.util.List;

@Mapper
public interface ClassesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);

    void deleteByClasses(@Param("classes") String classes,@Param("taketime")String taketime);

    List<Classes> getAllClasses(@Param("taketime") String taketime);

    Classes getByClasses(@Param("classes") String classes, @Param("taketime") String taketime);
}