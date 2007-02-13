package com.sns.booking.ejb;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ejb.SessionBean;
import javax.ejb.CreateException;
import javax.ejb.SessionContext;
import javax.ejb.EJBException;
import java.util.List;
import java.util.ArrayList;

import com.sns.booking.model.Seat;

public class SessionBookingBMTBean implements SessionBean {
    public static final Log log = LogFactory.getLog(SessionBookingBMTBean.class);

    public SessionBookingBMTBean() {
        log.info(SessionBookingBMTBean.class+" instance created ");
    }

    public void ejbCreate() throws CreateException {
    }

    public void setSessionContext(SessionContext sessionContext) throws EJBException {
    }

    public void ejbRemove() throws EJBException {
    }

    public void ejbActivate() throws EJBException {
    }

    public void ejbPassivate() throws EJBException {
    }

    public boolean bookSeat(Integer id) {
        log.info("Booking seat "+id);
        return true;
    }

    public List getAvailableSeats() {
        log.info("Listing avaiable seats...");

        List seats = new ArrayList();

        seats.add(new Seat(1,1,1, "A"));
        seats.add(new Seat(2,1,2, "A"));
        seats.add(new Seat(3,1,3, "A"));
        seats.add(new Seat(4,1,4, "A"));
        seats.add(new Seat(5,1,1, "B"));
        seats.add(new Seat(6,1,2, "B"));
        seats.add(new Seat(7,1,3, "B"));
        seats.add(new Seat(8,1,4, "B"));
        seats.add(new Seat(9,1,1, "C"));
        seats.add(new Seat(10,1,2, "C"));
        seats.add(new Seat(11,1,3, "C"));
        seats.add(new Seat(12,1,4, "C"));
        seats.add(new Seat(13,1,1, "D"));
        seats.add(new Seat(14,1,2, "D"));
        seats.add(new Seat(15,1,3, "D"));
        seats.add(new Seat(16,1,4, "D"));

        return seats;
    }
}
