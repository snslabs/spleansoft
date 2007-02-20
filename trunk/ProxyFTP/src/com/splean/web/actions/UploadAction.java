package com.splean.web.actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.splean.web.forms.Upload;
import com.splean.web.file.FilesFacade;

import java.io.FileOutputStream;

public class UploadAction extends Action {
    private FilesFacade filesFacade = new FilesFacade();

    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        final Upload uploadForm = ((Upload) actionForm);
        FormFile myFile = uploadForm.getUploadedFile();
        String contentType = myFile.getContentType();
        String fileName = myFile.getFileName();
        int fileSize = myFile.getFileSize();
        byte[] fileData = myFile.getFileData();
        System.out.println("Uploaded = " + fileName);

        final String path = uploadForm.getCurrentDir() + "/" + fileName;
        filesFacade.uploadFile(path, fileData);


        return actionMapping.findForward("panel");
    }
}
