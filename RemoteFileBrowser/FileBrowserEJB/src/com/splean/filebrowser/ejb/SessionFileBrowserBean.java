package com.splean.filebrowser.ejb;

import com.splean.filebrowser.FileUtil;
import com.splean.filebrowser.model.FileModel;

import javax.ejb.SessionBean;
import javax.ejb.CreateException;
import javax.ejb.SessionContext;
import javax.ejb.EJBException;
import java.util.List;
import java.io.File;
import java.rmi.RemoteException;

public class SessionFileBrowserBean implements SessionBean {
    /** директория по умолчанию */
    private static final String DEFAULT_DIR = "c:\\";
    private FileUtil fileUtil = new FileUtil();

    public SessionFileBrowserBean() {
    }

    public void ejbCreate() throws CreateException {
    }

    public void setSessionContext(SessionContext sessionContext) throws EJBException {
    }

    public void ejbRemove() throws EJBException {
    }

    public void ejbActivate() throws EJBException {
    }

    public void ejbPassivate() throws EJBException {
    }

    /**
     * Вовзращает список в указанной директории
     *
     * @param path относительный или абсолютный путь к директории
     * @return список файлов в указанной директории
     */
    public List getDirectoryList(String path) throws EJBException {
        return fileUtil.getDirectoryList(path);
    }

    /**
     * Смена текущей директории
     *
     * @param path относительный или абсолютный путь к директории
     * @return true - если смена директории произошла удачно, false - если нет
     */
    public Boolean setCurrentDirectory(String path) throws EJBException {
        return fileUtil.setCurrentDirectory(path)? Boolean.TRUE : Boolean.FALSE;
    }

    public List getDirectoryList() throws EJBException {
        return fileUtil.getDirectoryList();
    }

    public FileModel getCurrentDirectory() throws EJBException {
        return fileUtil.getCurrentDirectory();
    }


}
