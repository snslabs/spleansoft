package com.markit.dtccrelay.mtp.util.ui;

import com.markit.dtccrelay.mtp.util.MappingTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MappingForm {
    private JTable mappingTable;
    private JComboBox productType;
    private JComboBox transactionType;
    private JButton refreshButton;
    public JPanel mainPanel;

    public MappingForm(TableModel tableModel) {
        mappingTable.setModel( tableModel );
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((MappingTableModel)mappingTable.getModel()).setFilter(
                        productType.getSelectedItem().toString(),
                        transactionType.getSelectedItem().toString()
                );
                mainPanel.updateUI();
            }
        });
    }

    public void setData(MappingFormBean data) {
        data.setProductType(productType.getSelectedItem().toString());
        data.setTransactionType(transactionType.getSelectedItem().toString());
    }

    public void getData(MappingFormBean data) {
        productType.setSelectedItem(data.getProductType());
        transactionType.setSelectedItem(data.getTransactionType());
    }

    public boolean isModified(MappingFormBean data) {
        return !productType.getSelectedItem().equals(data.getProductType()) ||
                !transactionType.getSelectedItem().equals(data.getTransactionType());
    }
}
