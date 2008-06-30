package uz.sportloto.paynet.xml.transform.sax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import uz.sportloto.Configuration;
import uz.sportloto.paynet.model.response.DayReport;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class PaynetReportHandler extends PaynetSAXHandler {
    private static final Log log = LogFactory.getLog(PaynetReportHandler.class);
    
    private static final  Integer UNKNOWN = null;
    private static final  int REPORT = (1);
    private static final  int REPORT_DATE = (2);
    private static final  int PAY_COUNT = (3);
    private static final  int PAY_SUM = (4);
    public static final SimpleDateFormat SDF = new SimpleDateFormat(Configuration.getReportDateFormat());
    public static final DecimalFormat NF = new DecimalFormat(Configuration.getReportNumberFormat());
    private String terminalId;

    private static final Map tagMap = new HashMap();
    {
        tagMap.put("", UNKNOWN);
        tagMap.put("report", new Integer(REPORT));
        tagMap.put("pay_count", new Integer(PAY_COUNT));
        tagMap.put("report_date", new Integer(REPORT_DATE));
        tagMap.put("pay_sum", new Integer(PAY_SUM));
    }

    private Integer currentPosition = UNKNOWN;

    private DayReport dayReport;


    public void startDocument() throws SAXException {
        dayReport = new DayReport();
    }                               

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentPosition = (Integer) tagMap.get(qName);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        currentPosition = UNKNOWN;
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        if(currentPosition != null){
            final int curPos = currentPosition.intValue();
            final String value = new String(ch, start, length).trim();
            switch(curPos){
                case PAY_COUNT:
                    dayReport.setPayCount(Long.valueOf(value));
                    break;
                case REPORT_DATE:
                    try {
                        dayReport.setReportDate(SDF.parse(value));
                    }
                    catch (ParseException e) {
                        log.error("Canot parse report date: " + value+
                                "\nDefaults to 01-01-1970");
                        try{
                            dayReport.setReportDate(SDF.parse("01.01.1970"));
                        }
                        catch(Exception ex){
                            log.error(ex);
                            throw new RuntimeException("Check your code, date format incosistent. Check paynet.report.dateformat and paynet.report.defaultdate properties in config.");
                        }
                    }
                    break;
                case PAY_SUM:
                    try{
                        dayReport.setPaySum(new BigDecimal(value));
                    }
                    catch(Exception e){
                        log.error("Cannot parse sum received from Payent :"+value,e);
                    }
                    break;
            }
        }
    }

    public void endDocument() throws SAXException {

    }

    public DayReport getDayReport() {
        return dayReport;
    }

    public String toTextMode() {
        StringBuffer sb = new StringBuffer();
        sb.append("\nNomer terminala : ").append(terminalId).append("\n");
        sb.append("Data              : ").append(SDF.format(dayReport.getReportDate())).append("\n");
        sb.append("Kolichestvo oplat : ").append(NF.format(dayReport.getPayCount())).append("\n");
        sb.append("Summa oplat       : ").append(NF.format(dayReport.getPaySum())).append("\n");
        return sb.toString();
    }
}