package spln.ejb;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import java.rmi.RemoteException;

public interface SessionSLTestHome extends EJBHome {
    spln.ejb.SessionSLTest create() throws RemoteException, CreateException;
}
