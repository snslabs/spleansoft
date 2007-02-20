package com.splean.web.file;

import com.splean.web.file.FileModel;
import com.splean.web.file.FileBuilder;

import java.util.*;
import java.io.File;

abstract class AbstractFiles implements FilesFacadeInterface {

    protected static Map<String, Set<File>> clipBoards = new HashMap<String, Set<File>>();
    
    public final List<FileModel> getClipboard(String clipboardId){
        return FileBuilder.getFileModels( getClipBoardSet(clipboardId) );
    }

    protected final Set<File> getClipBoardSet(String id){
        Set<File> f = clipBoards.get(id);
        if(f == null){
            f =  new LinkedHashSet<File>();
            clipBoards.put(id, f);
        }
        return f;
    }

    public final String createClipboard(){
        return String.valueOf(System.currentTimeMillis());
    }



}
