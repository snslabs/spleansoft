package spln;

import javax.servlet.*;
import java.io.IOException;

public class PreProcessor implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Processed by filter!");
        filterChain.doFilter(request, response);
    }

    public void destroy() {

    }
}
