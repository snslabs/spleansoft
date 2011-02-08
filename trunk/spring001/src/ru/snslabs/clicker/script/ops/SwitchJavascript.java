package ru.snslabs.clicker.script.ops;

import ru.snslabs.clicker.script.ScriptContext;

public class SwitchJavascript extends HtmlScriptOperation {
    private boolean enabled;

    public Object execute(ScriptContext scriptContext) throws ScriptFailure {
        this.getWebClient(scriptContext).setJavaScriptEnabled(enabled);
        return null;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
