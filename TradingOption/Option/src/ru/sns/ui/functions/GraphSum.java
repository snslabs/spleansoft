package ru.sns.ui.functions;

import ru.sns.ui.functions.AbstractGraphFunction;
import ru.sns.ui.model.DoublePoint;

import java.util.ArrayList;

public class GraphSum extends AbstractGraphFunction {
    ArrayList list;

    public GraphSum(ArrayList list) {
        this.list = list;
    }

    public double calulate(double arg) {
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            AbstractGraphFunction gf = (AbstractGraphFunction) list.get(i);
            sum += gf.calulate(arg);
        }
        return sum;
    }

    public String getLabel() {
        return "SUM";
    }

    public DoublePoint getExtremum() {
        return null;
    }
}
