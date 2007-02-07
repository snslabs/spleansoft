package ru.sns.ui;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class PositionForm {
    private JPanel positionPanel;
    private JComboBox instrumentType;
    private JComboBox optionType;
    private JTextField strike;
    private JSpinner quantity;
    private JTextField price;
    private PositionBean pb;


    private static final NumberFormat  nf = new DecimalFormat("0.000");

    public PositionForm(PositionBean data) {
        pb = data;
        instrumentType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                optionType.setEnabled(instrumentType.getSelectedItem().equals("Option"));
                strike.setEnabled(instrumentType.getSelectedItem().equals("Option"));
            }
        });
        FocusAdapter pcl = new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                getData(pb);
            }
        };
        instrumentType.addFocusListener(pcl);
        optionType.addFocusListener(pcl);
        strike.addFocusListener(pcl);
        price.addFocusListener(pcl);
        quantity.addFocusListener(pcl);

        quantity.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                getData(pb);
            }
        });
        setData(pb);
    }

    public void setData(PositionBean data) {
        strike.setText(nf.format(data.getStrike()));
        price.setText(nf.format(data.getPrice()));
        instrumentType.setSelectedItem(data.getInstrumentType());
        optionType.setSelectedItem(data.getOptionType());
        quantity.setValue(data.getQuantity());
    }

    public void getData(PositionBean data) {
        try{
            data.setStrike(nf.parse(strike.getText().replaceAll("\\.",",")).doubleValue());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        try{
            data.setPrice(nf.parse(price.getText().replaceAll("\\.",",")).doubleValue());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        data.setInstrumentType((String) instrumentType.getSelectedItem());
        data.setOptionType((String) optionType.getSelectedItem());
        data.setQuantity(((Integer) quantity.getValue()).intValue());
    }

    public boolean isModified(PositionBean data) {
        if (!nf.format(data.getStrike()).equals(strike.getText()))
            return true;
        if (!nf.format(data.getPrice()).equals(price.getText()))
            return true;
        if(!instrumentType.getSelectedItem().equals(data.getInstrumentType()))
            return true;
        if(!optionType.getSelectedItem().equals(data.getOptionType()))
            return true;
        return false;
    }

    public JPanel getPositionPanel() {
        return positionPanel;
    }


    public PositionBean getPb() {
        return pb;
    }
}
