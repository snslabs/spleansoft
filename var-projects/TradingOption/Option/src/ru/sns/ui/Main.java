package ru.sns.ui;

import ru.sns.ui.functions.Option;
import ru.sns.ui.functions.Futures;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame mf = new MainFrame(frame);
        frame.setContentPane(mf.getMainPane());
        frame.setSize(800,600);
/*
        mf.getGraphPanel().functionList.add(
                new Option(20.5, 1.2, -2, Option.CALL)
        );
        mf.getGraphPanel().functionList.add(
                new Option(20, 1.8, 1, Option.CALL)
        );
        mf.getGraphPanel().functionList.add(
                new Option(21, 0.6, 1, Option.CALL)
        );
*/
                /*
        mf.getGraphPanel().functionList.add(
                new Option(20.5, 1.6, -2, Option.CALL)
        );
        mf.getGraphPanel().functionList.add(
                new Futures(20.2, 2)
        );
        mf.getGraphPanel().functionList.add(
                new Option(20, 0.8, 1, Option.PUT)
        );
        mf.getGraphPanel().functionList.add(
                new Option(21, 1.5, 1, Option.PUT)
        );
                  */
        frame.setVisible(true);
    }
}
