package com.splean.br;

import com.splean.comm.common.HTTPStreamProxy;
import com.splean.comm.common.Response;
import com.splean.comm.ejb.SessionComm;
import com.splean.comm.ejb.SessionCommHome;

import javax.swing.*;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;
import java.lang.reflect.Field;

public class HTMLBrowser extends JEditorPane {
    /**
     * ������� ������������� ������
     */
    private boolean useProxy = true;
    private boolean useEJBProxy = true;
    private HTTPStreamProxy httpStreamProxy;
    private SessionComm sessionComm;

    public HTMLBrowser(String url) throws IOException {
        super(url);
        httpStreamProxy = new HTTPStreamProxy(this,getPageProperties());
    }

    public void setPage(String url) throws IOException {
        super.setPage(url);
    }

    /**
     * ����������� ����� ��������� ������. ������������ ��� ����������� ����������� ������
     * @param page
     * @return
     * @throws IOException
     */
    protected InputStream getStream(URL page) throws IOException {
        if (useProxy) {
            if(useEJBProxy){
                 return getSessionComm().getStream(page).getInputStream();
//                return null;
            }
            else{
                System.out.println("using proxy to get " + page);
                return new Response(getProxy().getStream(page)).getInputStream();
            }
        }
        else {
            return super.getStream(page);
        }
    }

    private HTTPStreamProxy getProxy() {
        return httpStreamProxy;
    }

    /**
     * ����� ����� reflection ����������� hashtable �� ���������� ��������
     *
     * @return Hastable �� ���������� ��������
     */
    private Hashtable getPageProperties() {
        try {
            Class htmlBrowserClass = this.getClass().getSuperclass();
            Field declaredField = htmlBrowserClass.getDeclaredField("pageProperties");
            declaredField.setAccessible(true);
            return (Hashtable) declaredField.get(this);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot get page properties");
        }
    }

    public SessionComm getSessionComm() {
        if(sessionComm == null){
            try {
                InitialContext ic = new InitialContext();
                Object ref = ic.lookup("ejb/SessionComm");
                ref = PortableRemoteObject.narrow(ref, SessionCommHome.class);
                sessionComm = ((SessionCommHome)ref).create();
                System.out.println("Got EJB " + sessionComm);
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Cannot locate EJB",e);
            }
        }
        return sessionComm;
    }
}
