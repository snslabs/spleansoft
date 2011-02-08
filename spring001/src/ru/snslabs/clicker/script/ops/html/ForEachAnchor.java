package ru.snslabs.clicker.script.ops.html;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.xpath.XPathUtils;
import org.jaxen.JaxenException;
import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ScriptOperation;
import ru.snslabs.clicker.script.ScriptWebContext;
import ru.snslabs.clicker.script.ValueResolver;
import ru.snslabs.clicker.script.ops.HtmlScriptOperation;
import ru.snslabs.clicker.script.ops.ScriptFailure;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForEachAnchor extends HtmlScriptOperation {
    private String xpath;
    private ScriptOperation operation;
    private Pattern excludeRegexp;


    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public void setOperation(ScriptOperation operation) {
        this.operation = operation;
    }

    public String getXpath() {
        return xpath;
    }

    public ScriptOperation getOperation() {
        return operation;
    }

    public Object execute(ScriptContext scriptContext) throws ScriptFailure {
        WebClient wc = (WebClient) scriptContext.getAttribute(ScriptWebContext.WEB_CLIENT);
        HtmlPage htmlPage = (HtmlPage) scriptContext.getAttribute(ScriptWebContext.CURRENT_PAGE);
        Set<String> visitedAnchors = new HashSet<String>();
        try {
            String xPath = substParams(xpath,scriptContext);
            final List nodeList = XPathUtils.getByXPath(htmlPage.getDocumentElement(), xPath);
            for (Object node : nodeList) {
                final HtmlAnchor a = (HtmlAnchor) node;
                if(excludeRegexp != null){
                    Matcher matcher = excludeRegexp.matcher(a.getHrefAttribute());
                    if(matcher.find()){
                        System.out.println(a.getHrefAttribute()+" exculded!");
                        continue;
                    }
                }
                // this will prevent clicking twice on the same links
                if(visitedAnchors.contains(a.getHrefAttribute())){
                    continue;
                }
                else{
                    visitedAnchors.add(a.getHrefAttribute());
                }
                ShiftClickOn clickOn = new ShiftClickOn();
                clickOn.setCustomResolver(new ValueResolver() {
                    public Object resolve(ScriptContext scriptContext) {
                        return a;
                    }
                });
                clickOn.execute(scriptContext);
                operation.execute(scriptContext);

            }
        }
        catch (JaxenException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getExcludeRegexp() {
        return excludeRegexp.pattern();
    }

    public void setExcludeRegexp(String excludeRegexp) {
        this.excludeRegexp = Pattern.compile(excludeRegexp);
    }
}
