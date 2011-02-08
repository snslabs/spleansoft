package spln.ejb;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

public interface SessionSLTest extends EJBObject {
    public Long getBeanInstanceId() throws RemoteException;
    public String executeQuickMethod() throws RemoteException;
    public String executeLongMethod(Long time) throws RemoteException;

    public String transactionalMethod(Boolean toFail) throws RemoteException;
    public String externalTransactionalMethod(Boolean toFail, Boolean toFailBefore,
                                              Boolean callInnerLocally,
                                              Boolean failInner) throws RemoteException;
}
