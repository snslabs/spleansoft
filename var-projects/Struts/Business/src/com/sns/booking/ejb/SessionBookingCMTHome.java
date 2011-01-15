package com.sns.booking.ejb;

import javax.ejb.EJBHome;
import javax.ejb.CreateException;
import java.rmi.RemoteException;

public interface SessionBookingCMTHome extends EJBHome {
    com.sns.booking.ejb.SessionBookingCMT create() throws RemoteException, CreateException;
}
