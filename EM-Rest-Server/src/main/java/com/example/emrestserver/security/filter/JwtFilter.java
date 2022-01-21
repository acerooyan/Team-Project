package com.example.emrestserver.security.filter;


import com.example.emrestserver.constant.JwtConstant;
import com.example.emrestserver.security.util.CookieUtil;
import com.example.emrestserver.security.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/api/jwt/*")
public class JwtFilter implements Filter {

//    @Override
//    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
//
//        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
//        System.out.println(req.getHeader("Origin"));
//        res.setHeader("Access-Control-Allow-Credentials", "true");
//        res.setHeader("Access-Control-Allow-Methods", "*");
//        res.setHeader("Access-Control-Max-Age", "3600");
//        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
//
//        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
//        System.out.println("+++++++++JwtFilter++++++++");
//        System.out.println(token);
//        if (token!=null) {
//            String userName = JwtUtil.getSubjectFromJwt(token);
//            System.out.println(userName);
//            Claims claims = JwtUtil.getClaimsFromJwt(token);
//            if (userName!=null) {
//                req.setAttribute("userName", userName);
//                filterChain.doFilter(req, res);
//            } else {
////                String authLoginUrl = getFilterConfig().getInitParameter("authLoginUrl");
////                res.sendRedirect(authLoginUrl + "?redirect=" + req.getRequestURL());
//            }
//        } else {
////            String authLoginUrl = getFilterConfig().getInitParameter("authLoginUrl");
//            res.sendRedirect("authLoginUrl" + "?redirect=" + req.getRequestURL());
//        }
//    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
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


        //String authorization = req.getHeader("Authorization");
        //String token = authorization.substring(7);

        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
        System.out.println("+++++++++JwtFilter++++++++");
        System.out.println(token+"token");




        String userId = null;
        Claims claims = JwtUtil.getClaimsFromJwt(token);

        if(claims!=null){
            userId = claims.get("id").toString();
            System.out.println("UserId is " + userId);
        }


        if (userId == null ) {
            System.out.println("Failed first filter, direct back to login");
            res.setStatus(401);
        } else {
            System.out.println("pass first filter.");
            req.setAttribute("userId", userId);
            filterChain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
