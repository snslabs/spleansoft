package com.splean.filebrowser;

import com.splean.filebrowser.model.FileModel;

import java.util.List;
import java.rmi.RemoteException;

public interface IFileBrowser {
    /**
     * Вовзращает список в указанной директории
     * @param path относительный или абсолютный путь к директории
     * @return список файлов в указанной директории
     * @throws java.rmi.RemoteException
     */
    public List getDirectoryList(String path) throws RemoteException;

    public List getDirectoryList() throws RemoteException;
    /**
     * Смена текущей директории
     * @param path относительный или абсолютный путь к директории
     * @return true - если смена директории произошла удачно, false - если нет
     * @throws RemoteException
     */
    public Boolean setCurrentDirectory(String path) throws RemoteException;

    public FileModel getCurrentDirectory() throws RemoteException;
}
