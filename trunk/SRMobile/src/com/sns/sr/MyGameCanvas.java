package com.sns.sr;

import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class MyGameCanvas extends GameCanvas implements Runnable {
    private long milliseconds = 250;
    protected volatile boolean keepRunning = true;
    private Image coupleImage;
    public MyGameCanvas() {
        super(false);
    }

    protected MyGameCanvas(boolean b) {
        super(b);
    }

    public void start(){
        try{
            coupleImage = Image.createImage("/couple.gif");
        }
        catch(Exception e){
            e.printStackTrace();
        }

        Thread runner = new Thread(this);
        runner.start();
    }

    public void run() {

        while(keepRunning){
            try {
                verifyGameState();
                checkUserInput();
                updateGameScreen(getGraphics());
                Thread.sleep(milliseconds);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void updateGameScreen(Graphics graphics) {

    }

    private void checkUserInput() {

    }

    private void verifyGameState() {

    }
}
