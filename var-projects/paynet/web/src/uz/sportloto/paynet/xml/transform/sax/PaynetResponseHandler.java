package uz.sportloto.paynet.xml.transform.sax;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import uz.sportloto.Configuration;
import uz.sportloto.paynet.model.response.Transaction;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PaynetResponseHandler extends PaynetSAXHandler {
    private static final Log log = LogFactory.getLog(PaynetResponseHandler.class);

    private static final String ATT_LABEL = "label";
    private static final int UNKNOWN = -1;
    private static final int STATUS = (1);
    private static final int STATUS_TEXT = (2);
    private static final int RECEIPT = (3);
    private static final int DETAILS = (4);

    private final SimpleDateFormat sdf = new SimpleDateFormat(Configuration.getTransactionDateFormat());

    private int currentPosition = UNKNOWN;
    private String currentDetailsTag;
    private Transaction.ReceiptDetail currentReceiptDetail;


    private static final Map<String, Integer> tagMap = new HashMap<String, Integer>();

    {
        tagMap.put("", UNKNOWN);
        tagMap.put("status", STATUS);
        tagMap.put("receipt", RECEIPT);
        tagMap.put("status_text", STATUS_TEXT);
        tagMap.put("details", DETAILS);
    }

    private Transaction transaction;

    public Transaction getResponse() {
        return transaction;
    }

    public void startDocument() throws SAXException {
        transaction = new Transaction();
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (currentPosition == UNKNOWN) {
            currentPosition = tagMap.get(qName) == null ? UNKNOWN : tagMap.get(qName);
        } else if (currentPosition == RECEIPT) {
            // если мы внутри тэга receipt и начался новый тэг - то это какая то строчка из чека
            currentReceiptDetail = transaction.getReceiptValues().get(qName);
            if (currentReceiptDetail == null) {
                currentReceiptDetail = new Transaction.ReceiptDetail();
                transaction.getReceiptValues().put(qName, currentReceiptDetail);
                if(attributes.getValue(ATT_LABEL) != null){
                    currentReceiptDetail.setFieldLabel(attributes.getValue(ATT_LABEL));
                }
            }
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("receipt")) {
            final StringBuffer receiptDetails = new StringBuffer();
            for (Transaction.ReceiptDetail rd : transaction.getReceiptValues().values()) {
                if (rd.getFieldLabel() != null && rd.getFieldLabel().length()>0) {
                    receiptDetails.append(rd.getFieldLabel()).append(":");
                }
                receiptDetails.append(rd.getFieldValue()).append("\n");
            }
            transaction.setReceiptDetails(receiptDetails.toString());
        } else if (currentPosition == RECEIPT) {
            // закончился очередной тэг чека, текущий тэг - null
            currentReceiptDetail = null;
        }
        if (tagMap.get(qName) != null) {
            currentPosition = UNKNOWN;
        }

    }

    public void characters(char ch[], int start, int length) throws SAXException {
        if (currentPosition != UNKNOWN) {
            final int curPos = currentPosition;
            final String value = new String(ch, start, length);
            switch (curPos) {
                case STATUS:
                    transaction.setStatusCode(Integer.valueOf(value));
                    break;
                case STATUS_TEXT:
                    transaction.setStatusText(value);
                    break;
                case RECEIPT:
                    if (currentReceiptDetail != null) {
                        currentReceiptDetail.setFieldValue(currentReceiptDetail.getFieldValue()==null?value:(currentReceiptDetail.getFieldValue() + value));
                    }
                    break;
            }
        }
    }

    /**
     * Метод который пытается вынуть из строки данные
     *
     * @param key        имя тэга в RECEIPT в котором содержится нужная информация
     * @param regExp     регулярное выражение которое надо использовать чтобы достать нужные данные
     * @param groupIndex номер группы в регулярном выражении содержащей нужные данные
     * @return строку с найденными данными или null если это не та строка
     */
    private String parseValue(String key, String regExp, int groupIndex) {
        final Transaction.ReceiptDetail detail = transaction.getReceiptValues().get(key);
        if(detail == null){
            return null;
        }
        final String value = detail.getFieldValue();
        final Matcher matcher = Pattern.compile(regExp).matcher(value);
        if (matcher.find()) {
            return matcher.group(groupIndex);
        }
        return null;
    }

    private BigDecimal parseBigDecimal(String key, String regExp, int groupIndex) {
        final String stringValue = parseValue(key, regExp, groupIndex);
        if (stringValue != null) {
            try {
                return new BigDecimal(stringValue);
            }
            catch (NumberFormatException e) {
                log.error("Parameter " + key + " in response cannot be converted into BigDecimal due string value \"" + stringValue + "\" is NaN!", e);
            }
        }
        return null;
    }

    private Date parseDate(String key, String regExp, int groupIndex) {
        final String stringValue = parseValue(key, regExp, groupIndex);
        if (stringValue != null) {
            try {
                return sdf.parse(stringValue);
            }
            catch (ParseException e) {
                log.error("Parameter " + key + " in response cannot be converted into Date due string value \"" + stringValue + "\" does not match " + Configuration.getTransactionDateFormat() + " pattern!", e);
            }
        }
        return null;
    }

    public void endDocument() throws SAXException {
        transaction.setPaynetChequeId(parseValue("transaction_id", "(.+)", 1));
        transaction.setSum(parseBigDecimal("summa", "(\\d+[\\.,]?(\\d)*)", 1));
        transaction.setCommission(parseBigDecimal("comission", "(\\d+[\\.,]?(\\d)*)", 1));
        transaction.setTransactionDate(parseDate("time", "(.+)", 1));
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public String toTextMode() {
        StringBuffer sb = new StringBuffer("_PaynetResponse = {\n");
        if (transaction == null || transaction.getStatusCode() == null) {
            sb.append("\tstatus_code = -999,\n");
            sb.append("\tstatus_text = \"Неверный формат ответа\",\n");
            sb.append("\tresponse_text = nil\n");
        } else if (transaction.getStatusCode() != 0) {
            sb.append("\tstatus_code = ").append(transaction.getStatusCode()).append(",\n");
            sb.append("\tstatus_text = \"").append(wrap(transaction.getStatusText())).append("\",\n");
            sb.append("\tresponse_text = nil\n");
        } else {
            sb.append("\tstatus_code = ").append(transaction.getStatusCode()).append(",\n");
            sb.append("\tstatus_text = \"").append(wrap(transaction.getStatusText())).append("\",\n");
            sb.append("\tresponse_text = \"").append(wrap(transaction.getReceiptDetails())).append("\"\n");
        }
        return sb.append("\n}").toString();

    }

    private String wrap(String receiptDetails) {
        return receiptDetails.replaceAll("\n","\\\\n\" ..\n \"");
    }
}
