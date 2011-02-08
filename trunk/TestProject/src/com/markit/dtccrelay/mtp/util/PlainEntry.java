package com.markit.dtccrelay.mtp.util;

import java.util.Map;
import java.lang.reflect.Field;

public class PlainEntry implements Comparable {
    String internalField;
    String xpath;
    DBMapping db;


    public PlainEntry(String internalField, String xpath, DBMapping db) {
        this.internalField = internalField;
        this.xpath = xpath;
        this.db = db;
    }

    public String toString() {
        return db + "\t <=> " + internalField + " <=> " + xpath;
    }

    public int compareTo(Object o) {
        return o instanceof PlainEntry ? internalField.compareTo((((PlainEntry) o).internalField)) : 0;
    }

    public int hashCode() {
        return internalField.hashCode();
    }


    public boolean equals(Object obj) {
        return obj instanceof PlainEntry && internalField.equals(((PlainEntry) obj).internalField);
    }

    public String getInternalField() {
        return internalField;
    }

    public void setInternalField(String internalField) {
        this.internalField = internalField;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public DBMapping getDb() {
        return db;
    }

    public void setDb(DBMapping db) {
        this.db = db;
    }
}
