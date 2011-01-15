package com.splean.filebrowser.client.ui;

import com.splean.filebrowser.FileBrowser;

import javax.swing.*;
import java.awt.*;

public class FBFrame extends JFrame {
    private FileList fl;
    private JTextField cmd;
    private JButton goButton;
    private JComboBox drives;
    private FileBrowser fb;
    public FBFrame() throws HeadlessException {
        super();

        // подготавливаем Look And Feel

        try {
            fb = new FileBrowser(FileBrowser.MODE_LOCAL);
            //  попробуем переключиться на виндовый лук энд фил
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }


        this.setTitle("File Browser 1.0");
        this.getContentPane().setLayout(new BorderLayout());

        fl = new FileList(fb);
        JScrollPane scrollPane = new JScrollPane(fl);
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);


        drives = new JComboBox(new Object[]{"C","D","E"});

        this.getContentPane().add(drives, BorderLayout.NORTH);

        JPanel bottom = new JPanel(new BorderLayout());
        cmd = new JTextField();
        goButton = new JButton("Go");
        bottom.add(cmd, BorderLayout.CENTER);
        bottom.add(goButton, BorderLayout.EAST);

        this.getContentPane().add(bottom, BorderLayout.SOUTH);

    }
}
