package com.splean.web.file;

import com.splean.web.model.FileModel;
import com.splean.web.model.FileBuilder;

import java.util.List;
import java.util.Collections;

public class FilesFacade {

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

}
