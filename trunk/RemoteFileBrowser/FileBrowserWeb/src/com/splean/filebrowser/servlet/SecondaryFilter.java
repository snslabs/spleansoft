package com.splean.filebrowser.servlet;

import javax.servlet.*;
import java.io.IOException;

public class SecondaryFilter extends  PrimaryFilter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Secondary filter initialized");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Secondary filter processed");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        System.out.println("Secondary filter destoyed!");
    }
}
