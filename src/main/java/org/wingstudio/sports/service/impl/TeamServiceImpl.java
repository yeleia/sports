package org.wingstudio.sports.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingstudio.sports.constant.Common;
import org.wingstudio.sports.dao.TeamMapper;
import org.wingstudio.sports.domain.Team;
import org.wingstudio.sports.service.TeamService;
import org.wingstudio.sports.util.ReturnUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("teamService")
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamMapper teamMapper;
    @Override
    public Map<String, Object> addTeam(Team team) {
        //该专业是否参加该体育项目,这里注意届数
        if (teamMapper.isTeamExist(team.getSportid(),team.getProfession(),team.getCurrentime())<=0){
            team.setChecked(Common.NOCHECK);
            if (teamMapper.insertSelective(team)>0){
                return ReturnUtil.ret(true,"添加成功");
            }else {
                return ReturnUtil.ret(false,"添加失败");
            }
        }else {
            return ReturnUtil.ret(false,"已参加该体育项目");
        }

    }

    @Override
    public Map<String, Object> updateTeam(Team team) {
        team.setChecked(Common.UPCHECK);
        Team team1=teamMapper.selectByPrimaryKey(team.getId());
        //是否修改项目和专业
        if (team1.getSportid().equals(team.getSportid())&&team1.getProfession().equals(team.getProfession())){
            if (teamMapper.updateByPrimaryKeySelective(team)>0){
                return ReturnUtil.ret(true,"修改成功");
            }else {
                return ReturnUtil.ret(false,"修改失败");
            }
        }else {
            //查询该集体是否参加该项目
            if (teamMapper.isTeamExist(team.getSportid(),team.getProfession(),team.getCurrentime())<1){
                if (teamMapper.updateByPrimaryKeySelective(team)>0){
                    return ReturnUtil.ret(true,"修改成功");
                }else {
                    return ReturnUtil.ret(false,"修改失败");
                }
            }else {
                return ReturnUtil.ret(false,"已参加改体育项目");
            }
        }

    }

    @Override
    public Map<String, Object> deleteTeam(Integer id) {
        if (teamMapper.deleteByPrimaryKey(id)>0){
            return ReturnUtil.ret(true,"删除成功");
        }else {
            return ReturnUtil.ret(false,"删除失败");
        }
    }

    @Override
    public Map<String,Object> getTeamList(Integer tempPage, Integer pageCapacity,String currentime) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        Integer checked=Common.CHECKED;
        resultMap.put("team",teamMapper.getTeamList(tempPage*pageCapacity,pageCapacity,currentime,checked));
        resultMap.put("count",teamMapper.count(currentime,checked));
        return resultMap;
    }
    @Override
    public Map<String,Object> getTeam(Integer tempPage, Integer pageCapacity,Team team){
        Map<String,Object> resultMap=new LinkedHashMap<>();
        List<Team> teams=teamMapper.getTeam(team.getCurrentime(),team.getClasses(),team.getProfession(),tempPage*pageCapacity,pageCapacity);
        resultMap.put("team",teams);
        resultMap.put("count",teams.size());
        return resultMap;
    }
    @Override
    public Map<String,Object> getTeamNoCheck(Integer tempPage, Integer pageCapacity,String currentime) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        Integer checked=Common.NOCHECK;
        resultMap.put("team",teamMapper.getTeamList(tempPage*pageCapacity,pageCapacity,currentime,checked));
        resultMap.put("count",teamMapper.count(currentime,checked));
        return resultMap;
    }
    @Override
    public Map<String,Object> checkTeam(Integer id){
        Integer checked=Common.CHECKED;
        if (teamMapper.updateTeam(id,checked)>0){
            return ReturnUtil.ret(true,"审核通过");
        }else {
            return ReturnUtil.ret(false,"审核失败");
        }
    }
    @Override
    public Map<String,Object> getTeamNoCheckUp(Integer tempPage, Integer pageCapacity,String currentime) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        Integer checked=Common.UPCHECK;
        resultMap.put("team",teamMapper.getTeamList(tempPage*pageCapacity,pageCapacity,currentime,checked));
        resultMap.put("count",teamMapper.count(currentime,checked));
        return resultMap;
    }


}
