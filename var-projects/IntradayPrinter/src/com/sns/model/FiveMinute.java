package com.sns.model;

import java.math.BigDecimal;

public class FiveMinute extends AbstractCandel {
    
    public static final BigDecimal FIVE = new BigDecimal(5);

    BigDecimal getInterval() {
        return FIVE;
    }
}
