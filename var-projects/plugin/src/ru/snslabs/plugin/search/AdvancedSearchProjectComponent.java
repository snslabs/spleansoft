package ru.snslabs.plugin.search;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.usages.*;
import org.jetbrains.annotations.NotNull;
import ru.snslabs.plugin.search.scanner.ScanResult;
import ru.snslabs.plugin.search.ui.MyUsageInfo;
import ru.snslabs.plugin.search.ui.MyUsageTarget;
import ru.snslabs.plugin.search.ui.VerificatorSettingsDialog;

import java.util.ArrayList;
import java.util.List;

public class AdvancedSearchProjectComponent implements ProjectComponent {
    private Project project;
    private VerificatorSettingsDialog verificatorSettingsDialog;

    public AdvancedSearchProjectComponent(Project project) {
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
        verificatorSettingsDialog = new VerificatorSettingsDialog();
    }

    public void projectClosed() {
        // called when project is being closed
        System.out.println("Project closed");
//        verificatorSettingsDialog.dispose();
    }

    public VerificatorSettingsDialog showVerificatorSettings() {
        verificatorSettingsDialog.setSize(800,500);
        verificatorSettingsDialog.setLocation(240,207);
        verificatorSettingsDialog.pack();
        verificatorSettingsDialog.setVisible(true);
        return verificatorSettingsDialog;
    }

/*
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
*/

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
            ScannerComponent scannerComponent = new ScannerComponent();
            scannerComponent.setRegexpScanners(dialog.getRegexpScanners());
            scannerComponent.setRootFolder(project.getBaseDir());
            scannerComponent.setAcceptFileTypes(dialog.getAcceptedFileTypes());
            // perform in separate thread with progress bar
            scannerComponent.scan();
            // ~

            // displaying results
            // 1. search for
            UsageTarget[] searchedFor = new UsageTarget[]{new MyUsageTarget("Результат!")};
            // 2. found usages
            Usage[] foundUsages = buildResults(scannerComponent.getScanResults(), project);
            // 3. displaying found usages toolwindow
            final UsageViewManager uvm = UsageViewManager.getInstance(project);
            UsageViewPresentation presentation = new UsageViewPresentation();
            uvm.showUsages(searchedFor, foundUsages, presentation);
        }

    }
}
