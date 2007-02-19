package com.splean.web.actions;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.splean.web.forms.Upload;

import java.io.FileOutputStream;

public class UploadAction extends Action {

    public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        final Upload uploadForm = ((Upload) actionForm);
        FormFile myFile = uploadForm.getUploadedFile();
        String contentType = myFile.getContentType();
        String fileName = myFile.getFileName();
        int fileSize = myFile.getFileSize();
        byte[] fileData = myFile.getFileData();
        System.out.println("Uploaded = " + fileName);

        FileOutputStream fos = new FileOutputStream(uploadForm.getCurrentDir() + "/" + fileName);
        fos.write(fileData);
        fos.close();

        return actionMapping.findForward("panel");
    }
}
