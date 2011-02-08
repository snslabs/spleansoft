package ru.snslabs.plugin.search;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.vfs.VirtualFile;
import ru.snslabs.plugin.search.scanner.ScanResult;
import ru.snslabs.plugin.search.scanner.impl.RegexpScanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScannerComponent {
    private VirtualFile rootFolder;
    private List<FileType> acceptFileTypes = new ArrayList<FileType>();
    private List<String> excludedDirs = Arrays.asList(".svn",".","..","classes");
    private List<RegexpScanner> regexpScanners;
    private List<ScanResult> scanResults;

    public ScannerComponent() {
        scanResults = new ArrayList<ScanResult>();

    }

    public void scan(){
        if(rootFolder != null){
            processFile(rootFolder);
        }
    }

    private void processFile(VirtualFile virtualFile){
        if(virtualFile.isDirectory()){
            if(!excludedDirs.contains(virtualFile.getName())){
                for(VirtualFile file: virtualFile.getChildren()){
                    processFile(file);
                }
            }
        }
        else{
            if(acceptFileTypes.contains(virtualFile.getFileType())){
                scanFile(virtualFile);
            }
        }
    }

    /**
     * Scanning single virtual file with all regexp scanners
     * @param virtualFile
     */
    private void scanFile(VirtualFile virtualFile) {
        for (RegexpScanner regexpScanner : regexpScanners) {
            try{
                scanResults.addAll(regexpScanner.scan(virtualFile.getInputStream(), virtualFile.getLength(), virtualFile));
            }
            catch(Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    public VirtualFile getRootFolder() {
        return rootFolder;
    }

    public void setRootFolder(VirtualFile rootFolder) {
        this.rootFolder = rootFolder;
    }

    public List<FileType> getAcceptFileTypes() {
        if(acceptFileTypes == null){
            acceptFileTypes = new ArrayList<FileType>();
        }
        return acceptFileTypes;
    }

    public void setAcceptFileTypes(List<FileType> acceptFileTypes) {
        this.acceptFileTypes = acceptFileTypes;
    }

    public List<String> getExcludedDirs() {
        if(excludedDirs == null){
            excludedDirs = new ArrayList<String>();
        }
        return excludedDirs;
    }

    public void setExcludedDirs(List<String> excludedDirs) {
        this.excludedDirs = excludedDirs;
    }

    public List<RegexpScanner> getRegexpScanners() {
        return regexpScanners;
    }

    public void setRegexpScanners(List<RegexpScanner> regexpScanners) {
        this.regexpScanners = regexpScanners;
    }

    public List<ScanResult> getScanResults() {
        return scanResults;
    }
}
