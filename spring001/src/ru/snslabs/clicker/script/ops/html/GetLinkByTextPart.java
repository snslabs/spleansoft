package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ValueResolver;
import ru.snslabs.clicker.script.ops.HtmlScriptOperation;
import ru.snslabs.clicker.script.ops.ScriptFailure;

import java.util.List;

public class GetLinkByTextPart extends HtmlScriptOperation implements ValueResolver {

    private String text;

    public Object execute(ScriptContext scriptContext) throws ScriptFailure {
        return resolve(scriptContext);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object resolve(ScriptContext scriptContext) {
        List<HtmlAnchor> list = getCurrentPage(scriptContext).getAnchors();
        for (HtmlAnchor htmlAnchor : list) {
            Object o = htmlAnchor.getChildIterator().next();
            System.out.println(o.toString());
            if(o.toString().indexOf(text) != -1){
                return htmlAnchor;
            }
        }
        return null;
    }
}