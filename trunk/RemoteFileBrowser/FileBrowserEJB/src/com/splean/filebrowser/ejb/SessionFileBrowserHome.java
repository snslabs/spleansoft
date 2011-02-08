package com.splean.filebrowser.ejb;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import java.rmi.RemoteException;

public interface SessionFileBrowserHome extends EJBHome {
    com.splean.filebrowser.ejb.SessionFileBrowser create() throws RemoteException, CreateException;
}
