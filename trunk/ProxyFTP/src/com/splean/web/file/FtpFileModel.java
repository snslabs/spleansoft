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

    public FtpFileModel(String fileName, String dirPath, String server, int port, String user, String password, boolean isDirectory) {
        directory = isDirectory;
        name = fileName;
        extension = extractExtension(name);
        if (extension != null) {
            name = name.substring(0, name.length() - extension.length() - 1);
        }
        if(!dirPath.endsWith("/")){
            dirPath = dirPath + "/";
        }
        if("..".equals(fileName)){
            dirPath = dirPath.substring(0,dirPath.length()-1);
            dirPath = dirPath.substring(0, dirPath.lastIndexOf("/"));
            fileName = "";
        }
        fullPath = "ftp://"+user+":"+password+"@"+server+":"+port+dirPath+fileName;

        if(!fullPath.endsWith("/")){
                fullPath = fullPath + (directory?"/":"");
        }
    }
}
