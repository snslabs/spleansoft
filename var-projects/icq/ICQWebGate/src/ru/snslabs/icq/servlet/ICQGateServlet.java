package ru.snslabs.icq.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import ru.snslabs.icq.actions.*;

public class ICQGateServlet extends HttpServlet {
    private Map actions;
    private static final Log log = LogFactory.getLog(ICQGateServlet.class);

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        // карта обработчиков действий
        actions = new HashMap();
        actions.put("login", new LoginAction());
        actions.put("gwlogin", new ICQLoginAction());
        actions.put("gwsend", new ICQSendAction());
        actions.put("addcontact", new AddContact());
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("Cp1251");
        String action = request.getServletPath();
        action = action.substring(action.lastIndexOf("/")+1);
        action = action.substring(0,action.lastIndexOf(".gw"));
        log.debug("action="+action);
        Action actionImpl = (Action) actions.get(action);
        if(actionImpl==null){
            throw new ServletException("action "+action+
                    " does not have corresponding implementation class! Review ICQGateServlet");
        }
        try{
            actionImpl.act(request, response, getServletContext());
        }
        catch(Exception e){
            log.error("Error processing action "+ action,e);
            throw new ServletException(e);
        }
    }
}
