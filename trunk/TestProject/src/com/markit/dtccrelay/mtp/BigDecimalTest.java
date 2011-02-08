package com.markit.dtccrelay.mtp;

import junit.framework.TestCase;

import java.math.BigDecimal;

public class BigDecimalTest extends TestCase {
    public void testBigDecimal() {

        BigDecimal[] src = new BigDecimal[]{
                new BigDecimal(60000),
                new BigDecimal(600000),
                new BigDecimal(6000000),
                new BigDecimal(60000000),
                new BigDecimal(600000000),
                new BigDecimal("123.232300000"),
                new BigDecimal("434300000000"),
                new BigDecimal("2343.40404000044444"),
                new BigDecimal(2),
                new BigDecimal(0)
        };

        printArray(src);
        BigDecimal[] arr = new BigDecimal[src.length];

        for (int i = 0; i < src.length; i++) {
            BigDecimal decimal = src[i];
            arr[i] = decimal.stripTrailingZeros();
            if(arr[i].scale()<0){
                arr[i] = arr[i].setScale(0);
            }
        }

        System.out.println("----");
        printArray(arr);

    }

    private void printArray(BigDecimal[] arr) {
        for (BigDecimal d : arr) {
            System.out.println(d + " \t==  unscaled="+ d.unscaledValue()+"; \tscale=" + d.scale());
        }
    }
}
