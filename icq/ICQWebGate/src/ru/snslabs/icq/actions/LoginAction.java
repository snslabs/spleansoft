package ru.snslabs.icq.actions;

import ru.snslabs.icq.model.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

final public class LoginAction extends AbstractAction {
    public static final String USER_KEY = "userModel";
    private static final Map security;
    public static final String ADMIN_ROLE = "ADMIN";
    public static final String USER_ROLE = "USER";

    static{
        security = new HashedMap();
        security.put("snslabs","sp128ZX");
        security.put("splean.com","sp128ZX");
    }

    public void act(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws Exception {
        HttpSession session = request.getSession(true);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(checkLogin(username, password)){
            UserModel userModel = new UserModel(username, getRole(username));
            session.setAttribute(USER_KEY, userModel);
            response.sendRedirect("/gw/login.jsp");
        }
        else{
            request.setAttribute("error","invalid username/password");
            forward("login.jsp",request, response);
        }

    }

    private String getRole(String username) {
        if("snslabs".equals(username)){
            return ADMIN_ROLE;
        }
        else{
            return USER_ROLE;
        }
    }

    /**
     * проверяем логин пользователя
     * @param username
     * @param password
     * @return результат проверки пароля
     */
    private boolean checkLogin(String username, String password) {
        return username!= null && password!=null && (password.equals(security.get(username)));
    }

}
