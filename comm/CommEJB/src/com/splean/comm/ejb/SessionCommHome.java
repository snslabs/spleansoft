package com.splean.comm.ejb;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import java.rmi.RemoteException;

public interface SessionCommHome extends EJBHome {
    com.splean.comm.ejb.SessionComm create() throws RemoteException, CreateException;
}
