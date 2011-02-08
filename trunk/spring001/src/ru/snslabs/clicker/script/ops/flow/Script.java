package ru.snslabs.clicker.script.ops.flow;

import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ScriptOperation;
import ru.snslabs.clicker.script.ScriptWebContext;
import ru.snslabs.clicker.script.ops.ScriptFailure;

import java.util.HashMap;
import java.util.Map;

/**
 * Операция выполняет все операции которые в неё включены
 */
public class Script implements ScriptOperation {
    private java.util.List<ScriptOperation> operations;
    private Map<String,String> parameters;

    public Script() {
    }

    public Object execute(ScriptContext scriptContext) throws ScriptFailure {
        scriptContext.setAttribute(ScriptWebContext.SCRIPT, this);
        for (ScriptOperation op : operations) {
            op.execute(scriptContext);
        }
        return null;
    }

    public java.util.List<ScriptOperation> getOperations() {
        return operations;
    }

    public void setOperations(java.util.List<ScriptOperation> operations) {
        this.operations = operations;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }
}
