package com.splean.comm.common;

/**
 * Subscriber type - enumeration. 
 */
public final class SubscriberType implements Comparable {

    public static final SubscriberType ICQ = new SubscriberType(1,"icq");
    public static final SubscriberType MAIL_RU = new SubscriberType(2,"mail.ru");
    public static final SubscriberType YAHOO = new SubscriberType(3,"yahoo");


    private int typeCode;
    private String typeName;

    private SubscriberType(int typeCode, String typeName){
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public int hashCode() {
        return typeCode;
    }

    public boolean equals(Object obj) {
        if(obj instanceof SubscriberType){
            return obj.hashCode() == this.hashCode();
        }
        return false;
    }

    public int compareTo(Object o) {
        if(o instanceof SubscriberType){
            return (o.hashCode() - this.hashCode());
        }
        return 0;
    }
}
