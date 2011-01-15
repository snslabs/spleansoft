package ru.snslabs.plugin.search.scanner;

import com.intellij.openapi.vfs.VirtualFile;

public class ScanResult extends AbstractScanResult {
    private VirtualFile file;

    protected ScanResult(Object ref, int line, String result, ScannerDescription typeDesc, int startOffset, int endOffset) {
            this.file = (VirtualFile) ref;
            this.line = line;
            this.result = result;
            this.typeDesc = typeDesc;
            this.startOffset = startOffset;
            this.endOffset = endOffset;
    }

    public VirtualFile getFile() {
        return file;
    }

    public void setFile(VirtualFile file) {
        this.file = file;
    }

}
