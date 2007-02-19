package com.splean.web.model;

import com.splean.web.model.FileModel;
import com.splean.web.file.FileBrowserException;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.text.MessageFormat;

/**
 * Thread safe file model builder
 */
public class FileBuilder {

    public static List<FileModel> getFileModels(String dir) throws FileBrowserException {
        List<File> filez = getFiles(dir);
        List<FileModel> fileModelz = new ArrayList<FileModel>();
        for (File file : filez) {
            fileModelz.add(new FileModel(file));
        }
        File dirFile = new File(dir);
        final File parentFile = dirFile.getParentFile();
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
            return Arrays.asList(dirFile.listFiles());
        }
   }
}
