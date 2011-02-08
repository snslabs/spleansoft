package com.sns.booking.ejb;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;
import java.util.List;

public interface SessionBookingBMT extends EJBObject {
    boolean bookSeat(Integer id) throws RemoteException;
    List getAvailableSeats() throws RemoteException;
}
