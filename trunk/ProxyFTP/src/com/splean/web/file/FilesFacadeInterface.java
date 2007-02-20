package com.splean.web.file;

import com.splean.web.file.FileModel;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;

interface FilesFacadeInterface {
    List<FileModel> dir(String path) throws FileBrowserException;

    String deleteFile(String path) throws FileBrowserException;

    String copyFile(String filePath, String clipboardId);

    void uploadFile(String path, byte[] fileData) throws IOException;

    String pasteFiles(String dstDirPath, String clipboardId);

    List<FileModel>getClipboard(String clipboardId);

    String createClipboard();

    byte[] getFileDataAsByteArray(FileModel fileModel) throws IOException;

    InputStream getFileDataAsStream(FileModel fileModel) throws IOException;

    FileModel getFile(String path);
}
