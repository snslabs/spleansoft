package com.splean.web.file;

import javax.servlet.SingleThreadModel;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.net.MalformedURLException;

interface FilesFacadeBackend {
    
    List<AbstractFileModel> dir(String path) throws FileBrowserException, IOException;

    String deleteFile(String path) throws FileBrowserException, IOException;

    String copyFile(String filePath, String clipboardId);

    void uploadFile(String path, byte[] fileData) throws IOException, FileBrowserException;

    String pasteFiles(String dstDirPath, String clipboardId);

    List<AbstractFileModel> getClipboard(String clipboardId);

    String createClipboard();

    byte[] getFileDataAsByteArray(AbstractFileModel fileModel) throws IOException;

    InputStream getFileDataAsStream(AbstractFileModel fileModel) throws IOException;

    AbstractFileModel getFile(String path) throws IOException;

    String createDirectory(String path) throws IOException, FileBrowserException;

    void init(String path) throws FileBrowserException;
}
