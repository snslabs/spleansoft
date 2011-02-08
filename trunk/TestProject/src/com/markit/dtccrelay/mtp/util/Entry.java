package com.markit.dtccrelay.mtp.util;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.LinkedHashMap;

public class Entry implements Comparable {
    String internalField;
    Map<Condition,String> xpaths = new LinkedHashMap<Condition,String>();
    Map<Condition,DBMapping> db = new LinkedHashMap<Condition,DBMapping>();

    public Entry(Field field) {
        this.internalField = field.getName();
    }

    public String toString() {
        return /*db + "\n" + internalField + "\n" + */xpaths+"\n";
    }

    public int compareTo(Object o) {
        return o instanceof Entry ? internalField.compareTo((((Entry) o).internalField)) : 0;
    }

    public int hashCode() {
        return internalField.hashCode();
    }

    public boolean equals(Object obj) {
        return obj instanceof Entry && internalField.equals(((Entry) obj).internalField);
    }

    public void setInternalField(String internalField) {
        this.internalField = internalField;
    }

    public void addXpath(Condition condition, String xpath) {
        this.xpaths.put(condition, xpath);
    }

    public void addAllXpath(Map<Condition, String> xpathsMap) {
        this.xpaths.putAll(xpathsMap);
    }

    public void addDBMapping(Condition condition, DBMapping dbMapping) {
        this.db.put(condition, dbMapping);
    }

    public String getInternalField() {
        return internalField;
    }

    public Map<Condition, String> getXpaths() {
        return xpaths;
    }

    public Map<Condition, DBMapping> getDb() {
        return db;
    }

    public String dbMappingToString(){
        StringBuffer sb = new StringBuffer();

        for (Condition condition : db.keySet()) {
            sb.append("\t").append(condition.toString()).append(" :: ").append(db.get(condition).toString()).append("\n");
        }

        return sb.toString();
    }

    public String dbMappingToString2() {
        StringBuffer sb = new StringBuffer();
        for (Condition condition : db.keySet()) {
            sb.append(",").append(db.get(condition).toString());
        }
        return sb.toString().substring(1);
    }
}
