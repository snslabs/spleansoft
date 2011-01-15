package com.splean.filebrowser;

import com.splean.filebrowser.model.FileModel;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.io.File;

/**
 * Утилитный класс для работы с файловой системой
 */
public class FileUtil {

    private File currentDirectory = new File("c:\\");
    /**
     * получаем список содержимого директории в виде моделей файлов
     * @param path
     * @return  список содержимого директорий в виде коллекции моделей файлов
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
     * Устанавливает текущую папку
     * @param absolutePath абсолютный путь
     * @return true - если папка существует и успешно установлена, false - если нет
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
     * Метод преобразует массив объектов файлов в коллекцию моделей файлов и сортирует по алфавиту (директории в начало)
     * @param files массив файлов
     * @return отсортированный список моделей файлов
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
     * Метод преобразует объект файла в модель файла
     * @param file объект файла
     * @return модель файла
     */
    private FileModel wrapFile(File file) {
        return new FileModel(file.getAbsolutePath(), file.getName() ,file.isDirectory()?FileModel.TYPE_DIR:FileModel.TYPE_FILE);
    }

    /**
     * Метод возвращает список файлов в текущей директории
     * @return список файлов текущей директории
     */
    public List getDirectoryList() {
        return getDirectoryList(this.currentDirectory.getAbsolutePath());
    }

    /**
     * @return Модель текущей директории
     */
    public FileModel getCurrentDirectory(){
        return wrapFile(currentDirectory);
    }
}
