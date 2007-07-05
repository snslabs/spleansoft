package com.luxoft.itci.i18n.plugin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.regex.Pattern;

public class ScannerUI {
    private JComboBox mainRegexp;
    private JLabel mainRegexpStatus;
    private JPanel mainPane;
    private JButton addButton;
    private JButton delButton;

    public ScannerUI() {
        mainRegexp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                validate();
            }
        });
    }

    private void validate() {
        try{
            Pattern.compile(mainRegexp.getSelectedItem().toString());
            mainRegexpStatus.setForeground(Color.BLACK);
            mainRegexpStatus.setText("Valid");
        }
        catch(Exception e){
            mainRegexpStatus.setText("Invalid");
            mainRegexpStatus.setForeground(Color.RED);
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("test");
        ScannerUI scannerUI = new ScannerUI();
        frame.setContentPane( scannerUI.mainPane );
        frame.setVisible(true);
    }
}
