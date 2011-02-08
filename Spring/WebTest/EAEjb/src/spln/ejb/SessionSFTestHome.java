package spln.ejb;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import java.rmi.RemoteException;

public interface SessionSFTestHome extends EJBHome {
    spln.ejb.SessionSFTest create() throws RemoteException, CreateException;
}
