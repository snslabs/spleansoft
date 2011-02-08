package com.splean.comm.common;

import javax.swing.text.Document;
import javax.swing.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Класс прокси для потока HTTP
 */
public class HTTPStreamProxy {

    private Hashtable pageProperties;
    private JEditorPane editorPane;
    /**
     * Создаём класс прокси и передаём ему свойства страницы от редактора
     * @param pageProperties
     */
    public HTTPStreamProxy(JEditorPane editorPane, Hashtable pageProperties) {
        this.editorPane = editorPane;
        this.pageProperties = pageProperties;
    }

    /**
     * метод возвращает поток с данным по заданной странице в таком же виде как его предоставляет прямой запрос URL'a
     *
     * @param page адрес запрашиваемой страницы
     * @return поток с данными
     */
    public InputStream getStream(URL page) throws IOException {
        URLConnection conn = page.openConnection();
        if (conn instanceof HttpURLConnection) {
            HttpURLConnection hconn = (HttpURLConnection) conn;
            hconn.setInstanceFollowRedirects(false);
            int response = hconn.getResponseCode();
            boolean redirect = (response >= 300 && response <= 399);

            /*
            * In the case of a redirect, we want to actually change the URL
            * that was input to the new, redirected URL
            */
            if (redirect) {
                String loc = conn.getHeaderField("Location");
                if (loc.startsWith("http", 0)) {
                    page = new URL(loc);
                } else {
                    page = new URL(page, loc);
                }
                return getStream(page);
            }
        }
        if (pageProperties == null) {
            pageProperties = new Hashtable();
        }
        String type = conn.getContentType();
        if (type != null) {
            if(editorPane!=null){
                editorPane.setContentType(type);
            }
            pageProperties.put("content-type", type);
        }
        pageProperties.put(Document.StreamDescriptionProperty, page);
        String enc = conn.getContentEncoding();
        if (enc != null) {
            pageProperties.put("content-encoding", enc);
        }
        return conn.getInputStream();
    }
}
