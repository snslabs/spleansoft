package com.splean.comm.ejb;

import com.splean.comm.common.Response;
import com.splean.comm.common.HTTPStreamProxy;

import javax.ejb.SessionBean;
import javax.ejb.CreateException;
import javax.ejb.SessionContext;
import javax.ejb.EJBException;
import java.net.URL;
import java.util.Hashtable;
import java.io.IOException;

public class SessionCommBean implements SessionBean {

    private HTTPStreamProxy httpStreamProxy;
    private Hashtable pageProperties;

    public SessionCommBean() {
        pageProperties = new Hashtable();
    }

    public void ejbCreate() throws CreateException {
        httpStreamProxy = new HTTPStreamProxy(null, pageProperties);
    }

    public void setSessionContext(SessionContext sessionContext) throws EJBException {
    }

    public void ejbRemove() throws EJBException {
    }

    public void ejbActivate() throws EJBException {
    }

    public void ejbPassivate() throws EJBException {
    }

    public Response getStream(URL url)  {
        try {
            return new Response(httpStreamProxy.getStream(url));
        } catch (IOException e) {
            e.printStackTrace();
            throw new EJBException(e);
        }
    }
}
