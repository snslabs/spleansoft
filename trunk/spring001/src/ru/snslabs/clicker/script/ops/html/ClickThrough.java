package ru.snslabs.clicker.script.ops.html;

import ru.snslabs.clicker.script.ScriptContext;
import ru.snslabs.clicker.script.ops.HtmlScriptOperation;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ClickThrough extends HtmlScriptOperation {
    protected String regexp;
    protected String pageXML;
    public Object execute(ScriptContext scriptContext) {
        try {
            Page currentPage = getCurrentPage(scriptContext);
            if(currentPage instanceof HtmlPage){
                pageXML = ((HtmlPage) currentPage).asXml();
                Pattern pattern = Pattern.compile(getRegexp());
                Matcher matcher = pattern.matcher(pageXML);
                while(matcher.find()){
                    String link = matcher.group();
                    System.out.println(link);
                    OpenPage openPage = new OpenPage();
                    openPage.setUrl(link);
                    openPage.execute(scriptContext);

//                    GetElementBySubstringInProperty priceElFinder = new GetElementBySubstringInProperty();
//                    priceElFinder.setAttrName("class");
//                    priceElFinder.setSubstring("price");
//                    HtmlElement priceElement = (HtmlElement) priceElFinder.execute(scriptContext);
//                    System.out.println("price: " + priceElement.toString());
                    Print print = new Print();
                    print.setFilename("c:\\irr\\"+matcher.group(1)+".html");
                    print.execute(scriptContext);
                }
            }
            return currentPage;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getRegexp() {
        return regexp;
    }

    public void setRegexp(String url) {
        this.regexp = url;
    }
}
