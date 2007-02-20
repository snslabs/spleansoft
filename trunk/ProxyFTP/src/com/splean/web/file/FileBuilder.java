package com.splean.web.file;

import java.io.File;
import java.io.FileFilter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Thread safe file model builder
 */
public class FileBuilder {

    public static List<AbstractFileModel> getFileModels(String dir) throws FileBrowserException {
        List<AbstractFileModel> fileModelz = getFileModels(getFiles(dir));
        final File parentFile = new File(dir).getParentFile();
        if(parentFile != null){
            FileSystemFileModel model = new FileSystemFileModel(parentFile);
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

    public static List<AbstractFileModel> getFileModels(Collection<File> filez) {
        List<AbstractFileModel> fileModelz = new ArrayList<AbstractFileModel>();
        for (File file : filez) {
            fileModelz.add(new FileSystemFileModel(file));
        }
        return fileModelz;
    }
}
