package com.luxoft.itci.i18n.plugin;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class HelloWorldApplicationComponent implements ApplicationComponent {
    public HelloWorldApplicationComponent() {
    }

    public void initComponent() {
        // TODO: insert component initialization logic here
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "HelloWorldApplicationComponent";
    }

    public void sayHello() {
        Messages.showMessageDialog("Hello world", "Sample", Messages.getInformationIcon());
    }

}
