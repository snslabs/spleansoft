package com.luxoft.itci.i18n.plugin;

import com.luxoft.itci.i18n.plugin.scanner.RegexpScanner;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class VerificatorSettingsDialog extends JDialog {
    private String text;
    private String regexp;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField mainRegexp1;
    private JCheckBox filterCheckBox;
    private JTextField textField2;
    private JCheckBox filterCheckBox2;
    private JTextField textField3;
    private JList list1;
    private JCheckBox filterCheckBox1;
    private JTextField textField4;
    private JCheckBox filterCheckBox4;
    private JTextField textField5;
    private JTextArea textArea1;
    private JTextField regexpField;
    private boolean result = false;

    public VerificatorSettingsDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
// add your code here if necessary
        result = true;
        text = textArea1.getText();
        regexp = regexpField.getText(); 
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        result = false;
        dispose();
    }

    public static void main(String[] args) {
        VerificatorSettingsDialog dialog = new VerificatorSettingsDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public String getText(){
        return text;
    }

    public void setText(String s) {
        text = s;
        textArea1.setText(text);
    }

    public String getRegexp() {
        return regexp;
    }

    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    public List<RegexpScanner> getRegexpScanners() {
        List<RegexpScanner> list = new ArrayList<RegexpScanner>();
        final RegexpScanner scanner = new RegexpScanner("simple value type");
        scanner.setRegexp(regexp);
        list.add(scanner);
        return list;
    }

    public boolean getResult() {
        return result;
    }




}
