package com.splean.web.file;

import java.io.Serializable;
import java.io.File;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

public abstract class AbstractFileModel implements Serializable, Comparable<AbstractFileModel> {
    
    protected boolean directory;
    protected String name;
    protected String extension;
    protected String date;
    protected String size;
    protected String fileName;
    protected String fullPath;
    protected File file;
    protected int type;
    protected static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
    protected static final DecimalFormat DF = new DecimalFormat("#,###");
    protected boolean copiable;
    protected boolean deletable;
    protected boolean downloadable;

    protected String extractExtension(String name) {
        int lastDotIndex = name.lastIndexOf(".");
        if ((lastDotIndex != -1) && (lastDotIndex + 1 < name.length())) {
            String ext = name.substring(lastDotIndex + 1);
            if (ext.length() > 5 || ext.length() < 1) {
                return null;
            }
            return ext;
        }
        else {
            return null;
        }
    }

    public boolean isDirectory() {
        return directory;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public String getDate() {
        return date;
    }

    public String getSize() {
        return size;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String toString() {
        return this.fullPath;
    }

    public long length() {
        return file.length();
    }

    File getFile() {
        return file;
    }

    public String getFileName() {
        return fileName;
    }


    public int compareTo(AbstractFileModel fileModel) {
        if (fileModel.directory && !this.directory) {
            return 1;
        }
        else if (!fileModel.directory && this.directory) {
            return -1;
        }
        else {
            return this.name.compareTo(fileModel.name);
        }
    }

    public boolean isCopiable() {
        return copiable;
    }

    public void setCopiable(boolean copiable) {
        this.copiable = copiable;
    }

    public boolean isDeletable() {
        return deletable;
    }

    public void setDeletable(boolean deletable) {
        this.deletable = deletable;
    }

    public boolean isDownloadable() {
        return downloadable;
    }

    public void setDownloadable(boolean downloadable) {
        this.downloadable = downloadable;
    }
}
