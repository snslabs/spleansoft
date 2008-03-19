package ru.snslabs.clicker.script;

import ru.snslabs.clicker.script.ops.ScriptFailure;

public interface ScriptOperation {
    Object execute(ScriptContext scriptContext) throws ScriptFailure;
}
