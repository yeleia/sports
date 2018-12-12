package org.wingstudio.sports.service.impl;

import io.swagger.models.auth.In;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingstudio.sports.dao.*;
import org.wingstudio.sports.domain.*;
import org.wingstudio.sports.service.UserService;
import org.wingstudio.sports.util.CheckUtil;
import org.wingstudio.sports.util.ReturnUtil;
import org.wingstudio.sports.util.TimeRegexUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SportMapper sportMapper;
    @Autowired
    private PreRoleMapper preRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private TeamRoleMapper teamRoleMapper;
    @Autowired
    private HistoryMapper historyMapper;
    @Autowired
    private ContestantMapper contestantMapper;
    @Override
    public Map<String, Object> login(User user, HttpServletRequest request) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        if (userMapper.getByNamePass(user)!=null){
            HttpSession session=request.getSession();
            session.setAttribute("token",session.getId());
            resultMap.put("status","200");
            resultMap.put("message","登陆成功");
            resultMap.put("token",session.getAttribute("token"));
        }else {
            resultMap.put("status",500);
            resultMap.put("message","登陆失败");
        }
        return resultMap;
    }

    @Override
    public Map<String,Object> addSport(Sport sport) {
        //输入值格式检查
        if (sport.getSortrule()==0||(sport.getSortrule()==1&&TimeRegexUtil.TimeRegex(sport.getInmax(),sport.getInmin(),sport.getRecord(),sport.getTwolevel()))) {
            //限制输入检查
            if (CheckUtil.incheck(sport.getSortrule(), sport.getInmax(), sport.getInmin())) {
                //体育项目是否存在
                if (sportMapper.sportIsExist(sport.getProject(), sport.getSex()) > 0) {
                    return ReturnUtil.ret(false, "该体育项目已经存在");
                } else {
                    sportMapper.insertSelective(sport);
                    return ReturnUtil.ret(true, "体育项目添加成功");
                }
            }else {
                return ReturnUtil.ret(false,"添加失败，最大值和最小值输入值不合规范");
            }
        }else {
           return ReturnUtil.ret(false,"请输入正确得格式");
        }

    }

    @Override
    public Map<String, Object> updateSport(Sport sport) {
        //输入格式检查
        if (sport.getSortrule()==0||(sport.getSortrule()==1&&TimeRegexUtil.TimeRegex(sport.getInmax(),sport.getInmin(),sport.getRecord(),sport.getTwolevel()))) {
            //限制输入检查
            if (CheckUtil.incheck(sport.getSortrule(), sport.getInmax(), sport.getInmin())) {
                sportMapper.updateByPrimaryKeySelective(sport);
                return ReturnUtil.ret(true, "更新成功");
            } else {
                return ReturnUtil.ret(false,"更新失败，最大值和最小值输入值不合规范");
            }
        }else {
            return ReturnUtil.ret(false,"请输入正确的格式");
        }
    }

    @Override
    public List<Sport> getSportList(Integer tempPage, Integer pageCapacity) {
        return sportMapper.getSportList(tempPage*pageCapacity,pageCapacity);
    }

    @Override
    public Integer countSportList() {
        return sportMapper.countSportList();
    }

    @Override
    public Map<String, Object> addPreRoles(String role) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        JSONArray jsonArray=JSONArray.fromObject(role);
        List<PreRole> preRoles=new ArrayList<>();
        for (int i=0;i<jsonArray.size();i++){
            JSONObject json=new JSONObject();
            json= JSONObject.fromObject(jsonArray.getString(i));
            PreRole preRole=new PreRole();
            preRole.setSportid(json.getInt("sportid"));
            preRole.setRank(json.getInt("rank"));
            preRole.setAddscore(json.getDouble("addscore"));
            preRole.setCampus(json.getString("campus"));
            preRole.setSportname(json.getString("sportname"));
            preRoles.add(preRole);
        }
        List<PreRole> failProRole=new ArrayList<>();
        for (int i=0;i<preRoles.size();i++){
            //检查该规则是否存在
            System.out.println(preRoleMapper.isexist(preRoles.get(i)));
                if (preRoleMapper.isexist(preRoles.get(i))<1){
                    preRoleMapper.insertSelective(preRoles.get(i));
                }else {
                    failProRole.add(preRoles.get(i));
                }
        }
        resultMap.put("message","失败"+failProRole.size()+"条");
        resultMap.put("proRoleList",failProRole);
        return resultMap;
    }

    @Override
    public Map<String, Object> updatePreRole(PreRole role) {
        int i=preRoleMapper.updatePreRole(role);
        if (i>0){
            return ReturnUtil.ret(true,"修改成功");
        }else {
            return ReturnUtil.ret(false,"修改失败");
        }
    }

    @Override
    public Map<String, Object> deletePreRole(Integer id) {
        int i=preRoleMapper.deleteByPrimaryKey(id);
        if (i>0){
            return ReturnUtil.ret(true,"删除成功");
        }else {
            return ReturnUtil.ret(false,"删除失败");
        }
    }

    @Override
    public List<PreRole> getPreRoleList(Integer sportId) {
        return preRoleMapper.getPreRoleList(sportId);
    }

    @Override
    public List<Sport> getSportId() {
        return sportMapper.getSportIdList();
    }

    @Override
    public Map<String, Object> addRoles(String role) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        JSONArray jsonArray=JSONArray.fromObject(role);
        List<Role> roleList=new ArrayList<>();
        for (int i=0;i<jsonArray.size();i++){
            JSONObject json=new JSONObject();
            json= JSONObject.fromObject(jsonArray.getString(i));
            Role roles=new Role();
            roles.setSportid(json.getInt("sportid"));
            roles.setRank(json.getInt("rank"));
            roles.setCampus(json.getString("campus"));
            roles.setSportname(json.getString("sportname"));
            roles.setAddscore(json.getDouble("addscore"));
            roleList.add(roles);
        }
        List<Role> failRole=new ArrayList<>();
         for (int i=0;i<roleList.size();i++){
            //检查该规则是否存在
             if (roleMapper.isExist(roleList.get(i))<1){
                 roleMapper.insertSelective(roleList.get(i));
             }else {
                 failRole.add(roleList.get(i));
             }
         }
         resultMap.put("message","失败"+failRole.size()+"条");
         resultMap.put("RoleList",failRole);
         return resultMap;
    }

    @Override
    public Map<String, Object> updateRole(Role role) {
        int i=roleMapper.updateByPrimaryKeySelective(role);
        if (i>0){
            return ReturnUtil.ret(true,"更新成功");
        }else {
            return ReturnUtil.ret(false,"更新失败");
        }
    }

    @Override
    public Map<String, Object> deleteRole(int id) {
        int i=roleMapper.deleteByPrimaryKey(id);
        if (i>0){
            return ReturnUtil.ret(true,"删除成功");
        }else {
            return ReturnUtil.ret(false,"删除失败");
        }
    }

    @Override
    public List<Role> getRoleList(int sportid) {
        return roleMapper.getRoleList(sportid);
    }

    @Override
    public Map<String, Object> addTeamRole(TeamRole teamRole) {
        //检查该规则是否存在
        if (teamRoleMapper.isTeamRoleExsit(teamRole)<1){
            teamRoleMapper.insertSelective(teamRole);
            return ReturnUtil.ret(true,"添加成功");
        }else{
            return ReturnUtil.ret(false,"该规则已经存在");
        }
    }

    @Override
    public Map<String, Object> updateTeamRole(TeamRole teamRole) {
        int i=teamRoleMapper.updateByPrimaryKeySelective(teamRole);
        if (i>0){
            return ReturnUtil.ret(true,"更新成功");
        }else {
            return ReturnUtil.ret(false,"更新失败");
        }
    }

    @Override
    public Map<String, Object> deleteTeamRole(Integer id) {
        teamRoleMapper.deleteByPrimaryKey(id);
        return ReturnUtil.ret(true,"成功删除");
    }

    @Override
    public List<TeamRole> getTeamRoleList(Integer sportid) {
        return teamRoleMapper.getBySportid(sportid);
    }

    @Override
    public Map<String, Object> addSportMeet(History history) {
        try {
            historyMapper.insertSelective(history);
            return ReturnUtil.ret(true,"成功添加");
        }catch (Exception e){
            return ReturnUtil.ret(true,"添加失败");
        }
    }
    @Override
    public Map<String ,Object> updateSportMeet(History history){
        try {
            historyMapper.updateByPrimaryKeySelective(history);
            return ReturnUtil.ret(true,"成功修改");
        }catch (Exception e){
            return ReturnUtil.ret(true,"修改失败");
        }
    }
    @Override
    public Map<String,Object> delSportMeet(Integer id){
        try {
            historyMapper.deleteByPrimaryKey(id);
            return ReturnUtil.ret(true,"成功删除");
        }catch (Exception e){
            return ReturnUtil.ret(true,"删除失败");

        }
    }
    @Override
    public List<History> getAllSportMeet(){
        return historyMapper.getAllHistory();
    }
    @Override
    public Map<String,Object> getContantList(Integer tempPage, Integer pageCapacity,String currentime) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        resultMap.put("contestant",contestantMapper.getContestList(tempPage*pageCapacity,pageCapacity,currentime));
        resultMap.put("count",contestantMapper.count(currentime));
        return resultMap;
    }
    @Override
    public Map<String,Object> getContantListNoCheck(Integer tempPage, Integer pageCapacity,String currentime){
        Map<String,Object> resultMap=new LinkedHashMap<>();
        resultMap.put("contestant",contestantMapper.getContestListNocheck(tempPage*pageCapacity,pageCapacity,currentime));
        resultMap.put("count",contestantMapper.countNoCheck(currentime));
        return resultMap;
    }
    @Override
    public Map<String,Object> getContestantUp(Integer tempPage, Integer pageCapacity,String currentime){
        Map<String,Object> resultMap=new LinkedHashMap<>();
        resultMap.put("contestant",contestantMapper.getContestantUp(tempPage*pageCapacity,pageCapacity,currentime));
        resultMap.put("count",contestantMapper.countUp(currentime));
        return resultMap;
    }
    @Override
    public Map<String,Object> checkContestant(Integer id){
        if (contestantMapper.check(id)>0){
           return ReturnUtil.ret(true,"审核成功");
        }else {
            return ReturnUtil.ret(false,"审核失败");
        }
    }
    @Override
    public List<Sport> getSportBySex(Integer tempPage, Integer pageCapacity,Integer sex){
        return sportMapper.getSportsBySex(tempPage*pageCapacity,pageCapacity,sex);
    }
    @Override
    public int getSportCount(Integer sex){
        return sportMapper.getSportCount(sex);
    }

}
