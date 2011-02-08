package ru.snslabs.plugin.search.scanner;

import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class AbstractScanResult {
    protected int line;
    protected String result;
    protected ScannerDescription typeDesc;
    protected int endOffset;
    protected int startOffset;

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
        return typeDesc.getName();
    }

    public int getEndOffset() {
        return endOffset;
    }

    public int getStartOffset() {
        return startOffset;
    }
    
    public static ScanResult createScanResult(@NotNull Object ref , int line, String result, ScannerDescription typeDesc, int startOffset, int endOffset) {
        if(ref instanceof VirtualFile){
            return new ScanResult(ref, line, result, typeDesc, startOffset, endOffset);
        }
        else{
            throw new IllegalArgumentException("Unknown ref object type "+ref.getClass().toString()+"!");
        }
    }
}
