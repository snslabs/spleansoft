package com.sns.sqbot.groovy;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.ElementArray;
import com.gargoylesoftware.htmlunit.javascript.host.Document;

import java.net.URL;

public class t {
    public static void main(String[] args) throws Exception {
        WebClient wc = new WebClient(BrowserVersion.INTERNET_EXPLORER_6_0,"msk-isa4.luxoft.com",8080);
        wc.setTimeout(60000);
        wc.setCredentialsProvider( new MyCredentialProvider());

        for(int j = 0; j < 10; j ++){
            HtmlPage page = (HtmlPage) wc.getPage(new URL("http://www.mail.ru/?bla="+System.currentTimeMillis()));
            Document doc = (Document) page.getScriptObject();
            ElementArray arr = (ElementArray) doc.jsxFunction_getElementsByTagName("object");
            int length = arr.jsGet_length();
            for(int i = 0;  i < length; i++){
                System.out.println( i + " ["+j+"] : " + arr.get(i, arr) );
            }
        }
    }
}
