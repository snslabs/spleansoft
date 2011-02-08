package ru.snslabs.clicker.script.ext;

import ru.snslabs.clicker.script.ops.HtmlScriptOperation;
import ru.snslabs.clicker.script.ops.ScriptFailure;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ValueResolver;
import com.gargoylesoftware.htmlunit.WebClient;

abstract public class CaptchaResolver extends HtmlScriptOperation implements ValueResolver {
    protected Object srcUrl;
    protected WebClient webClient;

    public Object resolve(ScriptContext scriptContext) {
        return execute(scriptContext);
    }

    public Object execute(ScriptContext scriptContext) throws ScriptFailure {
        webClient = getWebClient(scriptContext);
        return resolveCaptcha(scriptContext);
    }

    abstract public Object resolveCaptcha(ScriptContext scriptContext);

    public Object getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(Object srcUrl) {
        this.srcUrl = srcUrl;
    }
}
