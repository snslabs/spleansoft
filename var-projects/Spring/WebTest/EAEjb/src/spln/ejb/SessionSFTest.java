package spln.ejb;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

public interface SessionSFTest extends EJBObject {
    public Long getBeanInstanceId() throws RemoteException;
    public String executeQuickMethod() throws RemoteException;
    public String executeLongMethod(Long time) throws RemoteException;
}
