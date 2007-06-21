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
    private List<List<AbstractCandel>> allData;
    private int vLevel = 2;
    private List<AbstractCandel> data = new ArrayList<AbstractCandel>();
    private Map<String, List<AbstractCandel>> map = new LinkedHashMap<String, List<AbstractCandel>>();
    private static final int GRAPHICS_PER_PAGE = 3;

    public static void main(String[] args) throws Exception {
        IntradayPrinter intradayPrinter = new IntradayPrinter();
        intradayPrinter.loadData("C:\\Serge\\Projects\\IntradayPrinter\\data\\EESR_070110_070614.txt");
//        intradayPrinter.preview();
//        /*
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
/* */
    }

    private void preview() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
        final IntradayPrinter this_ = this;
        JPanel comp = new JPanel() {
            final Iterator<List<AbstractCandel>> iterator = map.values().iterator();

            public void paint(Graphics g) {
                try {
                    this_.print(g, new PageFormat(), 0);
                }
                catch (PrinterException e) {
                    e.printStackTrace();
                }
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
        while ((line = reader.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line, "\t", false);
            FiveMinute candel = new FiveMinute();
            candel.setTicker(st.nextToken());
            st.nextToken();
            candel.setDateTime(SDF.parse(st.nextToken() + st.nextToken()));
            candel.setOpen(new BigDecimal(st.nextToken()));
            candel.setHigh(new BigDecimal(st.nextToken()));
            candel.setLow(new BigDecimal(st.nextToken()));
            candel.setClose(new BigDecimal(st.nextToken()));
            if (st.hasMoreTokens()) {
                candel.setVolume(new BigDecimal(st.nextToken()));
            }

            d(candel);
            data.add(candel);
        }
        i("Total " + data.size() + " candels;");
        reader.close();
        groupByDays();
        final Iterator<List<AbstractCandel>> iterator = map.values().iterator();
        allData = new ArrayList<List<AbstractCandel>>();
        while (iterator.hasNext()) {
            allData.add(iterator.next());
        }
    }

    private void groupByDays() {
        for (AbstractCandel candel : data) {
            String key = DF.format(candel.getDateTime());
            List<AbstractCandel> lst = map.get(key);
            if (lst == null) {
                lst = new ArrayList<AbstractCandel>();
                map.put(key, lst);
            }
            lst.add(candel);
        }
        d("Found " + map.size() + " days.");
    }

    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if ((pageIndex * GRAPHICS_PER_PAGE) > allData.size()) {
            return Printable.NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        g2d.scale(0.5, 0.5);
        /*
        // print rulers
        for(int i = 0;i<2000;i+=100){
            g2d.drawLine(i,0,i,5);
            g2d.drawLine(0,i,5,i);
            g2d.drawString(""+i,i-20,10);
            g2d.drawString(""+i,5,i);
        }
        /* */
        for (int i = 0; i < GRAPHICS_PER_PAGE; i++) {
//            new Painter(allData.get(i + pageIndex * 5), (i % 2 == 0) ? 20 : 550, 20 + 400 * (i / 2)).paint(g2d);
            new Painter(allData.get(i + pageIndex * GRAPHICS_PER_PAGE), 20, 20 + 430 * i).paint(g2d);
        }
//            return Printable.NO_SUCH_PAGE;
//        return pageIndex == 0 ? Printable.PAGE_EXISTS : Printable.NO_SUCH_PAGE;
        return Printable.PAGE_EXISTS;
    }

    private void i(Object msg) {
        v(msg, 2);
    }

    private void d(Object msg) {
        v(msg, 4);
    }

    private void v(Object msg, int level) {
        if (vLevel >= level) {
            System.out.println(msg);
        }
    }
}
