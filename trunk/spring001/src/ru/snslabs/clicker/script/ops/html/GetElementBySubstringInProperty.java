package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ValueResolver;
import ru.snslabs.clicker.script.ops.HtmlScriptOperation;
import ru.snslabs.clicker.script.ops.ScriptFailure;

import java.util.Iterator;

public class GetElementBySubstringInProperty extends HtmlScriptOperation implements ValueResolver {

    private Object attrName;
    private Object substring;
    private Object tagName;


    public Object execute(ScriptContext scriptContext) throws ScriptFailure {
        return resolve(scriptContext);
    }

    public Object resolve(ScriptContext scriptContext) {
        HtmlElement element = getCurrentPage(scriptContext).getDocumentElement();
        return find(element,
                resolveToString(attrName, scriptContext),
                resolveToString(substring, scriptContext),
                resolveToString(tagName, scriptContext),
                scriptContext);


    }

    private Object find(HtmlElement element, String attrName, String substring, String tagName, ScriptContext scriptContext) {
        String attrValue = element.getAttribute(attrName);
        System.out.println(element + " : "+attrName + " = "+ attrValue+"\n"+substring);
        if (attrValue != null && attrValue.toUpperCase().indexOf(substring.toUpperCase())!=-1 && (tagName==null || tagName.equalsIgnoreCase(element.getTagName()) )) {
            return element;
        }
        else {
            Iterator childIterator = element.getChildElements().iterator();
            while (childIterator.hasNext()) {
                final Object o = childIterator.next();
                if(o instanceof HtmlElement){
                    final Object res = find((HtmlElement) o, attrName, substring, tagName, scriptContext);
                    if (res != null) {
                        return res;
                    }
                }
            }
        }
        return null;
    }

    public Object getSubstring() {
        return substring;
    }

    public void setSubstring(Object substring) {
        this.substring = substring;
    }

    public Object getAttrName() {
        return attrName;
    }

    public void setAttrName(Object attrName) {
        this.attrName = attrName;
    }

    public Object getTagName() {
        return tagName;
    }

    public void setTagName(Object tagName) {
        this.tagName = tagName;
    }
}