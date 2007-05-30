package com.web;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.validator.ValidatorActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sns.UserDirectory;

public class LogonAction extends Action {

    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        boolean res = false;
        LogonForm logonForm = (LogonForm) actionForm;
        res = UserDirectory.getInstance().isValidPassword(logonForm.getUsername(), logonForm.getPassword());
        ActionForward forward = null;

        if(res){
            httpServletRequest.getSession().setAttribute(Constants.USER_KEY, logonForm);
            forward = actionMapping.findForward(Constants.WELCOME_SCREEN);
        }else{

        }
        return forward;
    }

}
