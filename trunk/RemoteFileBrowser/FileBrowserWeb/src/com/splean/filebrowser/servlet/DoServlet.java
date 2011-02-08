package com.splean.filebrowser.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

public class DoServlet extends HttpServlet {

//    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
//        System.out.println("SERVICE!!!");
//        httpServletResponse.sendRedirect("/index.jsp?from=do");
//    }

    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        System.out.println("POST!!!");
        httpServletResponse.sendRedirect("/index.jsp?from=do");
    }
}
