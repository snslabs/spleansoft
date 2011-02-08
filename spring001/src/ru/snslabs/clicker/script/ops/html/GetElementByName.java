package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ValueResolver;
import ru.snslabs.clicker.script.ops.HtmlScriptOperation;
import ru.snslabs.clicker.script.ops.ScriptFailure;

import java.util.List;

public class GetElementByName extends HtmlScriptOperation implements ValueResolver {

    private String name;

    public Object execute(ScriptContext scriptContext) throws ScriptFailure {
        return resolve(scriptContext);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object resolve(ScriptContext scriptContext) {
        final List listByName = getCurrentPage(scriptContext).getHtmlElementsByName(name);
        if(listByName.size() >0){
            return listByName.get(0);
        }
        else{
            return null;
        }
    }
}