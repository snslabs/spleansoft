package com.splean.web.file;

public class FtpFileModel extends AbstractFileModel {

    public FtpFileModel(String fileName, String dirPath, String server, int port, String user, String password) {
        name = fileName;
        extension = extractExtension(name);
        if (extension != null) {
            name = name.substring(0, name.length() - extension.length() - 1);
        }
        fullPath = "ftp://"+user+":"+password+"@"+server+":"+port+dirPath+fileName;
    }

}
