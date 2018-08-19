package org.wingstudio.sports.service;

import org.wingstudio.sports.domain.Contestant;

import java.util.List;
import java.util.Map;

public interface StudentService {
    /**
     * 学生报名参加某项项目
     * @param contestant
     * @return
     */
    Map<String,Object> addContestant(Contestant contestant);

    /**
     * 修改学生报名信息
     * @param contestant
     * @return
     */
    Map<String,Object> updateContestant(Contestant contestant);

    /**
     * 删除学生信息
     * @param id
     * @return
     */
    Map<String,Object> deleteContestant(Integer id);

    /**
     * 查询所有参赛学生
     * @return
     */
    List<Contestant> getContantList(Integer tempPage, Integer pageCapacity);

    /**
     * 根据学号查询参赛者信息
     * @param stuNum
     * @return
     */
    List<Contestant> getContestantByNum(String stuNum);
}