package ru.snslabs.plugin.search.ui;

import javax.swing.*;

public class FilterForm {
    private JComboBox filterType;
    private JComboBox parameter;
    private JPanel mainPanel;

    public String getFilterType() {
        return filterType.getSelectedItem().toString();
    }

    public String getParameter() {
        return parameter.getSelectedItem().toString();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
