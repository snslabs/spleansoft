package ru.sns.ui.model;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

public class OptionsTableModel extends AbstractTableModel {
    List<Ticker> data;


    public OptionsTableModel() {
        data = new ArrayList<Ticker>();
    }

    public OptionsTableModel(List<Ticker> data) {
        this.data = data;
    }

    public int getRowCount() {
        return data.size();
    }

    public int getColumnCount() {
        return 13;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return ((Ticker)data.get(rowIndex)).getValueByIndex(columnIndex);
    }


    public List<Ticker> getData() {
        return data;
    }


    public void setData(List<Ticker> data) {
        this.data = data;
    }
}
