package com.sns;

import com.sns.model.AbstractCandel;

import java.util.List;
import java.awt.*;
import java.math.BigDecimal;

class Painter {
    private List<AbstractCandel> data;
    private BigDecimal left;
    private BigDecimal top;
    private BigDecimal width;
    private BigDecimal height;

    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private int candelsCount;
    private static final int CANDEL_WIDTH_PX = 4;
    private static final int CANDEL_SPACING_PX = 5;

    public Painter(List<AbstractCandel> data, int left, int top) {
//        this.width = new BigDecimal(width);
        this.top = new BigDecimal(top);
        this.left = new BigDecimal(left);
//        this.height = new BigDecimal(height);
        this.data = data;
        minPrice = data.get(0).getLow();
        maxPrice = data.get(0).getHigh();
        for (AbstractCandel candel : data) {
            if (maxPrice.compareTo(candel.getHigh()) < 0) {
                maxPrice = candel.getHigh();
            }
            if (minPrice.compareTo(candel.getLow()) > 0) {
                minPrice = candel.getLow();
            }
        }
        candelsCount = data.size();

        // настраиваем размер области графика
        this.width = new BigDecimal(candelsCount * CANDEL_SPACING_PX);
        this.height = this.width.multiply(new BigDecimal(0.75));


        System.out.println("Low = " + minPrice);
        System.out.println("High = " + maxPrice);
        System.out.println("Candel = " + candelsCount);

    }

    public void paint(Graphics2D g) {
        
        drawBorder(g);
        int i = 0;
        for (AbstractCandel candel : data) {
            drawCandel(candel, i++, g);
        }
        // подписи осей
        drawAxisLabelX(g, left.intValue(), "10:30");
    }

    private void drawBorder(Graphics g) {
        Color old = g.getColor();
        g.setColor(new Color(0xB0,0xB0,0xB0));
        g.drawRect(left.intValue(), top.intValue(), width.intValue(), height.intValue());
        g.setColor(old);
    }

    private void drawLabel(Graphics g, String text, int x, int y){
    }
    private void drawAxisLabelX(Graphics g, int x, String text){
        Color color = g.getColor();
        g.setColor(Color.BLACK);
        g.drawString(text, x-15, top.add(height).intValue()+12);
        g.setColor(color);
    }

    private void drawCandel(AbstractCandel candel, int index, Graphics g) {
        // drawing shadows
        g.drawLine(left.intValue() + index * (CANDEL_SPACING_PX) + (CANDEL_WIDTH_PX/2),
                pointToScreen(candel.getHigh()),
                left.intValue() + index * (CANDEL_SPACING_PX) + (CANDEL_WIDTH_PX/2),
                pointToScreen(candel.getLow()) );
        // drawing candels
        if (candel.getOpen().compareTo(candel.getClose()) <= 0) {
            Color color = g.getColor();
            g.setColor(Color.WHITE);
            g.fillRect(index * (CANDEL_SPACING_PX )+ left.intValue(), pointToScreen(candel.getClose()), CANDEL_WIDTH_PX,
                    scalePrice(candel.getClose().subtract(candel.getOpen())));
            g.setColor(color);
            g.drawRect(index * (CANDEL_SPACING_PX )+ left.intValue(), pointToScreen(candel.getClose()), CANDEL_WIDTH_PX,
                    scalePrice(candel.getClose().subtract(candel.getOpen())));
        }
        else{
            g.drawRect(index * (CANDEL_SPACING_PX) + left.intValue(), pointToScreen(candel.getOpen()), CANDEL_WIDTH_PX,
                    scalePrice(candel.getOpen().subtract(candel.getClose())));
            g.fillRect(index * (CANDEL_SPACING_PX) + left.intValue(), pointToScreen(candel.getOpen()), CANDEL_WIDTH_PX,
                    scalePrice(candel.getOpen().subtract(candel.getClose())));
        }
    }

    private int scalePrice(BigDecimal priceDiff) {
        int i = (int) (priceDiff.multiply(height).floatValue() / (maxPrice.subtract(minPrice)).floatValue());
//        System.out.println(priceDiff + " => " + i);
        return i;
    }

    private int pointToScreen(BigDecimal price) {
        int y = (int)(price.subtract(minPrice).multiply(height).floatValue() / maxPrice.subtract(minPrice).floatValue());
        y = top.intValue() + height.intValue() - y;
//        System.out.println(price + " => " + y );
        return y;
    }

}
