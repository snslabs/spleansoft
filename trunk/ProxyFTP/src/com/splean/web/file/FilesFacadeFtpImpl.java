package com.splean.web.file;

import com.splean.web.file.FileModel;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;

/**
 * FilesFacade to work with ftp server 
 */
class FilesFacadeFtpImpl extends AbstractFiles {

    public List<FileModel> dir(String path) throws FileBrowserException {
        return null;
    }

    public String deleteFile(String path) throws FileBrowserException {
        return null;
    }

    public String copyFile(String filePath, String clipboardId) {
        return null;
    }

    public void uploadFile(String path, byte[] fileData) throws IOException {

    }

    public String pasteFiles(String dstDirPath, String clipboardId) {
        return null;
    }

    public byte[] getFileDataAsByteArray(FileModel fileModel) throws IOException {
        return new byte[0];
    }

    public InputStream getFileDataAsStream(FileModel fileModel) throws IOException {
        return null;
    }

    public FileModel getFile(String path) {
        return null;
    }
}
