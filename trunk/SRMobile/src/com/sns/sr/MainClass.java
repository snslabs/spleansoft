package com.sns.sr;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class MainClass extends MIDlet {
    private MyGameCanvas canvas = new MyGameCanvas();

    protected void startApp() throws MIDletStateChangeException {
        Display display = Display.getDisplay(this);
        canvas.start();
        display.setCurrent(canvas);
    }

    protected void pauseApp() {

    }

    protected void destroyApp(boolean b) throws MIDletStateChangeException {

    }
}
