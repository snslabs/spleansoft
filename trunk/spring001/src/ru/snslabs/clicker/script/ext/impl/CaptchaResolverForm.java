package ru.snslabs.clicker.script.ext.impl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaptchaResolverForm {
    private JFrame owner;
    private JPanel mainPanel;
    private JTextField captchaValue;
    private JButton buttonOk;
    private JPanel imageHolder;
    private Image img;

    private String strCaptchaValue = "";

    public CaptchaResolverForm(JFrame owner) {
        this();
        this.owner = owner;
    }

    public CaptchaResolverForm() {
        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // OK button pressed
                strCaptchaValue = captchaValue.getText();
                owner.setVisible(false);
            }
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public String getCaptchaValue() {
        return strCaptchaValue;
    }

    public JPanel getImageHolder() {
        return imageHolder;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(final Image img) {
        this.img = img;
        imageHolder.setLayout(new BorderLayout());
        imageHolder.add(new JPanel(){
            public void paint(Graphics g) {
                super.paint(g);
                g.drawImage(img, 0,0, owner);
            }
        }, BorderLayout.CENTER );
        imageHolder.updateUI();
        System.out.println("Image was drawn");
    }
}
