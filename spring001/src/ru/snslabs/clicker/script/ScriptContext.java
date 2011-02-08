package ru.snslabs.clicker.script;

public interface ScriptContext {

    void setAttribute(String name, Object value);

    <T> T getAttribute(String name, T clazz);

    Object getAttribute(String name);

}
