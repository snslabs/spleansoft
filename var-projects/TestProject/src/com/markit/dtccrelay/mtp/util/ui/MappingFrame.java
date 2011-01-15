package com.markit.dtccrelay.mtp.util.ui;

import javax.swing.*;
import java.awt.*;

public class MappingFrame extends JFrame {
    MappingForm mappingForm;

    public MappingFrame() throws HeadlessException {
        super();    //To change body of overridden methods use File | Settings | File Templates.
        this.getContentPane().add( mappingForm.mainPanel );
    }
}
