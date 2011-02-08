package com.sns.booking.ejb;

import javax.ejb.EJBLocalObject;
import java.util.List;

public interface LocalSessionBookingBMT extends EJBLocalObject {
    boolean bookSeat(Integer id);
    List getAvailableSeats();

}
