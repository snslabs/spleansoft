package com.splean.comm.client;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client extends Applet {
    private JTextArea textArea;
    private JButton button;
    private JTextField textField;
    private Engine engine;

    public static final String ACTION_SEND_MSG = "send message";
    public static final String ACTION_SEND_MSG_ENTER = "send message via Enter";
    private JScrollPane scrollablePane;

    public void init() {
        super.init();

        ActionListener sendActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(""+e);
                if( ACTION_SEND_MSG.equals(e.getActionCommand()) ||
                        ACTION_SEND_MSG_ENTER.equals(e.getActionCommand())){
                    engine.sendMsg();
                    textArea.invalidate();
                }
            }
        };

        engine = new Engine();
        this.setSize(500,400);
        Container panel = this;
        panel.setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setDocument( engine.getMsgLog() );

        button = new JButton("Send");
        textField = new JTextField();
        textField.setDocument(engine.getMessageLineDocument());

        button.setActionCommand(ACTION_SEND_MSG);
        button.addActionListener(sendActionListener);
        textField.setActionCommand(ACTION_SEND_MSG);
        textField.addActionListener(sendActionListener);

        scrollablePane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        panel.add(scrollablePane,BorderLayout.CENTER);
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(button, BorderLayout.EAST);
        panel2.add(textField, BorderLayout.CENTER);
        panel.add(panel2,BorderLayout.SOUTH);

        System.out.println("initialized...");
    }

}
