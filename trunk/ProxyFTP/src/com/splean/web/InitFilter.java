package com.splean.web;

import com.splean.FileBrowserConfig;

import javax.servlet.*;
import java.io.IOException;

public class InitFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        filterConfig.getServletContext().setAttribute(WebConstants.CONFIG_KEY, FileBrowserConfig.configure());
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
