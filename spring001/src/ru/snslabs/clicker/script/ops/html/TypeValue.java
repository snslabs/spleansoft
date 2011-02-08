package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.javascript.host.Event;
import com.gargoylesoftware.htmlunit.javascript.host.KeyboardEvent;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ValueResolver;

public class TypeValue extends AbstractElementOperation {

    private Object value;

    Object execute(HtmlElement htmlEl, ScriptContext scriptContext) {
        htmlEl.focus();
        typeIn(htmlEl, resolveToString(value,scriptContext));
        htmlEl.blur();
        return null;
    }

    /**
     * Эмулирует печатание символов :-)
     * @param el элемент в который будут печататься символы
     * @param s текст для печати
     */
    private void typeIn(HtmlElement el, String s) {
        StringBuffer sb = null;
        if(log.isDebugEnabled()){
            sb = new StringBuffer();
        }

        for(char c : s.toCharArray()){
            el.fireEvent(new KeyboardEvent(el, Event.TYPE_KEY_DOWN, (int)c ,false, false, false));
            String val = el.getAttribute("value");
            if(val == null){
                val = "";
            }
            el.setAttribute("value", val +c );
            el.fireEvent(new KeyboardEvent(el, "keyup", (int)c ,false, false, false));
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
