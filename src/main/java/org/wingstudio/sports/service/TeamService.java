package org.wingstudio.sports.service;

import org.wingstudio.sports.domain.Team;

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
}
