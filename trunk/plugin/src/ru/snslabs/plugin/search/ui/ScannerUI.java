package ru.snslabs.plugin.search.ui;

import ru.snslabs.plugin.search.ui.GBC;
import ru.snslabs.plugin.search.ui.FilterForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.regex.Pattern;

public class ScannerUI {
    private JComboBox mainRegexp;
    private JLabel mainRegexpStatus;
    private JPanel mainPanel;
    private JButton addButton;
    private JButton delButton;
    private JPanel filterPanel;
    private java.util.List<FilterForm> filterForms  = new ArrayList<FilterForm>();
    private static final String ACTION_ADD_FILTER = "action.addFilter";

    public ScannerUI() {
        mainRegexp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                validate();
            }
        });
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                addFilter();
            }
        });
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                removeFilter();
            }
        });
    }

    private void removeFilter() {
        for(FilterForm form :filterForms){
            filterPanel.remove(form.getMainPanel());
        }

        if(filterForms.size()>0){
            filterForms.remove(filterForms.size()-1);
        }
        refreshPanels();
    }

    private void addFilter() {
        for(FilterForm form :filterForms){
            filterPanel.remove(form.getMainPanel());
        }

        filterForms.add(new FilterForm());
        refreshPanels();
    }

    private void refreshPanels() {
        for(int index = 0; index < filterForms.size(); index++){
            FilterForm form = filterForms.get(index);
            filterPanel.add(form.getMainPanel(),new GBC(0,index).setWeight(1,(index==filterForms.size()-1)?1:0).
                    setAnchor(GridBagConstraints.NORTH).setFill(GridBagConstraints.HORIZONTAL) );
            System.out.println("index="+index);
        }
        filterPanel.updateUI();
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        ScannerUI scannerUI = new ScannerUI();
        frame.setContentPane( scannerUI.mainPanel);
        frame.setVisible(true);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public String getRegexp() {
        return mainRegexp.getSelectedItem().toString();
    }

    public java.util.List<FilterForm> getFilterForms() {
        return filterForms;
    }
}
