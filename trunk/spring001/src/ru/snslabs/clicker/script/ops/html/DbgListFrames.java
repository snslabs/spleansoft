package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ops.HtmlScriptOperation;

import java.io.FileWriter;
import java.io.IOException;

public class DbgListFrames extends HtmlScriptOperation {

    public Object execute(ScriptContext scriptContext) {
        Page currentPage = getCurrentPage(scriptContext);
        dbg((HtmlPage) currentPage);
        return false;
    }

    private void dbg(HtmlPage currentPage) {
        for(Object o : currentPage.getFrames()){
            System.out.println(o.toString());
        }
    }

}