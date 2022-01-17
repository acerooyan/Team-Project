package com.example.emrestserver.security.filter;


import com.example.emrestserver.constant.JwtConstant;
import com.example.emrestserver.security.util.CookieUtil;
import com.example.emrestserver.security.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
        System.out.println("+++++++++JwtFilter++++++++");
        System.out.println(token);
        if (token!=null) {
            String userName = JwtUtil.getSubjectFromJwt(token);
            System.out.println(userName);
            Claims claims = JwtUtil.getClaimsFromJwt(token);
            if (userName!=null) {
                req.setAttribute("userName", userName);
                filterChain.doFilter(req, res);
            } else {
                String authLoginUrl = getFilterConfig().getInitParameter("authLoginUrl");
                res.sendRedirect(authLoginUrl + "?redirect=" + req.getRequestURL());
            }
        } else {
            String authLoginUrl = getFilterConfig().getInitParameter("authLoginUrl");
            res.sendRedirect("authLoginUrl" + "?redirect=" + req.getRequestURL());
        }
    }

}
