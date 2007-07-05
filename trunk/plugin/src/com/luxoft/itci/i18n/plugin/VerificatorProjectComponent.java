package com.luxoft.itci.i18n.plugin;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.usages.*;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiFile;
import com.luxoft.itci.i18n.plugin.scanner.ScanResult;
import com.luxoft.itci.i18n.plugin.scanner.Scanner;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.ArrayList;

public class VerificatorProjectComponent implements ProjectComponent {
    private Project project;
    public VerificatorProjectComponent(Project project) {
        this.project = project;
    }

    public void initComponent() {
        // TODO: insert component initialization logic here
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "VerificatorProjectComponent";
    }

    public void projectOpened() {
        System.out.println("Project opened...");
    }

    public void projectClosed() {
        // called when project is being closed
        System.out.println("Project closed");
    }

    public VerificatorSettingsDialog showVerificatorSettings() {
        VerificatorSettingsDialog vsd = new VerificatorSettingsDialog();
        vsd.pack();
        vsd.setVisible(true);
        return vsd;
    }

    private void scanFile(VirtualFile vf, StringBuffer sb) {
        if (vf.isDirectory()) {
            for (VirtualFile vfc : vf.getChildren()) {
                scanFile(vfc, sb);
            }
        }
        else {
            if(vf.getFileType().getName().equals("JAVA") || vf.getFileType().getName().equals("JSP") || vf.getFileType().getName().equals("Properties")){
                String str = vf.getPath() + ":" + vf.getLength() + ":" + vf.getFileType().getName();
                sb.append(str).append("\n");
            }
        }
    }

    private Usage[] buildResults(List<ScanResult> scanResults, Project project) {
        final PsiManager psiManager = PsiManager.getInstance(project);
        //Usage[] foundUsages =  new Usage[]{ new UsageInfo2UsageAdapter(usageInfo)};
        ArrayList<Usage> foundUsages = new ArrayList<Usage>();
        for (ScanResult scanResult : scanResults) {
            final VirtualFile file = scanResult.getFile();
            final PsiFile psiFile = psiManager.findFile(file);
            if (psiFile != null) {
                try {
                    final MyUsageInfo usageInfo = new MyUsageInfo(psiFile, scanResult.getStartOffset(),
                            scanResult.getEndOffset());
                    usageInfo.setTooltipText(scanResult.getTypeDesc());
                    final UsageInfo2UsageAdapter usg = new UsageInfo2UsageAdapter(usageInfo);

                    foundUsages.add(usg);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Cannot find psi element for file: " + file.getPath());
            }
        }
        return foundUsages.toArray(new Usage[foundUsages.size()]);
    }

    public void executeAdvancedSearch() {
        final VerificatorSettingsDialog dialog = showVerificatorSettings();
        if(dialog.getResult()){
            Scanner scanner = new Scanner();
            scanner.setRegexpScanners(dialog.getRegexpScanners());
            scanner.setRootFolder(project.getBaseDir());
            // perform in separate thread with progress bar
            scanner.scan();
            // ~

            // displaying results
            // 1. search for
            UsageTarget[] searchedFor = new UsageTarget[]{new MyUsageTarget(dialog.getRegexp())};
            // 2. found usages
            Usage[] foundUsages = buildResults(scanner.getScanResults(), project);
            // 3. displaying found usages toolwindow
            final UsageViewManager uvm = UsageViewManager.getInstance(project);
            UsageViewPresentation presentation = new UsageViewPresentation();
            uvm.showUsages(searchedFor, foundUsages, presentation);
        }

    }
}
