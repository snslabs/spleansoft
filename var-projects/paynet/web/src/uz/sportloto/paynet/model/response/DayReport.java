package uz.sportloto.paynet.model.response;

import java.util.Date;
import java.math.BigDecimal;

public class DayReport {
    private Date reportDate;
    private BigDecimal paySum;
    private Long payCount;

    public DayReport() {
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public BigDecimal getPaySum() {
        return paySum;
    }

    public void setPaySum(BigDecimal paySum) {
        this.paySum = paySum;
    }

    public Long getPayCount() {
        return payCount;
    }

    public void setPayCount(Long payCount) {
        this.payCount = payCount;
    }
}
