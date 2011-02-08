package uz.sportloto.paynet.model;

import net.sf.hibernate.lob.ClobImpl;
import uz.sportloto.paynet.model.response.Transaction;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaynetRequest {
    private Long id;
    private String internalChequeId;
    private String paynetChequeId;
    // 'R' получено от терминала, 'S' - отправлено в пейнет, 'C' - получен ответ из пейнета
    private char state;
    //    private Date receivedOn;
    private Date sentOn;
    //    private Date responseReceivedOn;
    private Integer providerId;
    private Integer serviceId;
    private BigDecimal sum;
    private List<PaynetRequestDetail> details = new ArrayList<PaynetRequestDetail>();
    private Integer statusCode;
    private String statusText;
    private String url;
    private String response;
    private Integer actionCode;
    private String terminalId;
    private String reportDate;
    private BigDecimal servicePrice;
    private Integer quantity;
    private Transaction transaction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInternalChequeId() {
        return internalChequeId;
    }

    public void setInternalChequeId(String internalChequeId) {
        this.internalChequeId = internalChequeId;
    }

    public String getPaynetChequeId() {
        return paynetChequeId;
    }

    public void setPaynetChequeId(String paynetChequeId) {
        this.paynetChequeId = paynetChequeId;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Date getSentOn() {
        return sentOn;
    }

    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }

    public List<PaynetRequestDetail> getDetails() {
        return details;
    }

    public void setDetails(List<PaynetRequestDetail> details) {
        this.details = details;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String toString() {
        return
                "PaynetRequest { \n" +
                        "   id = " + id + "\n" +
                        "   internalChequeId = " + internalChequeId + "\n" +
                        "   paynetChequeId = " + paynetChequeId + "\n" +
                        "   state = " + state + "\n" +
                        "   sentOn = " + sentOn + "\n" +
                        "   providerId = " + providerId + "\n" +
                        "   serviceId = " + serviceId + "\n" +
                        "   sum = " + sum + "\n" +
                        "   details = " + details + "\n" +
                        "}";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Integer getActionCode() {
        return actionCode;
    }

    public void setActionCode(Integer actionCode) {
        this.actionCode = actionCode;
    }

    public String getTerminalId() {
        return terminalId;
    }

    /**
     * Установить идентификатор терминала PAYNET (а не sportlotto)
     *
     * @param terminalId идентификатор
     */
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

}
