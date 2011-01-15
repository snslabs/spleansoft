package com.markit.dtccrelay.mtp.util;

public class DBMapping {
    String table;
    String column;
    String updatable;

    public DBMapping() {
    }

    public DBMapping(String table, String column, String updatable) {
        this.updatable = updatable;
        this.table = table;
        this.column = column;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getUpdatable() {
        return updatable;
    }

    public void setUpdatable(String updatable) {
        this.updatable = updatable;
    }

    public String toString() {
        return table+"."+column;
    }
}
