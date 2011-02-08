package ru.snslabs.plugin.search.scanner;

public class ScannerDescription {

    private String name;

    public ScannerDescription(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}
