package ru.snslabs.clicker.script.ops.flow;

import ru.snslabs.clicker.script.ScriptOperation;
import ru.snslabs.clicker.script.ScriptContext;

public class IfThenElse  implements ScriptOperation {
    ScriptOperation condition;
    ScriptOperation thenOp;
    ScriptOperation elseOp;

    public Object execute(ScriptContext scriptContext) {
        if( ((Boolean)condition.execute(scriptContext)).booleanValue()  ){
            return thenOp.execute(scriptContext);
        }
        else{
            return elseOp.execute(scriptContext);
        }
    }

    public ScriptOperation getCondition() {
        return condition;
    }

    public void setCondition(ScriptOperation condition) {
        this.condition = condition;
    }

    public ScriptOperation getThenOp() {
        return thenOp;
    }

    public void setThenOp(ScriptOperation thenOp) {
        this.thenOp = thenOp;
    }

    public ScriptOperation getElseOp() {
        return elseOp;
    }

    public void setElseOp(ScriptOperation elseOp) {
        this.elseOp = elseOp;
    }
}
