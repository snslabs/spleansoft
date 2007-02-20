package com.splean.web.model;

import com.splean.web.file.FileBrowserException;

import java.io.File;
import java.io.FileFilter;
import java.text.MessageFormat;
import java.util.*;

/**
 * Thread safe file model builder
 */
public class FileBuilder {

    public static List<FileModel> getFileModels(String dir) throws FileBrowserException {
        List<FileModel> fileModelz = getFileModels(getFiles(dir));
        final File parentFile = new File(dir).getParentFile();
        if(parentFile != null){
            FileModel model = new FileModel(parentFile);
            model.setName("..");
            fileModelz.add(model);
        }
        return fileModelz;
    }

    public static List<File> getFiles(String dir) throws FileBrowserException {
        File dirFile = new File(dir);
        if(!dirFile.isDirectory()){
            throw new FileBrowserException(MessageFormat.format("File {0} is not a directory!", dir));
        }
        else{
            FileFilter filter = new FileFilter() {
                public boolean accept(File file) {
                    return !file.isHidden();
                }
            };
            return Arrays.asList(dirFile.listFiles(filter));
        }
   }

    public static List<FileModel> getFileModels(Collection<File> filez) {
        List<FileModel> fileModelz = new ArrayList<FileModel>();
        for (File file : filez) {
            fileModelz.add(new FileModel(file));
        }
        return fileModelz;
    }
}
