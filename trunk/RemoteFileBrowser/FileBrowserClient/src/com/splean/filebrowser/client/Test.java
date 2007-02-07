package com.splean.filebrowser.client;

import com.splean.filebrowser.FileBrowser;
import com.splean.filebrowser.model.FileModel;

import javax.swing.*;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        try {
            UIManager.LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
            for (int i = 0; i < installedLookAndFeels.length; i++) {
                UIManager.LookAndFeelInfo installedLookAndFeel = installedLookAndFeels[i];
                System.out.println(installedLookAndFeel.getName());
                System.out.println(installedLookAndFeel.getClassName());
                System.out.println("-------");
            }

            /*
            FileBrowser fb = new FileBrowser(FileBrowser.MODE_LOCAL);
            List files = fb.getDirectoryList("c:\\");
            for (int i = 0; i < files.size(); i++) {
                Object o =  files.get(i);
                System.out.println(i+"\t"+o);
            }
            fb.setCurrentDirectory( ((FileModel)files.get(2)).getAbsolutePath() );
            System.out.println( fb.getCurrentDirectory() );
            files = fb.getDirectoryList();
            for (int i = 0; i < files.size(); i++) {
                Object o =  files.get(i);
                System.out.println(i+"\t"+o);
            }
            */
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
