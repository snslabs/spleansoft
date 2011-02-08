package ru.sns.ui;

import java.math.BigDecimal;

public class PositionBean {
    private double strike;
    private double price;
    private String instrumentType;
    private String optionType;
    private int quantity;


    public PositionBean( String instrumentType, String optionType, double strike, double price, int quantity) {
        this.strike = strike;
        this.price = price;
        this.instrumentType = instrumentType;
        this.optionType = optionType;
        this.quantity = quantity;
    }

    public PositionBean() {
    }

    public double getStrike() {
        return strike;
    }

    public void setStrike(final double strike) {
        this.strike = strike;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public String getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(String instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String toString() {
        return instrumentType + " " + optionType + "@"+strike+" for " + price + " x"+quantity;
    }
}