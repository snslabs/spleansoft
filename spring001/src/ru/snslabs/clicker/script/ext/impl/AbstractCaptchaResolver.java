package ru.snslabs.clicker.ext.impl;

import com.gargoylesoftware.htmlunit.WebClient;
import ru.snslabs.clicker.script.ValueResolver;

abstract public class AbstractCaptchaResolver implements ValueResolver {
    protected String srcUrl;
    protected WebClient webClient;

    public String getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(String srcUrl) {
        this.srcUrl = srcUrl;
    }

    public WebClient getWebClient() {
        return webClient;
    }

    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

}
