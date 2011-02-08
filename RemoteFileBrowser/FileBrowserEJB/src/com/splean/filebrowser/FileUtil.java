package com.splean.filebrowser;

import com.splean.filebrowser.model.FileModel;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.io.File;

/**
 * ��������� ����� ��� ������ � �������� ��������
 */
public class FileUtil {

    private File currentDirectory = new File("c:\\");
    /**
     * �������� ������ ����������� ���������� � ���� ������� ������
     * @param path
     * @return  ������ ����������� ���������� � ���� ��������� ������� ������
     */
    public List getDirectoryList(String path) {
        File file = new File(path);
        System.out.println(file);
        if(file.exists()){
            List list = wrap(file.listFiles());
            if(file.getParentFile() != null){
                list.add(0, new FileModel(file.getParentFile().getAbsolutePath(), "..", FileModel.TYPE_DIR));
            }
            return list;
        }
        else{
            System.out.println("filepath does not exists!");
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * ������������� ������� �����
     * @param absolutePath ���������� ����
     * @return true - ���� ����� ���������� � ������� �����������, false - ���� ���
     */
    public boolean setCurrentDirectory(String absolutePath){
        File currentDirectory_ = new File( absolutePath);
        if(currentDirectory_.exists()){
            currentDirectory  = currentDirectory_;
            return true;
        }
        return false;
    }

    /**
     * ����� ����������� ������ �������� ������ � ��������� ������� ������ � ��������� �� �������� (���������� � ������)
     * @param files ������ ������
     * @return ��������������� ������ ������� ������
     */
    private List wrap(File[] files){
        List l = new ArrayList();
        for (int i = 0; i < files.length; i++) {
            l.add(wrapFile(files[i]));
        }
        Collections.sort(l);
        return l;
    }

    /**
     * ����� ����������� ������ ����� � ������ �����
     * @param file ������ �����
     * @return ������ �����
     */
    private FileModel wrapFile(File file) {
        return new FileModel(file.getAbsolutePath(), file.getName() ,file.isDirectory()?FileModel.TYPE_DIR:FileModel.TYPE_FILE);
    }

    /**
     * ����� ���������� ������ ������ � ������� ����������
     * @return ������ ������ ������� ����������
     */
    public List getDirectoryList() {
        return getDirectoryList(this.currentDirectory.getAbsolutePath());
    }

    /**
     * @return ������ ������� ����������
     */
    public FileModel getCurrentDirectory(){
        return wrapFile(currentDirectory);
    }
}
