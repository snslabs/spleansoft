package ru.snslabs.clicker.script;

import ru.snslabs.clicker.script.ScriptContext;

public interface ValueResolver {
    Object resolve(ScriptContext scriptContext);
}
