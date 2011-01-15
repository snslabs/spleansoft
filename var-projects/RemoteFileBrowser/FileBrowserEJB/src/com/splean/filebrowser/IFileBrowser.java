package com.splean.filebrowser;

import com.splean.filebrowser.model.FileModel;

import java.util.List;
import java.rmi.RemoteException;

public interface IFileBrowser {
    /**
     * ���������� ������ � ��������� ����������
     * @param path ������������� ��� ���������� ���� � ����������
     * @return ������ ������ � ��������� ����������
     * @throws java.rmi.RemoteException
     */
    public List getDirectoryList(String path) throws RemoteException;

    public List getDirectoryList() throws RemoteException;
    /**
     * ����� ������� ����������
     * @param path ������������� ��� ���������� ���� � ����������
     * @return true - ���� ����� ���������� ��������� ������, false - ���� ���
     * @throws RemoteException
     */
    public Boolean setCurrentDirectory(String path) throws RemoteException;

    public FileModel getCurrentDirectory() throws RemoteException;
}
