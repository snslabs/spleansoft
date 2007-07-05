package com.luxoft.itci.i18n.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataConstants;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class VerificatorSettingsAction extends AnAction {

    public void actionPerformed(AnActionEvent e) {

        Project project = (Project) e.getDataContext().getData(DataConstants.PROJECT);
        if (project == null) {
            Messages.showMessageDialog("No project loaded. Please open any project and try again.",
                    "Error opening project", Messages.getErrorIcon());
            return;
        }
        VerificatorProjectComponent vpc = project.getComponent(VerificatorProjectComponent.class);

        if (vpc == null) {
            System.out.println("Cannot locate VerificatorProjectComponent :-(");
        }
        else {
            vpc.executeAdvancedSearch();
        }
    }

}
