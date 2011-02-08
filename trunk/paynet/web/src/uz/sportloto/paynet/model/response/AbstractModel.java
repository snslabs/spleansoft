package uz.sportloto.paynet.model.response;

import java.util.Date;

abstract public class AbstractModel {
    protected char active;
    protected Date createdOn;

    protected AbstractModel() {
        active = 'Y';
        createdOn = new Date();
    }

    public char isActive() {
        return active;
    }

    public void setActive(char active) {
        this.active = active;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
