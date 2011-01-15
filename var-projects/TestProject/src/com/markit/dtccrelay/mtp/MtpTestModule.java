package com.markit.dtccrelay.mtp;

import com.markit.dtccrelay.mtp.util.XSLParser;
import com.markit.dtccrelay.mtp.util.MappingTableModel;
import com.markit.dtccrelay.mtp.util.ui.MappingForm;

import javax.swing.*;

public class MtpTestModule {

    public static void main(String[] args) throws Exception {
        XSLParser xslParser = new XSLParser();
        xslParser.process();
        /*
        JFrame frame = new JFrame("DTCC Relay fields mapping browser");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        MappingForm mf = new MappingForm(new MappingTableModel(xslParser.getCreditMapping()));
        frame.setContentPane( mf.mainPanel );
        frame.setSize(600,800);
        frame.setVisible(true);
        */
    }

}
