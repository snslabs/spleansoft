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
    /** ���������� �� ��������� */
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
     * ���������� ������ � ��������� ����������
     *
     * @param path ������������� ��� ���������� ���� � ����������
     * @return ������ ������ � ��������� ����������
     */
    public List getDirectoryList(String path) throws EJBException {
        return fileUtil.getDirectoryList(path);
    }

    /**
     * ����� ������� ����������
     *
     * @param path ������������� ��� ���������� ���� � ����������
     * @return true - ���� ����� ���������� ��������� ������, false - ���� ���
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
