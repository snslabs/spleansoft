package spln.ejb;

import javax.ejb.EJBLocalObject;

public interface LocalEntityEmp extends EJBLocalObject {
    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);
}
