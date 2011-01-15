package ru.snslabs.icq.servlet;

import ru.snslabs.icq.actions.LoginAction;

import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ICQGateFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)request).getSession();
        if(session.getAttribute(LoginAction.USER_KEY)!=null){
            System.out.println("access granted");
            filterChain.doFilter(request, response);
        }
        else{
            System.out.println("access denied!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
