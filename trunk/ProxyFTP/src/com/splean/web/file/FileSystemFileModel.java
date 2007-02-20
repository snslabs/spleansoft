package com.splean.web.file;

import java.io.File;
import java.util.Date;

public class FileSystemFileModel extends AbstractFileModel {

    public FileSystemFileModel() {
    }

    public FileSystemFileModel(File f) {
        file = f;
        fullPath = f.getAbsolutePath();
        fullPath = fullPath.replaceAll("\\\\","/");
        directory = f.isDirectory();
        fileName = f.getName();
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



}
