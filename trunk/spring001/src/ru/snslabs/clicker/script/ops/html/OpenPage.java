package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.Page;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ops.HtmlScriptOperation;

import java.io.IOException;

public class OpenPage extends HtmlScriptOperation {

    public OpenPage() {
        System.out.println("OpenPage constructed!!!!! ");
    }

    protected String url;

    public Object execute(ScriptContext scriptContext) {
        try {
            System.out.println("Loading : "+substParams(url,scriptContext));
            Page page = getWebClient(scriptContext).getPage(substParams(url,scriptContext));
            setCurrentPage(page, scriptContext);
            return page;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
