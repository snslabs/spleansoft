package ru.sns.ui.functions;

import ru.sns.ui.PositionBean;
import ru.sns.ui.model.DoublePoint;

public abstract class AbstractGraphFunction {
    abstract public double calulate(double arg);
    abstract public String getLabel();
    abstract public DoublePoint getExtremum();
}
