package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.html.FocusableElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.javascript.host.Event;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ValueResolver;
import ru.snslabs.clicker.script.ops.HtmlScriptOperation;
import ru.snslabs.clicker.script.ops.ScriptFailure;

import java.util.List;

public class TypeValueInto extends AbstractElementOperationEx {

    private Object value;

    protected Object execute(HtmlElement htmlEl, ScriptContext scriptContext) {
        if(htmlEl ==null){
            log.warn("No element found");
        }
        else{
            ((FocusableElement)htmlEl).focus();
            typeIn(htmlEl, resolveToString(value,scriptContext));
            ((FocusableElement)htmlEl).blur();
        }
        return null;
    }

    /**
     * Эмулирует печатание символов :-)
     * @param el
     * @param s
     */
    private void typeIn(HtmlElement el, String s) {
        StringBuffer sb = null;
        if(log.isDebugEnabled()){
            sb = new StringBuffer();
        }

        for(char c : s.toCharArray()){
            el.fireEvent(new Event(el, Event.TYPE_KEY_DOWN, (int)c ,false, false, false));
            String val = el.getAttributeValue("value");
            if(val == null){
                val = "";
            }
            el.setAttributeValue("value", val +c );
            el.fireEvent(new Event(el, "keyup", (int)c ,false, false, false));
            if(sb!=null){
                sb.append(c);
            }
        }
        if(log.isDebugEnabled()){
            log.debug("Typing : " + sb + " into "+ el);
        }

    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}