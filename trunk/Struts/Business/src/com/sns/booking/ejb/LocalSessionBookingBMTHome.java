package com.sns.booking.ejb;

import javax.ejb.EJBLocalHome;
import javax.ejb.CreateException;

public interface LocalSessionBookingBMTHome extends EJBLocalHome {
    com.sns.booking.ejb.LocalSessionBookingBMT create() throws CreateException;
}
