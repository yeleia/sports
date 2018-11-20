package org.wingstudio.sports;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author beifengtz
 * @Site www.beifengtz.com
 * @Date Created in 22:11 2018/10/29
 * @Description:
 */
@Order(0) // 定义filter执行顺序，数字越小越先执行
@WebFilter(urlPatterns = "/*" ,filterName = "HeaderFilter")
public class HeaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse rep = (HttpServletResponse) servletResponse;

        //设置允许跨域的配置
        // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
        rep.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的访问方法
        rep.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        rep.setHeader("Access-Control-Max-Age", "3600");
        // 头部信息
        rep.setHeader("Access-Control-Allow-Headers", "token, Origin, X-Requested-With, Content-Type, Accept");

        // 字符编码
        servletResponse.setCharacterEncoding("UTF-8");

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
