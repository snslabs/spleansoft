package com.sns;

import com.sns.booking.ejb.SessionBookingBMTHome;
import com.sns.booking.ejb.SessionBookingBMT;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import java.util.List;
import java.util.Iterator;
import java.rmi.RemoteException;

public class EJBTest {
    public static void main(String[] args) {

        try {
            Context ctx = new InitialContext();
            Object ref = ctx.lookup("booking/SessionBookingBMT");
            SessionBookingBMTHome home = (SessionBookingBMTHome) PortableRemoteObject.narrow(ref, SessionBookingBMTHome.class);
            SessionBookingBMT bookingBMT = home.create();

            List list = bookingBMT.getAvailableSeats();
            for (Iterator it = list.iterator(); it.hasNext();) {
                Object o =  it.next();
                System.out.println(":"+o);
            }
            bookingBMT.remove();
        }
        catch (NamingException e) {
            e.printStackTrace();
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
        catch (CreateException e) {
            e.printStackTrace();
        }
        catch (RemoveException e) {
            e.printStackTrace();
        }


    }
}
