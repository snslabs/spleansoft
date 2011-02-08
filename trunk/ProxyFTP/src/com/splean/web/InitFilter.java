package com.splean.web;

import com.splean.FileBrowserConfig;
import com.splean.web.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InitFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        filterConfig.getServletContext().setAttribute(WebConstants.CONFIG_KEY, FileBrowserConfig.configure());
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("req: "+ req.getServletPath());
        if("/loginSubimt.do".equals(req.getServletPath())){
            filterChain.doFilter(req, response);
        }
        boolean isLocal = "127.0.0.1".equals(req.getRemoteAddr());
        if(req.getSession().getAttribute(WebConstants.USER_KEY) == null){
            if(isLocal){
                System.out.println("User is local.. setting to admin.");
                User user = new User("admin","localadmin");
                req.getSession().setAttribute(WebConstants.USER_KEY, user);
            }
            else{
                System.out.println("User not authenticated");
                request.getRequestDispatcher("/login.do").forward(req, response);
                return;
            }
        }
        filterChain.doFilter(req, response);
    }

    public void destroy() {

    }
}
