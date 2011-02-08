package com.sns.booking.ejb;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import java.rmi.RemoteException;

public interface SessionBookingBMTHome extends EJBHome {
    com.sns.booking.ejb.SessionBookingBMT create() throws RemoteException, CreateException;
}
