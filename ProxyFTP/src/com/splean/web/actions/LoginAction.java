package com.splean.web.actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.splean.web.model.User;
import com.splean.web.forms.Login;
import com.splean.web.WebConstants;


public class LoginAction extends Action {
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        System.out.println("checking username");
        Login loginForm = (Login) actionForm;
        User user = null;

        if(!loginForm.getUsername().equals("enemy")){
            user = new User(loginForm.getUsername(), "N/A");
        }

        if(user==null){
            httpServletRequest.getSession().removeAttribute(WebConstants.USER_KEY);
            return actionMapping.findForward("login");
        }
        else{
            httpServletRequest.getSession().setAttribute(WebConstants.USER_KEY, user);
        }
        return actionMapping.findForward("panel");
    }
}
