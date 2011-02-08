package ru.snslabs.clicker.script.ops;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ScriptOperation;
import ru.snslabs.clicker.script.ScriptWebContext;
import ru.snslabs.clicker.script.ValueResolver;
import ru.snslabs.clicker.script.ops.flow.Script;

import java.util.Map;

abstract public class HtmlScriptOperation implements ScriptOperation {

    protected static final Log log = LogFactory.getLog("SCRIPT");

    protected WebClient getWebClient(ScriptContext swc){
        return (WebClient) swc.getAttribute(ScriptWebContext.WEB_CLIENT);
    }

    protected HtmlPage getCurrentPage(ScriptContext swc){
        return (HtmlPage) swc.getAttribute(ScriptWebContext.CURRENT_PAGE);
    }

    protected void setCurrentPage(Page page, ScriptContext scriptContext) {
        scriptContext.setAttribute(ScriptWebContext.CURRENT_PAGE, page);
    }

    protected String resolveToString(Object property, ScriptContext scriptContext){
        if(property == null){
            return null;
        }
        if(property instanceof ValueResolver){
            return ((ValueResolver)property).resolve(scriptContext).toString();
        }
        else{
            return property.toString();
        }
    }

    protected HtmlElement resolveToHtmlElement(Object htmlEl, ScriptContext scriptContext) {
        if(htmlEl instanceof ValueResolver){
            return (HtmlElement) ((ValueResolver)htmlEl).resolve(scriptContext);
        }
        return null;
    }
    
    protected String substParams(String originalValue, ScriptContext scriptContext){
        final Script script = (Script) scriptContext.getAttribute(ScriptWebContext.SCRIPT);
        Map<String, String> params = null;
        if(script != null){
            params = script.getParameters();
        }

        if(params != null && !params.isEmpty()){
            for(Map.Entry<String, String> param : params.entrySet()){
                originalValue = originalValue.replaceAll("\\$\\{"+param.getKey()+"\\}",param.getValue());
            }
        }
        return originalValue;
    }

}
