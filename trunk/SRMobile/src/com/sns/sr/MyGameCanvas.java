package com.sns.sr;

import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.util.Random;

public class MyGameCanvas extends GameCanvas implements Runnable {
    public final int GAME_WIDTH = 160;
    public final int GAME_HEIGHT = 160;
    public final int GAME_ORIGIN_X = (getWidth() - GAME_WIDTH) / 2;
    public final int GAME_ORIGIN_Y = (getHeight() - GAME_HEIGHT) / 2;
    private long milliseconds = 30;
    protected volatile boolean keepRunning = true;
    private int CENTER_X = getWidth() / 2;
    private int CENTER_Y = getHeight() / 2;
    // a flag to indicate which direction the couple are moving
    private boolean up = true;
    // indicates the random jump height, calculated for every jump

    // random number generator
    public Random random = new Random();

    public MyGameCanvas() {
        super(false);
    }

    protected MyGameCanvas(boolean b) {
        super(b);
    }

    public void start() {
        try {
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        Thread runner = new Thread(this);
        runner.start();
    }

    public void run() {

        while (keepRunning) {
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

    private void updateGameScreen(Graphics g) {
        // clearing screen
        g.setColor(0xffffff);
        g.fillRect(0, 0, getWidth(), getHeight());
        // drawing scene
        buildGameScreen(g);
        // flushing buffer to screen
        flushGraphics();
    }

    private void checkUserInput() {
        int keyState = getKeyStates();
//        calculateCoupleX(keyState);
    }

    private void buildGameScreen(Graphics g) {

    }

    private void verifyGameState() {

    }
}
