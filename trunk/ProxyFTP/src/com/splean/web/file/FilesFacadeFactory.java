package com.splean.web.file;

public class FilesFacadeFactory {

    private static FilesFacadeFactory instance;

    private FilesFacadeFactory() {
    }

    public static FilesFacadeFactory getInstance(){
        if(instance == null){
            instance = new FilesFacadeFactory();
        }
        return instance;
    }
}
