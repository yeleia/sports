package org.wingstudio.sports.service;


import org.wingstudio.sports.domain.Sport;
import org.wingstudio.sports.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 登陆
     * @param user
     * @param request
     * @return
     */
    Map<String,Object> login(User user, HttpServletRequest request);

    /**
     * 添加操作
     * 返回0，该项目已经存在
     * 返回1，添加成功
     * 返回2, 成绩格式错误
     * @param sport
     * @return
     */
    Map<String,Object> addSport(Sport sport);
    /**
     * 修改操作
     * @param sport
     * @return
     * true，更新成功
     * false,更新失败
     */
    Map<String,Object> updateSport(Sport sport);

    /**
     * 获得所有的体育项目信息
     * @return
     * @param tempPage
     * @param pageCapacity
     */
    List<Sport> getSportList(Integer tempPage, Integer pageCapacity);

    Integer countSportList();
}
