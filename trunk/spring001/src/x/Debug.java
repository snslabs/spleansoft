package x;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.xpath.HtmlUnitXPath;
import org.jaxen.JaxenException;
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
        HtmlElement element = getCurrentPage(scriptContext).getDocumentHtmlElement();
        try {
            HtmlUnitXPath xPath = new HtmlUnitXPath(xpath);
            final List list = xPath.selectNodes(element);
            System.out.println(">> SPAMMED: "+list);
        }
        catch (JaxenException e) {
            e.printStackTrace();
        }
        return null;
    }
}
