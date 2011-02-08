package ru.sns.core;

import junit.framework.TestCase;

import java.math.BigDecimal;

public class TruncateTest extends TestCase {
    public void testTruncate() throws Exception{
        checkIt( new BigDecimal("123434.3234"));
        checkIt( new BigDecimal("123434.3234323"));
        checkIt( new BigDecimal("123434.323"));
        checkIt( new BigDecimal("123434.3"));
        checkIt( new BigDecimal("123434"));
        checkIt( new BigDecimal("123434000"));
        checkIt( new BigDecimal("000"));
        checkIt( new BigDecimal("000.00"));
        checkIt( new BigDecimal("0"));
        checkIt( new BigDecimal("-123434.3234"));
        checkIt( new BigDecimal("-123434.3234323"));
        checkIt( new BigDecimal("-123434.323"));
        checkIt( new BigDecimal("-123434.3"));
        checkIt( new BigDecimal("-123434"));
        checkIt( new BigDecimal("-123434000"));

    }

    private void checkIt(BigDecimal decimalValue) {
        int maximumScale = 3;
        decimalValue = decimalValue.movePointRight(maximumScale);
        boolean isInteger = decimalValue.precision() <  decimalValue.scale();
//        System.out.println(decimalValue+" : "+isInteger);

        try{
            decimalValue.movePointRight(maximumScale).longValueExact();
            isInteger = true;
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            isInteger = false;
        }
        System.out.println(decimalValue+" : "+isInteger);

    }
}
