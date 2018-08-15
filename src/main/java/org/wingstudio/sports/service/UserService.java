package org.wingstudio.sports.service;


import org.wingstudio.sports.domain.PreRole;
import org.wingstudio.sports.domain.Role;
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

    /**
     * 获得所有体育项目的总数
     * @return
     */
    Integer countSportList();

    /**
     * 添加预赛加分规则
     * @param role
     * role传入的是json字符串
     */
    Map<String, Object> addPreRoles(String role);

    /**
     * 修改预赛加分规则，只能修改分数
     * @param role
     * @return
     */
    Map<String, Object> updatePreRole(PreRole role);

    /**
     * 删除预赛加分规则
     * @param id
     * @return
     */
    Map<String, Object> deletePreRole(Integer id);

    /**
     * 查询该项目所有的加分规则
     * @param sportId
     * @return
     */
    List<PreRole> getPreRoleList(Integer sportId);



    /**
     * 获取所有体育项目id,便于添加所有加分规则
     * @return
     */
    List<Sport> getSportId();

    /**
     * 添加决赛规则
     * @param role
     * @return
     */
    Map<String,Object> addRoles(String role);

    /**
     * 更新决赛规则
     * @param role
     * @return
     */
    Map<String,Object> updateRole(Role role);

    /**
     * 删除决赛规则
     * @param id
     * @return
     */
    Map<String,Object> deleteRole(int id);

    /**
     * 查询该体育项目的决赛规则
     * @param sportid
     * @return
     */
    List<Role> getRoleList(int sportid);
}
