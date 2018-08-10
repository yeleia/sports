package org.wingstudio.sports.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wingstudio.sports.dao.UserMapper;
import org.wingstudio.sports.domain.User;
import org.wingstudio.sports.service.UserService;
import org.wingstudio.sports.util.ReturnUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Service("loginService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
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
}
