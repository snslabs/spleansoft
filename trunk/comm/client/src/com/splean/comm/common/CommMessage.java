package com.splean.comm.common;

/**
 * Модель сообщения
 */
public class CommMessage {
    private Subscriber from;
    private Subscriber to;
    private String msgBody;
    private long timestamp;
    public CommMessage(Subscriber from, Subscriber to, String msgBody, long timestamp) {
        this.timestamp = timestamp;
        this.from = from;
        this.to = to;
        this.msgBody = msgBody;
    }

    public Subscriber getFrom() {
        return from;
    }

    public Subscriber getTo() {
        return to;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public long getTimestamp() {
        return timestamp;
    }

}
