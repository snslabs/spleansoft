package com.splean.web.file;

import com.splean.web.model.FileBuilder;
import com.splean.web.model.FileModel;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.*;

public class FilesFacade {
    static Map<String, Set<File>> clipBoards = new HashMap<String, Set<File>>();

    /**
     * returns content of the directory with default sorting (dirs first, alphabetically)
     * @param path path to directory
     * @return ordered collection of models
     * @throws FileBrowserException in case of unable to get list of files
     */
    public List<FileModel> dir(String path) throws FileBrowserException{
        final List<FileModel> list = FileBuilder.getFileModels(path);
        Collections.sort(list);
        return list;
    }

    /**
     * Deletes file
     * @param path absolute path to file
     * @return error message in case of error, null if succeded
     * @throws FileBrowserException in case of fatal error
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
     * Method generates id for clipboard
     * @return unique clipboard id
     */
    public String createClipboard(){
        return String.valueOf(System.currentTimeMillis());
    }

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

    public List<FileModel>getClipboard(String clipboardId){
        return FileBuilder.getFileModels( getClipBoardSet(clipboardId) );
    }

    private Set<File> getClipBoardSet(String id){
        Set<File> f = clipBoards.get(id);
        if(f == null){
            f =  new LinkedHashSet<File>();
            clipBoards.put(id, f);
        }
        return f;
    }

}
