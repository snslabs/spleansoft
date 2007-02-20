package com.splean.web.actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.splean.web.file.FilesFacade;
import com.splean.web.file.FileModel;

import java.io.InputStream;
import java.io.OutputStream;

public class DownloadAction extends Action {

    private FilesFacade filesFacade = new FilesFacade();

    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {

        final String path = request.getParameter("path");
        System.out.println("Downloading : "+ path);
        FileModel fileModel = filesFacade.getFile(path);
        if(fileModel.isDirectory()){
            return actionMapping.findForward("filenotfound");
        }

        response.setContentType("application/x-download");
        response.setHeader("Content-disposition", "attachment; filename=" +fileModel.getFileName());

        System.out.println("File is too large. Will use stream to download");
        InputStream is = filesFacade.getFileDataAsInputStream(fileModel);
        /**
         * using 16 kb buffer
         */
        byte[] buf = new byte[16384];
        OutputStream os = response.getOutputStream();
        int length = 0;
        while((length = is.read(buf))!=-1){
            os.write(buf,0,length);
        }
        os.flush();
        return null;
    }
}
