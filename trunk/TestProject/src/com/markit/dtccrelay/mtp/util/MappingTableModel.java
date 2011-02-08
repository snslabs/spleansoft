package com.markit.dtccrelay.mtp.util;

import org.springframework.util.StringUtils;

import javax.swing.table.TableModel;
import javax.swing.event.TableModelListener;
import javax.swing.event.TableModelEvent;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Set;

public class MappingTableModel implements TableModel {
    private List<Entry> srcMappingEntries;
    private List<PlainEntry> mappingEnties = new LinkedList<PlainEntry>();

    private List<TableModelListener> listners = new ArrayList<TableModelListener>();

    public MappingTableModel(List<Entry> mappingEntries) {
        this.srcMappingEntries = mappingEntries;
    }

    public void setFilter(String prouctType, String transactionType){
        mappingEnties.clear();
        Condition condition = new Condition(prouctType, transactionType);
        for (Entry me : srcMappingEntries) {
            String internalField = me.getInternalField();
            DBMapping db = null;
            Set<Condition> conditions = me.getDb().keySet();
            for (Condition c : conditions) {
                if(c.comply(condition)){
                    db = me.getDb().get(c);
                    break;
                }
            }

            String xpath = null;
            conditions = me.getXpaths().keySet();
            for (Condition c : conditions) {
                if(c.comply(condition)){
                    xpath = me.getXpaths().get(c);
                    break;
                }
            }

            if(StringUtils.hasText(xpath)  || db!=null){
                mappingEnties.add(new PlainEntry(internalField, xpath, db));
            }
        }

        for(TableModelListener tml :  listners){
            tml.tableChanged(new TableModelEvent(this,0,this.getRowCount()-1));
        }

    }


    public int getRowCount() {
        return mappingEnties.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0: return "DataBase";
            case 1: return "Internal Field";
            case 2: return "XPath";
            default: return "NFI";
        }
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        PlainEntry entry = mappingEnties.get(rowIndex);
        switch(columnIndex){
            case 0:
                return entry.db!=null?entry.db.toString():"N/A";
            case 1:
                return entry.getInternalField();
            case 2:
                return entry.xpath!=null?entry.xpath:"N/A";
            default:
                return "NFI";
        }
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    public void addTableModelListener(TableModelListener l) {
        listners.add(l);
    }

    public void removeTableModelListener(TableModelListener l) {
        listners.remove(l);
    }
}
