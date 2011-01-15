package com.splean.comm.fake;

import com.splean.comm.common.Subscriber;
import com.splean.comm.common.SubscriberType;

import java.util.Map;
import java.util.Collections;

/**
 * Фейковый пользователь ICQ
 */
public class FakeSubscriber implements Subscriber {
    private String name;
    private String address;
    private String status;

    public FakeSubscriber(String name, String address, String status) {
        this.name = name;
        this.address = address;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public Map getInfo() {
        return Collections.EMPTY_MAP;
    }

    public SubscriberType getType() {
        return SubscriberType.ICQ;
    }

    public int compareTo(Object o) {
        return 0;
    }

    public boolean equals(Object obj) {
        if(obj instanceof FakeSubscriber){
            return this.hashCode() == obj.hashCode();
        }
        return false;
    }

    public int hashCode() {
        return address.hashCode()*27 + getType().hashCode();
    }

}
