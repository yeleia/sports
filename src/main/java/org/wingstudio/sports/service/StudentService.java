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
     * 学生审核修改报名信息
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
     * 根据学号查询参赛者信息
     * @param stuNum
     * @return
     */
    List<Contestant> getContestantByNum(String stuNum,String time);

    /**
     * 获取全校校区，学院，专业信息
     * @return
     */
    Map<String,Object> getCCP();
}
