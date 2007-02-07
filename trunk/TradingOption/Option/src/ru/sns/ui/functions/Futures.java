package ru.sns.ui.functions;

import ru.sns.ui.model.DoublePoint;

public class Futures extends AbstractGraphFunction {
    private double buyPrice;
    private int quantity;
    private DoublePoint extremum;
    public Futures(double buyPrice, int quantity) {
        this.buyPrice = buyPrice;
        this.quantity = quantity;
        this.extremum = new DoublePoint(buyPrice, 0);
    }

    public double calulate(double arg) {
        return (buyPrice-arg)*(-quantity);
    }

    public String getLabel() {
        return "Futures "+quantity+ " x "+buyPrice;
    }


    public DoublePoint getExtremum() {
        return extremum;
    }
}
