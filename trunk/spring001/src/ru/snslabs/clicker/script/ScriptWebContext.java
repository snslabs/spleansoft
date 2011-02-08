package ru.snslabs.clicker.script;

import java.util.LinkedHashMap;
import java.util.Map;

public class ScriptWebContext implements ScriptContext {
    // ������ ���-�������
    public static final String WEB_CLIENT = "ru.snslabs.clicker.script.ScriptWebContext.webClient";
    // ������� �������� �������� � ������� ��������
    public static final String CURRENT_PAGE = "ru.snslabs.clicker.script.ScriptWebContext.currentPage";
    // ������������ ������ ������
    public static final String SCRIPT = "ru.snslabs.clicker.script.Script"; 

    private Map<String, Object> attributes = new LinkedHashMap<String, Object>();

    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public <T> T getAttribute(String name, T clazz) {
        return (T) attributes.get(name);
    }

    public Object getAttribute(String name) {
        return attributes.get(name);
    }
}
