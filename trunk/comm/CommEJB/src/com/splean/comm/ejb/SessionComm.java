package com.splean.comm.ejb;

import com.splean.comm.common.Response;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import java.net.URL;

public interface SessionComm extends EJBObject {
    public Response getStream(URL url) throws RemoteException;
}
