package com.example.emrestserver.security.filter;

import com.example.emrestserver.constant.JwtConstant;
import com.example.emrestserver.security.util.CookieUtil;
import com.example.emrestserver.security.util.JwtUtil;
import io.jsonwebtoken.Claims;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = "/api/jwt/employee/*")
public class EmployeeFilter implements Filter {



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        System.out.println(req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Methods", "*");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
        System.out.println("+++++++++EmployeeFilter++++++++");
        System.out.println(token+"token");

        Claims claims = JwtUtil.getClaimsFromJwt(token);
        String role = null;
        if(claims!=null){
            role = claims.get("role").toString();
            System.out.println(role);
        }

        if (role != null && !role.equalsIgnoreCase("employee")) {
            System.out.println("Failed employee filter, direct back to login");
            res.setStatus(401);
        } else {
            System.out.println("pass employee filter.");
            req.setAttribute("userId", role);
            filterChain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {

    }
}
