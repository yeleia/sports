package org.wingstudio.sports.service;


import io.swagger.models.auth.In;
import org.wingstudio.sports.domain.*;

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
    /**
     * 添加集体项目规则
     *
     */
    Map<String,Object> addTeamRole(TeamRole teamRole);

    /**\
     * 修改集体项目规则
     * @param teamRole
     * @return
     */
    Map<String,Object> updateTeamRole(TeamRole teamRole);

    /**
     * 删除集体项目规则
     * @param id
     * @return
     */
    Map<String,Object> deleteTeamRole(Integer id);

    /**
     * 查询该集体项目的规则
     * @param sportid
     * @return
     */
    List<TeamRole> getTeamRoleList(Integer sportid);

    /**
     * 添加新一届运动会
     * @param history
     * @return
     */
    Map<String,Object> addSportMeet(History history);

    /**
     * 修改运动会
     * @param history
     * @return
     */
    Map<String,Object> updateSportMeet(History history);

    /**
     * 删除历史记录
     * @param id
     * @return
     */
    Map<String,Object> delSportMeet(Integer id);

    /**
     * 获取所有历史纪录
     * @return
     */
    List<History> getAllSportMeet();

    /**
     * 查询所有参赛学生,
     * @return
     */
    Map<String,Object> getContantList(Integer tempPage, Integer pageCapacity,String time);
    /**
     * 查询所有未审核参赛学生
     *
     */
    Map<String,Object> getContantListNoCheck(Integer tempPage, Integer pageCapacity,String time);

    /**
     * 审核添加和修改
     * @param id
     * @return
     */
    Map<String,Object> checkContestant(Integer id);

    /**
     * 获取申请修改未审核的信息
     * @param tempPage
     * @param pageCapacity
     * @param currentime
     * @return
     */
    Map<String,Object> getContestantUp(Integer tempPage, Integer pageCapacity, String currentime);

    /**
     * 根据性别查询体育项目
     * @param tempPage
     * @param pageCapacity
     * @param sex
     * @return
     */
    List<Sport> getSportBySex(Integer tempPage, Integer pageCapacity, Integer sex);

    /**
     * 根据性别获得体育项目计数
     * @param sex
     * @return
     */
    int getSportCount(Integer sex);

    /**
     * 添加届数
     * @param history
     * @return
     */

    Map<String,Object> addHistory(History history);

    /**
     * 修改主题
     * @param history
     * @return
     */
    Map<String,Object> updateTheme(History history);

    /**
     * 删除届数
     * @param id
     * @return
     */
    Map<String,Object> deleteHistory(Integer id);

    /**
     * 获取所有届数
     * @return
     */
    List<History> getAllHistory();
}
