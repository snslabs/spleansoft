package ru.snslabs.clicker.script.ops.html;

import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ScriptOperation;
import ru.snslabs.clicker.script.ops.ScriptFailure;
import ru.snslabs.clicker.script.ops.flow.Script;

import java.util.HashMap;
import java.util.List;

public class ForEachInList implements ScriptOperation {
    private String paramName;
    private List<String> stringList;
    private Script script;

    public Object execute(ScriptContext scriptContext) throws ScriptFailure {
        if(paramName ==null){
            throw new IllegalArgumentException("paramName should be specified");
        }
        if(stringList == null){
            throw new IllegalArgumentException("stringList should be not null");
        }
        for (String s : stringList) {
            final HashMap<String, String> map = new HashMap<String, String>();
            map.put(paramName, s);
            System.out.println("Parameters : " + map);
            script.setParameters(map);
            script.execute(scriptContext);
        }
        return null;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public Script getScript() {
        return script;
    }

    public void setScript(Script script) {
        this.script = script;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
}
