package ru.snslabs.plugin.search.scanner;

/**
 * Listener that pulls every time scanner finds entry
 */
public interface ScanListener {
    void process(Object event);
}
