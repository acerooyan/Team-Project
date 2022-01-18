package com.example.emrestserver.security.filter;



import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = "/hr/*")
public class HRFilter implements Filter {



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession oldSession = ((HttpServletRequest)servletRequest).getSession(false);
        System.out.println("HrFilter    " + oldSession);
        if (oldSession == null) {// need to add condition to check jwt role
            servletRequest.setAttribute("exception"," NO AUTHORIZATION");
            servletRequest.setAttribute("url",((HttpServletRequest) servletRequest).getRequestURL());
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.sendRedirect(((HttpServletRequest) servletRequest).getContextPath() + "/errorPage");
        } else {
            System.out.println("HR do filter");
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
