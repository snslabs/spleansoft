package ru.sns.ui;

import ru.sns.ui.model.Ticker;

import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Date;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class TickerParser {
    public static final String DEFAULT_URL = "http://www.rts.ru/ru/archive/fortsmarketcsvresults.html?def=2&ns=O&type=2";

    public static List<Ticker> parseURL() {
        return parseURL(DEFAULT_URL);
    }
    public static List<Ticker> parseURL(String uri) {
        ArrayList<Ticker> list = new ArrayList<Ticker>();

        try {
            URL url = new URL(uri);
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                try {
                    StringTokenizer strTok = new StringTokenizer(line, ";", false);
                    Ticker ticker = new Ticker();
                    ticker.setContractCode(strTok.nextToken());
                    ticker.setExerciseDate(pdate(strTok.nextToken()));
                    ticker.setPriceAvg(pd(strTok.nextToken()));
                    ticker.setPriceMax(pd(strTok.nextToken()));
                    ticker.setPriceMin(pd(strTok.nextToken()));
                    ticker.setPriceLast(pd(strTok.nextToken()));
                    ticker.setChange(pd(strTok.nextToken()));
                    ticker.setVolumeLast(pi(strTok.nextToken()));
                    ticker.setDeals(pi(strTok.nextToken()));
                    ticker.setTradeVolume(pd(strTok.nextToken()));
                    ticker.setTradeVolumeContr(pi(strTok.nextToken()));
                    ticker.setOpenPositions(pd(strTok.nextToken()));
                    ticker.setOpenContracts(pi(strTok.nextToken()));
                    if(ticker.getDeals() != 0){
                        list.add(ticker);
                    }
                }
                catch (Exception e) {
                    // e.printStackTrace();
                }
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private static double pd(String s) {
        try {
            return Double.parseDouble(s.replaceAll("\\,", "."));
        }
        catch (Exception e) {
            return 0;
        }
    }

    private static int pi(String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (Exception e) {
            return 0;
        }
    }

    private static Date pdate(String s) {
        try {
            return sdf.parse(s);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        List l = parseURL(DEFAULT_URL);
        System.out.println(l);
    }

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
}
