package ru.snslabs.icq.model;

import java.util.Date;
import java.io.Serializable;

public class Message implements Serializable, Cloneable, Comparable {
    private Date date;
    private String from;
    private String to;
    private String text;
    private Byte networkId;
    private Boolean delivered;

    public Message() {
    }

    public Message(Date date, String from, String to, String text, Byte networkId) {
        this(date, from, to, text, networkId, Boolean.TRUE);
    }

    public Message(Date date, String from, String to, String text, Byte networkId, Boolean  isDelivered) {
        delivered = isDelivered;
        if(date==null){
            throw new IllegalArgumentException("Date cannot be null!!!");
        }
        this.date = date;
        this.from = from;
        this.to = to;
        this.text = text;
        this.networkId = networkId;
    }

    /**
     * сравнение сообщений по дате принятия
     * @param o объет для сравнения
     * @return результат сравнения по дате
     */
    public int compareTo(Object o) {
        if(o instanceof Message){
            return this.date.compareTo(((Message)o).date);
        }
        return -1;
    }


    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // new Message(date, from, to, text, networkId);
    }


    public Date getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getText() {
        return text;
    }

    public Byte getNetworkId() {
        return networkId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setNetworkId(Byte networkId) {
        this.networkId = networkId;
    }


    public String toString() {
        return networkId+"["+from+"->"+to+"@"+date+"]>"+text;
    }

    public String getFormattedMessage() {
        return toString();
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }
}
