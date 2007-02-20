package com.splean.web.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

interface FilesFacadeInterface {
    List<AbstractFileModel> dir(String path) throws FileBrowserException, IOException;

    String deleteFile(String path) throws FileBrowserException;

    String copyFile(String filePath, String clipboardId);

    void uploadFile(String path, byte[] fileData) throws IOException;

    String pasteFiles(String dstDirPath, String clipboardId);

    List<AbstractFileModel> getClipboard(String clipboardId);

    String createClipboard();

    byte[] getFileDataAsByteArray(AbstractFileModel fileModel) throws IOException;

    InputStream getFileDataAsStream(AbstractFileModel fileModel) throws IOException;

    AbstractFileModel getFile(String path);

    String createDirectory(String path);
}
