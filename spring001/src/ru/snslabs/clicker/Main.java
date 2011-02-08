package ru.snslabs.clicker;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import ru.snslabs.clicker.script.ScriptOperation;
import ru.snslabs.clicker.script.ScriptWebContext;

import java.util.HashMap;

public class Main {

    private BrowserVersion browserVersion = BrowserVersion.FIREFOX_2;

    private ScriptOperation scriptOperation;

    public Main(){
        System.out.println("Main created!!!");
    }

    public static void main(String[] args) {
        ClassPathResource resource = new ClassPathResource("main.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        Main main = (Main) factory.getBean("main");
        main.runScript();
    }


    private void runScript(){

        ScriptWebContext webContext = new ScriptWebContext();

        WebClient wc = new WebClient(browserVersion);
        wc.setJavaScriptEnabled(false);
        wc.setCookieManager(new CookieManager());
        wc.setRedirectEnabled(true);
        wc.setActiveXObjectMap(new HashMap<String, String>());
        wc.setAjaxController(new AjaxController(){
            public boolean processSynchron(HtmlPage htmlPage, WebRequestSettings webRequestSettings, boolean b) {
                return super.processSynchron(htmlPage, webRequestSettings, b);
            }
        });
        wc.setJavaScriptEngine( new JavaScriptEngine(wc) );
        wc.setThrowExceptionOnScriptError(true);
        wc.setThrowExceptionOnFailingStatusCode(false);
        /*
        HttpClientParams params = wc.setPa;
        params.setBooleanParameter( HttpClientParams.REJECT_RELATIVE_REDIRECT, false );
        params.setBooleanParameter( HttpClientParams.ALLOW_CIRCULAR_REDIRECTS, false );
        params.setIntParameter( HttpClientParams.MAX_REDIRECTS, 10 );
        */

        webContext.setAttribute(ScriptWebContext.WEB_CLIENT, wc);

        Object result = scriptOperation.execute(webContext);
        System.out.println("Result is " + result);
    }

    public ScriptOperation getScriptOperation() {
        return scriptOperation;
    }

    public void setScriptOperation(ScriptOperation scriptOperation) {
        this.scriptOperation = scriptOperation;
    }
}
