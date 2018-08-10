package org.wingstudio.sports.interceptor;

import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;


public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
       // System.out.println("test  =============");
        // TODO Auto-generated method stub
        //logger.info("------preHandle------");
        //获取session
        HttpSession session = request.getSession(true);
        //获取访问url
        String url = request.getRequestURL().toString();
        String token = request.getHeader("token");
        //返回json
        JSONObject jsonObject = new JSONObject();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        //判断用户ID是否存在，不存在就跳转到登录界面
        //System.out.println(session.getAttribute("token") == null && session.getAttribute("token") != token);
        if (!(session.getAttribute("token") == null && session.getAttribute("token") != token)) {
            //logger.info("------:跳转到login页面！");
            jsonObject.put("status", "403");
            jsonObject.put("message", "请登录");
            PrintWriter out = response.getWriter();
            out.append(jsonObject.toString());
            System.out.println("error");
            return false;
        } else {
            //session.setAttribute("userId", session.getAttribute("userId"));
            //System.out.println("success");
            return true;

        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

}
