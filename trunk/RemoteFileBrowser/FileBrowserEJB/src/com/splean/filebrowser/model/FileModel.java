package com.splean.filebrowser.model;

import java.io.Serializable;

/**
 * Модель файла для передачи по iiop
 */
public class FileModel implements Serializable, Comparable {
    public static final int TYPE_FILE = 0;
    public static final int TYPE_DIR = 1;
    private String absolutePath;
    private String fileName;
    private int type;

    public FileModel() {
    }

    public FileModel(String absolutePath, String fileName, int type) {
        this.fileName = fileName;
        this.absolutePath = absolutePath;
        this.type = type;

    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public int getType() {
        return type;
    }

    public String getFileName() {
        return fileName;
    }

    public int compareTo(Object object) {
        if(object instanceof FileModel){
            FileModel fileModel = ((FileModel) object);
            if(this.type == fileModel.type){
                return this.getAbsolutePath().compareTo(fileModel.getAbsolutePath());
            }
            else{
                return fileModel.type - this.type;
            }
        }
        return 0;
    }

    public String toString() {
        return (this.type==FileModel.TYPE_DIR?"DIR ":"    ")+ this.fileName;
    }
}
