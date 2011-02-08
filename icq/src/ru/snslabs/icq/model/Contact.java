package ru.snslabs.icq.model;

import org.jcq2k.MessagingNetwork;

import java.io.Serializable;

/**
 * Модель записи в контактном листе
 */
public class Contact implements Serializable {
    private String uin;
    private String nick;
    private String dsc;
    private int status = MessagingNetwork.STATUS_OFFLINE;

    public Contact() {
    }

    public Contact(String uin, String nick, String dsc) {
        this.uin = uin;
        this.nick = nick;
        this.dsc = dsc;
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
