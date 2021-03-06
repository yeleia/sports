package org.wingstudio.sports.service;

import org.wingstudio.sports.domain.Team;

import java.util.List;
import java.util.Map;

public interface TeamService {
    /**
     * 添加参赛团队信息
     * @return
     */
    Map<String,Object> addTeam(Team team);
    /**
     * 修改参赛团队信息
     */
    Map<String,Object> updateTeam(Team team);

    /**
     * 删除团队参赛信息
     * @param id
     * @return
     */
    Map<String,Object> deleteTeam(Integer id);

    /**
     * 查询所有团队信息
     * @param tempPage
     * @param pageCapacity
     * @Param time  根据届数查询
     * @return
     */
    Map<String,Object> getTeamList(Integer tempPage, Integer pageCapacity,String time);

    Map<String,Object> getTeam(Integer tempPage, Integer pageCapacity, Team team);

    Map<String,Object> getTeamNoCheck(Integer tempPage, Integer pageCapacity, String currentime);

    Map<String,Object> checkTeam(Integer id);

    Map<String,Object> getTeamNoCheckUp(Integer tempPage, Integer pageCapacity, String currentime);
}
