package org.wingstudio.sports.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingstudio.sports.dao.TeamMapper;
import org.wingstudio.sports.domain.Team;
import org.wingstudio.sports.service.TeamService;
import org.wingstudio.sports.util.ReturnUtil;

import java.util.Map;

@Service("teamService")
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamMapper teamMapper;
    @Override
    public Map<String, Object> addTeam(Team team) {
        //该专业是否参加该体育项目
        if (teamMapper.isTeamExist(team.getSportid(),team.getProfession())<=0){
            if (teamMapper.insertSelective(team)>0){
                return ReturnUtil.ret(true,"添加成功");
            }else {
                return ReturnUtil.ret(false,"添加失败");
            }
        }else {
            System.out.println(teamMapper.isTeamExist(team.getSportid(),team.getProfession()));
            return ReturnUtil.ret(false,"已参加该体育项目");
        }

    }

    @Override
    public Map<String, Object> updateTeam(Team team) {
        return null;
    }
}
