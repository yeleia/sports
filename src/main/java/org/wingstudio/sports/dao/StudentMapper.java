package org.wingstudio.sports.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.wingstudio.sports.domain.Student;

import java.util.List;
@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(@Param("id")Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(@Param("id")Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    List<String> getCampus();

    List<String> getClasses();

    List<String> getProfession();

    Student getByNuNa(@Param("stuname") String stuname, @Param("stunum") String stunum);

    List<String> getClassesByCampus(@Param("campus") String campus);
}