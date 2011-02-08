package ru.snslabs.plugin.search.ui;

import com.intellij.ide.projectView.PresentationData;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.usages.UsageTarget;
import org.jetbrains.annotations.Nullable;

public class MyUsageTarget implements UsageTarget {
    private String search;

    public MyUsageTarget(String search) {
        this.search = search;
    }

    public void findUsages() {
        // simple result - cannot be executed
        System.out.println("call MyUsageTarget.findUsages()");
    }

    public void findUsagesInEditor(FileEditor editor) {
        System.out.println("call MyUsageTarget.findUsagesInEditor()");
    }

    public boolean isValid() {
        return true;
    }

    public boolean isReadOnly() {
        return true;
    }

    @Nullable
    public VirtualFile[] getFiles() {
        return null;
    }

    public void update() {

    }

    @Nullable
    public String getName() {
        return null;
    }

    @Nullable
    public ItemPresentation getPresentation() {
        return new PresentationData(search,"location", null, null, null);
    }

    public FileStatus getFileStatus() {
        // not a file
        return FileStatus.NOT_CHANGED;
    }

    public void navigate(boolean requestFocus) {
        // cannot navigate
    }

    public boolean canNavigate() {
        return false;
    }

    public boolean canNavigateToSource() {
        return false;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
