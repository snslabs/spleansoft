package ru.sns.ui;

import ru.sns.ui.functions.AbstractGraphFunction;
import ru.sns.ui.functions.GraphSum;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

public class GraphPane extends JPanel {
    public ArrayList<AbstractGraphFunction> functionList = new ArrayList<AbstractGraphFunction>();
    private AbstractGraphFunction summ = new GraphSum(functionList);
    double centerPositionX = 20.5;
    double centerPositionY = 0;
    double tickStepX = 0.5;
    double tickStepY = 0.5;


    protected void paintComponent(Graphics g) {
        g.clearRect(0,0,this.getWidth(), this.getHeight());
        g.setColor(Color.GRAY);
        drawGrid(g);
        g.setColor(Color.BLACK);
        for (AbstractGraphFunction aFunctionList : functionList) {
            AbstractGraphFunction o = (AbstractGraphFunction) aFunctionList;
            drawGraph(g, o);
        }
        g.setColor(Color.BLUE);
        drawGraph(g, summ);

    }

    private void drawGraph(Graphics g, AbstractGraphFunction gf) {
        final double MAX_X = this.getWidth();
        final double MAX_Y = this.getHeight();
        final double CENTER_X = MAX_X/2;
        final double CENTER_Y = MAX_Y/2;
        double startX = centerPositionX - CENTER_X / getScaleX();
        double endX = centerPositionX + CENTER_X / getScaleX();
        double stepX = (1 / getScaleX());
        int y = (int) (CENTER_Y + (int) ((centerPositionY - gf.calulate(startX))*getScaleY()));
        int x = (int) (CENTER_X - (int)((centerPositionX - startX)*getScaleX()));
        g.drawString(gf.getLabel(), x+2,y+12);
        for(double arg = startX; arg < endX; arg += stepX){
            int y2 = (int) (CENTER_Y + (int) ((centerPositionY - gf.calulate(arg))*getScaleY()));
            int x2 = (int) (CENTER_X - (int)((centerPositionX-arg)*getScaleX()));
            g.drawLine(x,y,x2,y2);
            x = x2; y = y2;
        }

    }

    private void drawGrid(Graphics g) {
        final int MAX_X = this.getWidth();
        final int MAX_Y = this.getHeight();
        final int CENTER_X = MAX_X/2;
        final int CENTER_Y = MAX_Y/2;
        g.drawLine(0, CENTER_Y, MAX_X, CENTER_Y);
        g.drawLine(CENTER_X, 0, CENTER_X, MAX_Y);
        int maxX = (int) (CENTER_X/(tickStepX*getScaleX())+1);
        int maxY = (int) (CENTER_Y/(tickStepY*getScaleY())+1);
        System.out.println(maxX+ " : "+ maxY);
        for(int i = 1; i< maxX; i++ ){
            int x1 = CENTER_X + (int) (i * tickStepX * getScaleX());
            int x2 = CENTER_X - (int) (i * tickStepX * getScaleX());
            g.drawLine(x1, CENTER_Y-1, x1, CENTER_Y+1);
            g.drawString((centerPositionX+tickStepX*i)+"", x1, CENTER_Y+12);
            g.drawLine(x2, CENTER_Y-1, x2, CENTER_Y+1);
            g.drawString((centerPositionX-tickStepX*i)+"", x2, CENTER_Y+12);
        }
        for(int i = 1; i< maxY; i++ ){
            int y1 = CENTER_Y + (int) (i * tickStepY * getScaleY());
            int y2 = CENTER_Y - (int) (i * tickStepY * getScaleY());
            g.drawLine(CENTER_X-1, y1, CENTER_X+1, y1);
            g.drawString((centerPositionY-tickStepY*i)+"", CENTER_X+4, y1);
            g.drawLine(CENTER_X-1, y2, CENTER_X+1, y2);
            g.drawString((centerPositionY+tickStepY*i)+"", CENTER_X+4, y2);
        }
        g.drawString(centerPositionX+"", CENTER_X+2, CENTER_Y+12);
    }

    double getScaleX(){
        return scaleX;
    }

    double getScaleY(){
        return scaleY;
    }
    private double scaleX = 100;
    private double scaleY = 100;

    public void zoomIn(){
        System.out.println("Zooming In");
        scaleY *= 1.5;
        this.updateUI();
    }
    public void zoomOut(){
        System.out.println("Zooming out");
        scaleY /= 1.5;
        this.updateUI();
    }

    public void autoscale() {
        double minx=0.0, miny=0.0, maxx=0.0, maxy=0.0;
        if(functionList.size()>0){
            minx = functionList.get(0).getExtremum().x;
            maxx = functionList.get(0).getExtremum().x;
            miny = 0;
            maxy = 0;
            for (AbstractGraphFunction agf : functionList) {
                if(agf.getExtremum().x < minx){
                    minx = agf.getExtremum().x;
                }
                if(agf.getExtremum().y < miny){
                    miny = agf.getExtremum().y;
                }
                if(agf.getExtremum().x > maxx){
                    maxx = agf.getExtremum().x;
                }
                if(agf.getExtremum().y > maxy){
                    maxy = agf.getExtremum().y;
                }
            }
        }

        this.centerPositionX = (minx+maxx)/2.0;
        this.scaleY = 200.0/((maxy-miny)/8.0);
        this.tickStepX = 500;
        this.tickStepY = 500;
        this.scaleX = 0.1;

        System.out.println("center position " + centerPositionX);
        System.out.println("         step X " + tickStepX);
        System.out.println("        scale X " + scaleX);
        System.out.println("        scale Y " + scaleY);

    }
}

