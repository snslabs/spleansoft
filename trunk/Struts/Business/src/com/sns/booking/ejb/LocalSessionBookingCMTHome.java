package com.sns.booking.ejb;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface LocalSessionBookingCMTHome extends EJBLocalHome {
    com.sns.booking.ejb.LocalSessionBookingCMT create() throws CreateException;
}
