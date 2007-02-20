package com.splean.web.file;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;

class FilesFacadeFileSystemImpl extends AbstractFilesImpl {

    /**
     * returns content of the directory with default sorting (dirs first, alphabetically)
     * @param path path to directory
     * @return ordered collection of models
     * @throws com.splean.web.file.FileBrowserException in case of unable to get list of files
     */
    public List<AbstractFileModel> dir(String path) throws FileBrowserException{
        final List<AbstractFileModel> list = FileBuilder.getFileModels(path);
        Collections.sort(list);
        return list;
    }

    /**
     * Deletes file
     * @param path absolute path to file
     * @return error message in case of error, null if succeded
     * @throws com.splean.web.file.FileBrowserException in case of fatal error
     */
    public String deleteFile(String path) throws FileBrowserException{
        File f = new File(path);
        String res = null;
        if(!f.exists()){
            res = "file not found!";
        }
        else{
            try{
                f.delete();
            }
            catch(Exception e){
                res = "cannot delete file\n"+e.getMessage();
            }
        }
        return res;
    }

    /**
     * Copies file to clipboard
     * @param filePath path to file
     * @param clipboardId clipboardId
     * @return null if succeded or error message otherwise.
     */
    public String copyFile(String filePath, String clipboardId){
        final File file = new File(filePath);
        if(!file.exists()){
            return "File not found!";
        }
        else{
            getClipBoardSet(clipboardId).add(file);
        }
        return null;
    }

    /**
     * Stores provided byte array into file with provided path
     * @param path path to newly created file
     * @param fileData binary data of file content
     * @throws java.io.IOException in case of error
     */
    public void uploadFile(String path, byte[] fileData) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(fileData);
        fos.close();
    }

    public String pasteFiles(String dstDirPath, String clipboardId){
        File dstDir = new File(dstDirPath);
        if(!dstDir.exists()){
            return "Directory "+dstDirPath + " not found!";
        }
        if(!dstDir.isDirectory()){
            return dstDirPath+" is not a directory";
        }
        return copyFiles(getClipBoardSet(clipboardId), dstDir);
    }

    private String copyFiles(Set<File> clipBoardSet, File dstDir) {
        try{
            // copying files from set to dst dir.
            for (File file : clipBoardSet) {
                FileUtils.copyFileToDirectory(file, dstDir);
            }
        }
        catch(Exception e){
            return "Cannot copy files:\n"+e.getMessage();
        }
        // clearing clipboard
        clipBoardSet.clear();
        return null;
    }

    /**
     * returns file data as byte array
     * @param fileModel file model
     * @return byte array with file data
     * @throws IOException in case of error
     */
    public byte[] getFileDataAsByteArray(AbstractFileModel fileModel) throws IOException {
        return FileUtils.readFileToByteArray(fileModel.getFile());
    }

    /**
     * returns files data as stream
     * @param fileModel file model
     * @return input stream to read files data
     * @throws IOException in case of error
     */
    public InputStream getFileDataAsStream(AbstractFileModel fileModel) throws IOException {
        return new FileInputStream(fileModel.getFile());
    }


    public AbstractFileModel getFile(String path) {
        final File file = new File(path);
        return new FileSystemFileModel(file);
    }

    public void createDirectore(String path) {

    }
}
