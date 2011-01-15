package ru.snslabs.icq.actions;

import ru.snslabs.icq.ICQGate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

public class AddContact extends ICQAbstractAction {
    public void act(HttpServletRequest request, HttpServletResponse response,
                    ServletContext servletContext) throws Exception {

        ICQGate icqGate = getICQGate(request, response);
        String nick = request.getParameter("add_uin");
        String uin = request.getParameter("add_nick");
        String dsc = request.getParameter("add_dsc");
        icqGate.getContactList().addContact(uin, nick, dsc);
        response.sendRedirect("/gw/main.jsp");
    }
}
