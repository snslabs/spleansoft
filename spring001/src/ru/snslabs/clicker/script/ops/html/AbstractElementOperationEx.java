package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import org.springframework.util.StringUtils;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ScriptWebContext;
import ru.snslabs.clicker.script.ValueResolver;
import ru.snslabs.clicker.script.ops.HtmlScriptOperation;
import ru.snslabs.clicker.script.ops.ScriptFailure;
import ru.snslabs.clicker.script.ops.flow.Script;

import java.util.Map;

abstract public class AbstractElementOperationEx extends HtmlScriptOperation {

    private String idAttr;
    private String nameAttr;
    private String attribute;
    private String substr;
    private String xpath;
    private ValueResolver customResolver;

    abstract protected Object execute(HtmlElement htmlEl, ScriptContext scriptContext);

    public Object execute(ScriptContext scriptContext) throws ScriptFailure {
        HtmlElement el = null;
        ValueResolver elementFinder = null;
        if(customResolver != null){
            elementFinder = customResolver;
        }
        else if (StringUtils.hasText(xpath)){
            final GetElementByXPath xpathResolver = new GetElementByXPath();
            xpathResolver.setXpath(substParams(xpath,scriptContext));
            elementFinder = xpathResolver;
        }
        else if(StringUtils.hasText(idAttr)){
            GetElementById byId = new GetElementById();
            byId.setId(substParams(idAttr,scriptContext));
            elementFinder = byId;
        }
        else if(StringUtils.hasText(nameAttr)){
            GetElementByName byName = new GetElementByName();
            byName.setName(substParams(nameAttr, scriptContext));
            elementFinder = byName;
        }
        else if(StringUtils.hasText(attribute)){
            GetElementBySubstringInProperty bySusbtr = new GetElementBySubstringInProperty();
            bySusbtr.setAttrName(substParams(attribute,scriptContext));
            bySusbtr.setSubstring(substParams(substr,scriptContext));
            elementFinder = bySusbtr;
        }
        return execute(resolveToHtmlElement(elementFinder,scriptContext), scriptContext);
    }

    public String getIdAttr() {
        return idAttr;
    }

    public void setIdAttr(String idAttr) {
        this.idAttr = idAttr;
    }

    public String getNameAttr() {
        return nameAttr;
    }

    public void setNameAttr(String nameAttr) {
        this.nameAttr = nameAttr;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getSubstr() {
        return substr;
    }

    public void setSubstr(String substr) {
        this.substr = substr;
    }

    public ValueResolver getCustomResolver() {
        return customResolver;
    }

    public void setCustomResolver(ValueResolver customResolver) {
        this.customResolver = customResolver;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

}