package com.markit.dtccrelay.mtp.util.ui;

public class MappingFormBean {
    private String productType;
    private String transactionType;
    public MappingFormBean() {
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}