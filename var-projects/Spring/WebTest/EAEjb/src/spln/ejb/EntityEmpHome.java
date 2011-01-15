package spln.ejb;

import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import java.rmi.RemoteException;

public interface EntityEmpHome extends EJBHome {
    spln.ejb.EntityEmp findByPrimaryKey(Long key) throws RemoteException, FinderException;
}
