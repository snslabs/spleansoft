package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ScriptOperation;
import ru.snslabs.clicker.script.ScriptWebContext;
import ru.snslabs.clicker.script.ops.HtmlScriptOperation;
import ru.snslabs.clicker.script.ops.ScriptFailure;

/**
 * Абстрактный класс для действия с найденым элементом
 */
public abstract class AbstractElementOperation extends HtmlScriptOperation {

    private ScriptOperation findElementOp;

    public Object execute(ScriptContext scriptContext) throws ScriptFailure {
        HtmlElement htmlEl = (HtmlElement) findElementOp.execute(scriptContext);
        if (htmlEl != null ) {
            execute(htmlEl, scriptContext);
            return true;
        }
        else {
            return false;
        }
    }

    abstract Object execute(HtmlElement htmlEl, ScriptContext scriptContext);

    public ScriptOperation getFindElementOp() {
        return findElementOp;
    }

    public void setFindElementOp(ScriptOperation findElementOp) {
        this.findElementOp = findElementOp;
    }
}
