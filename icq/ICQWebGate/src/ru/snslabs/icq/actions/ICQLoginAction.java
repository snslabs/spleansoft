package ru.snslabs.icq.actions;

import ru.snslabs.icq.ICQGate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

final public class ICQLoginAction extends AbstractAction {
    public static final String UIN_KEY = "uin";
    public static final String PASS_KEY = "password";

    public void act(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception {
        String uin = request.getParameter(UIN_KEY);
        String password = request.getParameter(PASS_KEY);
        ICQGate icqGate = new ICQGate(uin, password);
        try{
            icqGate.login();
            icqGate.getContactList().addContact(uin,"Я","Это я! :-)");
            icqGate.getContactList().addContact("342367696","Splean","This is me!!! :-)");
            request.getSession(true).setAttribute(ICQAbstractAction.ICQ_GATE_KEY, icqGate);
            response.sendRedirect("/gw/main.jsp");
        }
        catch(Exception e){
            request.getSession(true).removeAttribute(ICQAbstractAction.ICQ_GATE_KEY);
            forward("/gw/login.jsp", request, response);
        }
    }
}
