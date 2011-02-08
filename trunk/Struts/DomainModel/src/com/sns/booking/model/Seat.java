package com.sns.booking.model;

import java.io.Serializable;

public class Seat implements Serializable {
    private Integer id;
    private Integer row;
    private Integer seat;
    private String hall;

    public Seat() {
    }

    public Seat(int id, int row, int seat, String hall) {
        this.id = new Integer(id);
        this.row = new Integer(row);
        this.seat = new Integer(seat);
        this.hall = hall;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }
}
