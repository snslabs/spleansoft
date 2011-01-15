package ru.snslabs.clicker.script.ops.flow;

import ru.snslabs.clicker.script.ScriptOperation;
import ru.snslabs.clicker.script.ScriptContext;

public class IfThen implements ScriptOperation {
    ScriptOperation condition;
    ScriptOperation then;

    public Object execute(ScriptContext scriptContext) {
        if( ((Boolean)condition.execute(scriptContext)).booleanValue()  ){
            return then.execute(scriptContext);
        }
        else{
            return null;
        }
    }

    public ScriptOperation getCondition() {
        return condition;
    }

    public void setCondition(ScriptOperation condition) {
        this.condition = condition;
    }

    public ScriptOperation getThen() {
        return then;
    }

    public void setThen(ScriptOperation then) {
        this.then = then;
    }
}
