package x;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.xpath.XPathUtils;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ops.HtmlScriptOperation;
import ru.snslabs.clicker.script.ops.ScriptFailure;

import java.util.List;

public class Debug extends HtmlScriptOperation {
    private String xpath;

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public Object execute(ScriptContext scriptContext) throws ScriptFailure {
        HtmlElement element = getCurrentPage(scriptContext).getDocumentElement();
        try {
            final List list = XPathUtils.getByXPath(element, xpath);
            System.out.println(">> SPAMMED: "+list);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
