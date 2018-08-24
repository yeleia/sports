package org.wingstudio.sports.service.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingstudio.sports.dao.PreRoleMapper;
import org.wingstudio.sports.dao.RoleMapper;
import org.wingstudio.sports.dao.SportMapper;
import org.wingstudio.sports.dao.UserMapper;
import org.wingstudio.sports.domain.PreRole;
import org.wingstudio.sports.domain.Role;
import org.wingstudio.sports.domain.Sport;
import org.wingstudio.sports.domain.User;
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
    @Override
    public Map<String, Object> login(User user, HttpServletRequest request) {
        Map<String,Object> resultMap=new LinkedHashMap<>();
        HttpSession session=request.getSession();
        if (userMapper.getByNamePass(user)!=null){
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
        JSONArray jsonArray=JSONArray.fromObject(role);
        List<PreRole> preRoles=new ArrayList<>();
        for (int i=0;i<jsonArray.size();i++){
            JSONObject json=new JSONObject();
            json= JSONObject.fromObject(jsonArray.getString(i));
            PreRole preRole=new PreRole();
            preRole.setSportid(json.getInt("sportid"));
            preRole.setRank(json.getInt("rank"));
            preRole.setAddscore(json.getDouble("addscore"));
            preRoles.add(preRole);
        }
        int i= preRoleMapper.addPreRoles(preRoles);
        if (i<preRoles.size()){
            return ReturnUtil.ret(false,"部分数据插入失败");
        }else {
            return ReturnUtil.ret(true,"添加成功");
        }
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
        JSONArray jsonArray=JSONArray.fromObject(role);
        List<Role> roleList=new ArrayList<>();
        for (int i=0;i<jsonArray.size();i++){
            JSONObject json=new JSONObject();
            json= JSONObject.fromObject(jsonArray.getString(i));
            Role roles=new Role();
            roles.setSportid(json.getInt("sportid"));
            roles.setRank(json.getInt("rank"));
            roles.setAddscore(json.getDouble("addscore"));
            roleList.add(roles);
        }
        int i= roleMapper.addRoles(roleList);
        if (i<roleList.size()){
            return ReturnUtil.ret(false,"部分数据插入失败");
        }else {
            return ReturnUtil.ret(true,"添加成功");
        }
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



}
