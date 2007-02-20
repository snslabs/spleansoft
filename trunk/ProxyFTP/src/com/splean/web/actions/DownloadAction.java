package com.splean.web.actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class DownloadAction extends Action {
    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {

        final String path = request.getParameter("path");
        System.out.println("Downloading : "+ path);
        File file = new File(path);
        if(file.isDirectory()){
            actionMapping.findForward("filenotfound");
        }
        if(file.length() < 10*1024*1024){
            response.setContentType("application/x-download");
            response.setHeader("Content-disposition", "attachment; filename=" +file.getName());
            response.getOutputStream().write(FileUtils.readFileToByteArray(file));
        }
        else{
            System.out.println("File is too large");
            response.sendError(500, "File is too large. Download is impossible :-(");
        }
        

        return null;
    }
}
