package com.luxoft.itci.i18n.plugin.scanner;

import com.intellij.openapi.vfs.VirtualFile;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Scanner {
    private VirtualFile rootFolder;
    private List<String> acceptFileTypes = Arrays.asList("JSP","XML","JavaScript","HTML","JAVA");
    private List<String> excludedDirs = Arrays.asList(".svn",".","..","classes");
    private List<RegexpScanner> regexpScanners;

    private List<ScanResult> scanResults;

    public Scanner() {
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
            if(acceptFileTypes.contains(virtualFile.getFileType().getName())){
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
                scanResults.addAll(regexpScanner.scan(virtualFile));
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

    public List<String> getAcceptFileTypes() {
        if(acceptFileTypes == null){
            acceptFileTypes = new ArrayList<String>();
        }
        return acceptFileTypes;
    }

    public void setAcceptFileTypes(List<String> acceptFileTypes) {
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
