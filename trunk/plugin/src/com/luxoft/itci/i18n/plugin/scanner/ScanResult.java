package com.luxoft.itci.i18n.plugin.scanner;

import com.intellij.openapi.vfs.VirtualFile;

public class ScanResult {
    private VirtualFile file;
    private int line;
    private String result;
    private String typeDesc;
    private int endOffset;
    private int startOffset;

    public ScanResult() {
        file = null;
        line = -1;
        result = "!!!void!!!";
    }

    public ScanResult(VirtualFile file, int line, String result, String typeDesc, int startOffset, int endOffset) {
        this.file = file;
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

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public int getEndOffset() {
        return endOffset;
    }

    public int getStartOffset() {
        return startOffset;
    }
}
