package com.splean.filebrowser.servlet;

import javax.servlet.*;
import java.io.IOException;

public class PrimaryFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Primary filter initialized");
        System.out.println(filterConfig.getInitParameter("param-1"));
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Primary filter processed");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        System.out.println("Primary filter destoyed!");
    }
}
