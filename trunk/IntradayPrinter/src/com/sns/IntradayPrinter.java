package com.sns;

import com.sns.model.AbstractCandel;
import com.sns.model.FiveMinute;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class IntradayPrinter implements Printable {
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final SimpleDateFormat DF = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat TF = new SimpleDateFormat("HHmmss");
    private int vLevel = 2;
    private List<AbstractCandel> data = new ArrayList<AbstractCandel>();
    private Map<String, List<AbstractCandel>> map = new HashMap<String, List<AbstractCandel>>();

    public static void main(String[] args) throws Exception {
        IntradayPrinter intradayPrinter = new IntradayPrinter();
        intradayPrinter.loadData("C:\\Serge\\Projects\\IntradayPrinter\\data\\EESR_070110_070614.txt");
//        intradayPrinter.preview();
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(intradayPrinter);
        if (printJob.printDialog()) {
            try {
                printJob.print();
            }
            catch (PrinterException pe) {
                System.out.println("Error printing: " + pe);
            }
        }

    }

    private void preview() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setVisible(true);
        final Iterator<List<AbstractCandel>> iterator = map.values().iterator();
        final Painter p = new Painter(iterator.next(), 20, 20);
        final Painter p2 = new Painter(iterator.next(), 550, 20);
        JPanel comp = new JPanel() {
            final Iterator<List<AbstractCandel>> iterator = map.values().iterator();

            public void paint(Graphics g) {
                p.paint((Graphics2D) g);
                p2.paint((Graphics2D) g);
            }
        };
        frame.getContentPane().add(comp);
        comp.updateUI();

    }

    private void loadData(String filePath) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        i("Reading data from file : " + filePath);
        String line = null;
        // skipping first line
        reader.readLine();
        while((line = reader.readLine())!=null){
            StringTokenizer st = new  StringTokenizer(line,"\t", false);
            FiveMinute candel = new FiveMinute();
            candel.setTicker(st.nextToken());
            st.nextToken();
            candel.setDateTime(SDF.parse(st.nextToken()+st.nextToken()));
            candel.setOpen(new BigDecimal(st.nextToken()));
            candel.setHigh(new BigDecimal(st.nextToken()));
            candel.setLow(new BigDecimal(st.nextToken()));
            candel.setClose(new BigDecimal(st.nextToken()));
            if(st.hasMoreTokens()){
                candel.setVolume(new BigDecimal(st.nextToken()));
            }

            d(candel);
            data.add(candel);
        }
        i("Total " + data.size()+" candels;");
        reader.close();

        groupByDays();
    }

    /**
     * ���������� ����� �� ����
     */
    private void groupByDays() {
        for(AbstractCandel candel : data){
            String key = DF.format(candel.getDateTime());
            List<AbstractCandel> lst = map.get(key);
            if(lst == null){
                lst = new ArrayList<AbstractCandel>();
                map.put(key,lst);
            }
            lst.add(candel);
        }
        d("Found " + map.size() + " days.");
    }

    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
//         Turn off double buffering
//        componentToBePrinted.paint(g2d);
        
        final Iterator<List<AbstractCandel>> iterator = map.values().iterator();
        final Painter p = new Painter(iterator.next(), 20, 20);
        final Painter p2 = new Painter(iterator.next(), 550, 20);
        p.paint(g2d);
        p2.paint(g2d);
        g2d.scale(3,3);
        return pageIndex==0?Printable.PAGE_EXISTS:Printable.NO_SUCH_PAGE;
    }

    /**
     * ���� ���� ������� 
     * @param msg
     */
    private void i(Object msg){
        v(msg,2);
    }
    /**
     * ���� ����� �������
     * @param msg
     */
    private void d(Object msg){
        v(msg,4);
    }

    private void v(Object msg,int level){
        if(vLevel >= level){
            System.out.println(msg);
        }
    }
}
