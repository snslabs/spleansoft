package com.splean.web.model;

import org.apache.struts.action.ActionForm;

import java.util.List;
import java.util.Arrays;

public class FilePanel extends ActionForm {

    private String path;
    private List<String> files;

    public FilePanel() {
        files = Arrays.asList("123.txt","blablabla.gif","BIN");
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }
}
