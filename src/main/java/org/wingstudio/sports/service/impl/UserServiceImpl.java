package org.wingstudio.sports.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingstudio.sports.dao.SportMapper;
import org.wingstudio.sports.dao.UserMapper;
import org.wingstudio.sports.domain.Sport;
import org.wingstudio.sports.domain.User;
import org.wingstudio.sports.service.UserService;
import org.wingstudio.sports.util.ReturnUtil;
import org.wingstudio.sports.util.TimeRegexUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service("loginService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SportMapper sportMapper;
    @Override
    public Map<String, Object> login(User user, HttpServletRequest request) {
        HttpSession session=request.getSession();
        if (userMapper.getByNamePass(user)!=null){
            session.setAttribute("token",session.getId());
            return ReturnUtil.ret(true,"登陆成功");
        }else {
            return ReturnUtil.ret(false,"登陆失败");
        }
    }

    @Override
    public Map<String,Object> addSport(Sport sport) {
        //输入值格式检查
        if (sport.getSortrule()==0||(sport.getSortrule()==1&&TimeRegexUtil.TimeRegex(sport.getInmax(),sport.getInmin(),sport.getRecord(),sport.getTwolevel()))){
            //体育项目是否存在
            if (sportMapper.sportIsExist(sport.getProject(), sport.getSex()) > 0) {
                return ReturnUtil.ret(false,"该体育项目已经存在");
            } else {
                sportMapper.insertSelective(sport);
                return ReturnUtil.ret(true,"体育项目添加成功");
            }
        }else {
           return ReturnUtil.ret(false,"请输入正确得格式");
        }

    }

    @Override
    public Map<String, Object> updateSport(Sport sport) {
        //输入格式检查
        if (sport.getSortrule()==0||(sport.getSortrule()==1&&TimeRegexUtil.TimeRegex(sport.getInmax(),sport.getInmin(),sport.getRecord(),sport.getTwolevel()))){
            sportMapper.updateByPrimaryKeySelective(sport);
            return ReturnUtil.ret(true,"更新成功");
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

}
