package ru.snslabs.icq.actions;

import ru.snslabs.icq.ICQGate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

abstract public class ICQAbstractAction extends AbstractAction {
    public static final String ICQ_GATE_KEY = "ICQ_GATE";

    protected ICQGate getICQGate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ICQGate icqGate = (ICQGate) request.getSession().getAttribute(ICQ_GATE_KEY);
        if(icqGate == null){
            forward("gwlogin.jsp",request, response);
        }
        return icqGate;
    }
}
