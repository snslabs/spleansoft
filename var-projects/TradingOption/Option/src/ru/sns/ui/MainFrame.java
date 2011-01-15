package ru.sns.ui;

import ru.sns.ui.functions.Futures;
import ru.sns.ui.functions.Option;
import ru.sns.ui.model.OptionsTableModel;
import ru.sns.ui.model.OptionsColumnModel;
import ru.sns.ui.model.Ticker;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame {

    private JFrame frame;

    private JPanel mainPane;
    private JButton bGo;
    private GraphPane graphPanel;
    private JButton zoomInYButton;
    private JButton zoomOutYButton;
    private JButton addButton;
    private JScrollPane scrollPane;
    private Container positionsPanel;
    private JPanel positionsPanelParent;
    private JButton bGetData;
    private JButton autoscaleButton;

    private JDialog childForm;
    private JTable table;
    private OptionsTableModel optionsModel = new OptionsTableModel();
    private OptionsColumnModel columnModel = new OptionsColumnModel();
    private List<PositionBean> positions = new ArrayList<PositionBean>();


    public MainFrame(JFrame frameOwner) {
        this.frame = frameOwner;
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if("in".equals(actionEvent.getActionCommand())){
                    graphPanel.zoomIn();
                }
                if("out".equals(actionEvent.getActionCommand())){
                    graphPanel.zoomOut();
                }
            }
        };
        zoomOutYButton.addActionListener( actionListener );
        zoomInYButton.addActionListener(actionListener);
        // добавляем новую позицию к списку
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                addNewPosition();
                // positionsPanel.paintAll(positionsPanel.getGraphics());

            }
        });
        bGo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList list = graphPanel.functionList;
                list.clear();
                for(PositionBean pos: positions){
                    if("Futures".equals(pos.getInstrumentType())){
                        list.add( new Futures(pos.getPrice(), pos.getQuantity()) );
                    }
                    else if("Option".equals(pos.getInstrumentType())){
                        if("Call".equals(pos.getOptionType())){
                            list.add(new Option(pos.getStrike(), pos.getPrice(), pos.getQuantity(), Option.CALL));
                        }
                        else{
                            list.add(new Option(pos.getStrike(), pos.getPrice(), pos.getQuantity(), Option.PUT));
                        }
                    }
                }
                graphPanel.updateUI();

            }
        });
        bGetData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                childForm.setVisible(true);
            }
        });

        childForm = new JDialog(getFrame(), true);
        childForm.getContentPane().setLayout(new BorderLayout());
        optionsModel.setData( TickerParser.parseURL()  );
        table = new JTable(optionsModel);
//        table.setColumnModel(columnModel);
        JScrollPane _scrollPane = new JScrollPane(table);
        childForm.add( _scrollPane );
        childForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        childForm.setSize(800,600);
        table.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() > 1){
                    addNewPosition(optionsModel.getData().get(table.getSelectedRow()));
                    childForm.setVisible(false);
                }
            }
        }
        );

        autoscaleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                graphPanel.autoscale();
                graphPanel.updateUI();
            }
        });
    }

    public void addNewPosition(Ticker ticker) {
        String price = ticker.getContractCode().substring(ticker.getContractCode().indexOf(" ")+1);
        addNewPosition("Option", ticker.getContractCode().indexOf("PA ")!=-1?"Put":"Call",
                Double.parseDouble(price), ticker.getPriceLast(),0);
    }
    public void addNewPosition() {
        addNewPosition("Option", "Call",0,0,0);
    }
    public void addNewPosition(String instrumentType, String optionType, double strike, double price, int quantity) {
        PositionBean position = new PositionBean(instrumentType, optionType, strike, price, quantity);
        System.out.println(position);
        positions.add(position);
        PositionForm pf = new PositionForm(position);
        GridBagConstraints c = new GridBagConstraints ();
        c.gridx = 0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.LINE_START;
        positionsPanel.add( pf.getPositionPanel(), c );
        scrollPane.updateUI();
    }
    public JPanel getMainPane() {
        return mainPane;
    }


    public GraphPane getGraphPanel() {
        return graphPanel;
    }


    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
