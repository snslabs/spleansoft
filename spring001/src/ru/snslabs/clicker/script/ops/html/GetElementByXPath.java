package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.xpath.HtmlUnitXPath;
import org.jaxen.JaxenException;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ValueResolver;
import ru.snslabs.clicker.script.ops.HtmlScriptOperation;
import ru.snslabs.clicker.script.ops.ScriptFailure;

public class GetElementByXPath extends HtmlScriptOperation implements ValueResolver {

    private String xpath;

    public Object execute(ScriptContext scriptContext) throws ScriptFailure {
        return resolve(scriptContext);
    }

    public Object resolve(ScriptContext scriptContext) {
        HtmlElement element = getCurrentPage(scriptContext).getDocumentHtmlElement();
        try {
            final Object o = find(element, xpath);
            return o;
        }
        catch (JaxenException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Object find(HtmlElement element, String xPath) throws JaxenException {
        HtmlUnitXPath xpath = new HtmlUnitXPath(xPath);
        return xpath.selectSingleNode(element);
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }
}