package spln.ejb;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

public interface EntityEmp extends EJBObject {
    Long getId() throws RemoteException;

    void setId(Long id) throws RemoteException;

    String getName() throws RemoteException;

    void setName(String name) throws RemoteException;
}
