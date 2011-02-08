package ru.snslabs.plugin.search.scanner.impl;

import ru.snslabs.plugin.search.scanner.Scanner;
import ru.snslabs.plugin.search.scanner.ScanResult;

import java.util.List;
import java.io.InputStream;

public class RegexpScannerWithSubst implements Scanner {
    private String desc;

    public RegexpScannerWithSubst(String desc) {
        this.desc = desc;
    }

    public List<? extends ScanResult> scan(InputStream inputStream, long length, Object ref) {
        return null;
    }

    public String getDesc() {
        return desc;
    }
}
