package com.splean.web.file;

import java.io.File;
import java.util.*;

abstract class AbstractFilesImpl implements FilesFacadeBackend {

    protected static Map<String, Set<File>> clipBoards = new HashMap<String, Set<File>>();
    
    public final List<AbstractFileModel> getClipboard(String clipboardId){
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

    public void init(String path) throws FileBrowserException {
    }
}
