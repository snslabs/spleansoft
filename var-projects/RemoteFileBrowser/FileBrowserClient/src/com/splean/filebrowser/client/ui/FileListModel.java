package com.splean.filebrowser.client.ui;

import com.splean.filebrowser.model.FileModel;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class FileListModel extends AbstractListModel {

    private List files = new ArrayList();

    public FileListModel(List files) {
        this.files = files;
    }

    public int getSize() {
        return files.size();
    }

    public Object getElementAt(int index) {
        return getFileAt(index);
    }

    private FileModel getFileAt(int index) {
        return (FileModel)files.get(index);
    }

    public void setFiles(List files) {
        this.files = files;
    }


}
