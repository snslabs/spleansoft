package uz.sportloto.paynet.model.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.LinkedHashMap;

public class Transaction {
    private Long id;
    private Integer statusCode;
    private String statusText;
    private String receiptDetails;
    private String paynetChequeId;
    private Date transactionDate;
    private BigDecimal sum;
    private BigDecimal commission;
    private Map<String, ReceiptDetail> receiptValues = new LinkedHashMap<String, ReceiptDetail>();
    

    public Transaction() {
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getReceiptDetails() {
        return receiptDetails;
    }

    public void setReceiptDetails(String receiptDetails) {
        if(receiptDetails!=null && receiptDetails.length() > 2000){
            this.receiptDetails = receiptDetails.substring(0,2000);
        }
        else{
            this.receiptDetails = receiptDetails;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaynetChequeId() {
        return paynetChequeId;
    }

    public void setPaynetChequeId(String paynetChequeId) {
        this.paynetChequeId = paynetChequeId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String toString() {
        return "status = " + statusCode + "\n" +
                "status_text = " + statusText + "\n" +
                "details = "+receiptDetails+"\n";
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public static class ReceiptDetail{
        String fieldLabel;
        String fieldValue;

        public String getFieldLabel() {
            return fieldLabel;
        }

        public void setFieldLabel(String fieldLabel) {
            this.fieldLabel = fieldLabel;
        }

        public String getFieldValue() {
            return fieldValue;
        }

        public void setFieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
        }
    }

    public Map<String, ReceiptDetail> getReceiptValues() {
        return receiptValues;
    }

    public void setReceiptValues(Map<String, ReceiptDetail> receiptValues) {
        this.receiptValues = receiptValues;
    }
}
