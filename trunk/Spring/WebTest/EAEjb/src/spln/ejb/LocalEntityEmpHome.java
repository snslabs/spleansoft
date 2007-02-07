package spln.ejb;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface LocalEntityEmpHome extends EJBLocalHome {
    LocalEntityEmp findByPrimaryKey(Long key) throws FinderException;
}
