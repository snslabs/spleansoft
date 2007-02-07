package com.splean.filebrowser.client;

import com.splean.filebrowser.client.ui.FBFrame;

import javax.swing.*;

public class FBMain {
    public static void main(String[] args) {
        FBFrame fb = new FBFrame();
        fb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fb.setSize(500,400);
        fb.show();
    }
}
