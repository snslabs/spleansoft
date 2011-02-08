package ru.sns.ui.functions;

import ru.sns.ui.functions.AbstractGraphFunction;
import ru.sns.ui.PositionBean;
import ru.sns.ui.model.DoublePoint;


public class Option extends AbstractGraphFunction {
    public static final int CALL = 1;
    public static final int PUT = 2;

    private double strikePrice;
    private double premium;
    private int quantity;
    private int type;

    private DoublePoint extremum;

    public Option(double strikePrice, double premium, int quantity, int type) {
        if(type != CALL && type != PUT){
            throw new IllegalArgumentException("Option can be only type of PUT or CALL");
        }
        this.strikePrice = strikePrice;
        this.premium = premium;
        this.quantity = quantity;
        this.type = type;
        this.extremum = new DoublePoint(strikePrice,((double)quantity)*premium );
    }


    public double getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(double strikePrice) {
        this.strikePrice = strikePrice;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        if(type != CALL && type != PUT){
            throw new IllegalArgumentException("Option can be only type of PUT or CALL");
        }
        this.type = type;
    }

    public double calulate(double arg) {
        if(type == CALL){
            if(arg < strikePrice){
                return premium*-quantity;
            }
            else{
                return (premium - (arg - strikePrice))*-quantity;
            }
        }
        else if(type == PUT){
            if(arg > strikePrice){
                return premium*-quantity;
            }
            else{
                return (premium - (strikePrice-arg))*-quantity;
            }
        }
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getLabel() {
        return (type==CALL?"Call ":"Put ")+"@"+strikePrice+" "+quantity + " x "+premium;
    }

    public DoublePoint getExtremum() {
        return extremum;
    }
}
