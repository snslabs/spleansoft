package ru.snslabs.plugin.search.scanner;

import java.io.InputStream;
import java.util.List;

public interface Scanner {
    List<? extends ScanResult> scan(InputStream inputStream, long length, Object ref);
}
