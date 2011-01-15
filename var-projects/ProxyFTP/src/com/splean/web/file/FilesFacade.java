package com.splean.web.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class FilesFacade {

    /**
     * returns content of the directory with default sorting (dirs first, alphabetically)
     * @param path path to directory
     * @return ordered collection of models
     * @throws FileBrowserException in case of unable to get list of files
     * @throws java.io.IOException in case of error in ftp processing
     */
    public List<AbstractFileModel> dir(String path) throws FileBrowserException, IOException {
        final FilesFacadeBackend backend = getImplementation(path);
        return backend.dir(path);
    }

    /**
     * Deletes file
     * @param path absolute path to file
     * @return error message in case of error, null if succeded
     * @throws FileBrowserException in case of fatal error
     * @throws java.io.IOException in case of io error
     */
    public String deleteFile(String path) throws FileBrowserException, IOException {
        return getImplementation(path).deleteFile(path);
    }

    /**
     * Method generates id for clipboard
     * @return unique clipboard id
     */
    public String createClipboard(){
        return getImplementation("default").createClipboard();
    }

    /**
     * Copies file to clipboard
     * @param filePath path to file
     * @param clipboardId clipboardId
     * @return null if succeded or error message otherwise.
     */
    public String copyFile(String filePath, String clipboardId){
        return getImplementation(filePath).copyFile(filePath, clipboardId);
    }

    /**
     * Stores provided byte array into file with provided path.
     * Not for use via DWR
     * @param path path to newly created file
     * @param fileData binary data of file content
     * @throws IOException in case of error
     */
    public void uploadFile(String path, byte[] fileData) throws IOException, FileBrowserException {
        getImplementation(path).uploadFile(path, fileData);
    }

    /**
     * Pastes files from clipboard to current directory
     * @param dstDirPath destination directory
     * @param clipboardId clipboard identifier
     * @return result of copying
     */
    public String pasteFiles(String dstDirPath, String clipboardId){
        return getImplementation(dstDirPath).pasteFiles(dstDirPath,clipboardId);
    }

    /**
     * Returns list of files in clipboard
     * @param clipboardId clipboard identifier
     * @return list of files placed in clipboard
     */
    public List<AbstractFileModel>getClipboard(String clipboardId){
        return getImplementation("default").getClipboard(clipboardId);
    }

    public String createDirectory(String fullDirPath) throws IOException, FileBrowserException {
        return getImplementation(fullDirPath).createDirectory(fullDirPath);
    }

    /**
     * This method decides what implementation should be used to deal with file specified with path.
     * The main idea is that path always contains full path (like URL). So it is possible to determine
     * if this is file on local file system or file at some ftp server.
     * @param path path to file
     * @return implementation of FilesFacade
     */
    private FilesFacadeBackend getImplementation(String path){
        return FilesFacadeFactory.getInstance().getImplementation(path);
    }
    private FilesFacadeBackend getImplementation(AbstractFileModel fileModel){
        return getImplementation(fileModel.getFullPath());
    }

    public byte[] getFileDataAsByteArray(AbstractFileModel fileModel) throws IOException {
        return getImplementation(fileModel).getFileDataAsByteArray(fileModel);
    }

    public InputStream getFileDataAsInputStream(AbstractFileModel fileModel) throws IOException {
        return getImplementation(fileModel).getFileDataAsStream(fileModel);
    }

    public AbstractFileModel getFile(String path) throws IOException {
        return getImplementation(path).getFile(path);
    }

}
