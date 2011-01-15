package com.splean.filebrowser;

import com.splean.filebrowser.ejb.SessionFileBrowserHome;
import com.splean.filebrowser.model.FileModel;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import javax.ejb.*;
import java.util.List;
import java.rmi.RemoteException;

/**
 * Facade класс для работы с EJB файл-браузером
 */
public class FileBrowser {
    /** локальный режим работы - не через EJB */
    public static final int MODE_LOCAL = 0;
    /** удалённый режим работы - через EJB */
    public static final int MODE_EJB = 1;
    /** JNDI имя EJB */
    private static String ejbJNDI = "ejb/SessionFileBrowser";
    /** URL EJB сервера */
    private static String ejbContainerURL = "iiop://localhost:2809/";
    /** режим работы: MODE_LOCAL или MODE_EJB */
    private int mode = MODE_LOCAL;
    /** реализация */
    private IFileBrowser impl;

    public FileBrowser(int mode) {
        if(mode != MODE_LOCAL && mode != MODE_EJB){
            throw new IllegalArgumentException("Mode should be 1 or 2, and not "+mode);
        }
        this.mode = mode;
    }

    public List getdirectoryList() throws Exception{
        return getImplementer().getDirectoryList();
    }

    public List getDirectoryList(String path) throws Exception {
        return getImplementer().getDirectoryList(path);
    }

    public Boolean setCurrentDirectory(String path) throws Exception {
        return getImplementer().setCurrentDirectory(path);
    }

    public List getDirectoryList() throws Exception {
        return getImplementer().getDirectoryList();
    }

    private IFileBrowser getImplementer() {
        if(impl != null){
            return impl;
        }
        if(mode == MODE_LOCAL){
            impl = new SessionFileBrowserStub();
        }
        else{
            try {
                InitialContext ic = new InitialContext();
                Object ref = ic.lookup(ejbJNDI);
                impl = ((SessionFileBrowserHome) PortableRemoteObject.narrow(ref, SessionFileBrowserHome.class)).create();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return impl;
    }

    public FileModel getCurrentDirectory() throws Exception {
        return getImplementer().getCurrentDirectory();
    }

    static class SessionFileBrowserStub implements IFileBrowser{
        /** директория по умолчанию */
        private static final String DEFAULT_DIR = "c:\\";
        private FileUtil fileUtil = new FileUtil();

        public SessionFileBrowserStub() {
        }

        public List getDirectoryList(String path) throws EJBException {
            return fileUtil.getDirectoryList(path);
        }

        public List getDirectoryList() throws RemoteException {
            return fileUtil.getDirectoryList();
        }

        public Boolean setCurrentDirectory(String path) throws EJBException {
            return fileUtil.setCurrentDirectory(path)? Boolean.TRUE : Boolean.FALSE;
        }

        public FileModel getCurrentDirectory() throws RemoteException {
            return fileUtil.getCurrentDirectory();
        }
    }
}
