package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ValueResolver;
import ru.snslabs.clicker.script.ops.HtmlScriptOperation;
import ru.snslabs.clicker.script.ops.ScriptFailure;

public class GetAttribute extends HtmlScriptOperation implements ValueResolver {

    private Object htmlEl;
    private Object attributeName;

    public Object execute(ScriptContext scriptContext) throws ScriptFailure {
        return resolve(scriptContext);
    }


    public Object getHtmlEl() {
        return htmlEl;
    }

    public void setHtmlEl(Object htmlEl) {
        this.htmlEl = htmlEl;
    }

    public Object getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(Object attributeName) {
        this.attributeName = attributeName;
    }

    public Object resolve(ScriptContext scriptContext) {
        HtmlElement el = resolveToHtmlElement(htmlEl, scriptContext);
        String attrName = resolveToString(attributeName, scriptContext);
        return el.getAttribute(attrName);
    }
}