package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.html.FocusableElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import ru.snslabs.clicker.script.ScriptContext;

/**
 * Устанавливает фокус на найденый элемент
 */
public class Focus extends AbstractElementOperation {

    Object execute(HtmlElement htmlEl, ScriptContext scriptContext) {
        if (htmlEl instanceof FocusableElement) {
            ((FocusableElement) htmlEl).focus();
            return true;
        }
        else {
            return false;
        }
    }
}
