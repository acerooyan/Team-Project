package com.example.emrestserver.security.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = "/employee/*")
public class EmployeeFilter implements Filter {



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession oldSession = ((HttpServletRequest)servletRequest).getSession(false);
        System.out.println("EmployeeFilter   " + oldSession);
        if (oldSession == null ) {
            servletRequest.setAttribute("exception","PLEASE LOG IN");
            servletRequest.setAttribute("url",((HttpServletRequest) servletRequest).getRequestURL());
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/errorPage");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }




}
