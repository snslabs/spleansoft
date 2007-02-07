package spln;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;

public class LargeServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>multiservlet generated page</title></head><body>");
        out.println("Not embedded text!!!!<hr/>");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("blah.em");
        requestDispatcher.include(request, response);
        out.println("</body></html>");
    }
}
