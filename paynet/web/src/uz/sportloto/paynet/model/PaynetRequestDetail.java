package uz.sportloto.paynet.model;

public class PaynetRequestDetail {
    private Long id;
    private PaynetRequest paynetRequest;
    private String fieldName;
    private String fieldValue;

    public PaynetRequestDetail(String fieldName, String fieldValue, PaynetRequest paynetRequest) {
        this.paynetRequest = paynetRequest;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public PaynetRequestDetail(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public PaynetRequestDetail() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaynetRequest getPayment() {
        return paynetRequest;
    }

    public void setPayment(PaynetRequest paynetRequest) {
        this.paynetRequest = paynetRequest;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String toString() {
        return "PaynetRequestDetail{ id = " + id + "\n"+
                "   fieldName = " + fieldName + "\n"+
                "   fieldValue = " + fieldValue + "\n"+
                "   paynetRequestId = " + paynetRequest.getId()+"\n"+
                "}";
    }
}
