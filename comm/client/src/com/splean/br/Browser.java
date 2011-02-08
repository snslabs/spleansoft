package com.splean.br;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

public class Browser extends Applet {
    public void init() {
        super.init();

        try {
            HTMLBrowser editor = new HTMLBrowser(  "http://www.yandex.ru/"
            //"http://www.google.ru/images/hp0.gif"
            );
            editor.addHyperlinkListener(new HyperlinkActivity());
            editor.setEditable(false);

            JScrollPane scrollablePane = new JScrollPane(editor, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            this.setLayout(new BorderLayout());
            this.add(scrollablePane, BorderLayout.CENTER);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
