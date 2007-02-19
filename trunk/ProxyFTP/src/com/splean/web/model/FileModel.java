package com.splean.web.model;

import java.io.Serializable;
import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

public class FileModel implements Serializable, Comparable {
    private boolean directory;
    private String name;
    private String extension;
    private String date;
    private String size;
    private String fullPath;

    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
    private static final DecimalFormat DF = new DecimalFormat("#,###");

    public FileModel() {
    }

    FileModel(File f) {
        fullPath = f.getAbsolutePath();
        fullPath = fullPath.replaceAll("\\\\","/");
        directory = f.isDirectory();
        name = f.getName();
        if (!directory) {
            extension = extractExtension(f.getName());
            if (extension != null) {
                name = name.substring(0, name.length() - extension.length() - 1);
            }
        }
        date = SDF.format(new Date(f.lastModified()));
        size = f.length() == 0 ? "" : DF.format(f.length());
    }

    private String extractExtension(String name) {
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
        return this.name;
    }

    public int compareTo(Object o) {
        if (o instanceof FileModel) {
            final FileModel fileModel = (FileModel) o;
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
        return 0;
    }
}
