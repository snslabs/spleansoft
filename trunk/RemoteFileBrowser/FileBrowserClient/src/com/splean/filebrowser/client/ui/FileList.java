package com.splean.filebrowser.client.ui;

import com.splean.filebrowser.FileBrowser;
import com.splean.filebrowser.model.FileModel;

import javax.swing.*;
import java.util.Collections;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class FileList extends JList {
    private FileBrowser fb;

    public FileList(FileBrowser fb) {
        this.fb = fb;
        this.setModel( updateModel() );
        this.addKeyListener(
                new KeyListener() {
                    public void keyPressed(KeyEvent e) {}
                    public void keyReleased(KeyEvent e) {
                        if(e.getKeyCode() == 10){
                            changeDir();
                        }
                    }
                    public void keyTyped(KeyEvent e) {}
                }
        );
    }

    private ListModel updateModel(){
        try {
            return new FileListModel(fb.getdirectoryList());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new FileListModel(Collections.EMPTY_LIST);
        }
    }

    private void changeDir() {
        System.out.println("Changing dir");
        try{
            FileModel fileModel = ((FileModel) getModel().getElementAt(this.getSelectedIndex()));
            if(fileModel.getType() != FileModel.TYPE_DIR){
                return;
            }
            String dir = fileModel.getAbsolutePath();
            if(fb.setCurrentDirectory(dir).booleanValue()){
                this.setModel( updateModel() );
            }
            this.setSelectedIndex(0);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
