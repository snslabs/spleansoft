package uz.sportloto.paynet.model.response;

import java.math.BigDecimal;

public class Service extends AbstractModel {
    private Integer serviceId;
    private String name;
    private Integer providerId;
    private Provider provider;
    private Integer categoryId;
    private Category category;
    private Integer serviceTypeId;
    private ServiceType serviceType;
    private String chequeInfo;
    private BigDecimal servicePrice;

    public Service() {
    }

    public Service(Integer serviceId, String name, Integer providerId, Integer categoryId,  Integer serviceTypeId) {
        this.serviceId = serviceId;
        this.providerId = providerId;
        this.categoryId = categoryId;
        this.name = name;
        this.serviceTypeId = serviceTypeId;
    }


    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public String getChequeInfo() {
        return chequeInfo;
    }

    public void setChequeInfo(String chequeInfo) {
        this.chequeInfo = chequeInfo;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String toString() {
        return "\n\t\t{\n" +
                "\t\t\tid            = \""+serviceId+"\",\n" +
                "\t\t\tproviderId    = \""+getProvider().getProviderId()+"\",\n" +
                "\t\t\tcategoryId    = \""+getCategory().getCategoryId()+"\",\n" +
                "\t\t\tname          = \""+name+"\",\n" +
                "\t\t\tserviceTypeId = \""+getServiceType().getServiceTypeId()+"\",\n" +
                "\t\t\tchequeInfo    = \""+wrap(chequeInfo)+"\",\n" +
                "\t\t\tserviceType   = " + serviceType+",\n" +
                "\t\t\tservicePrice  = " + servicePrice +"\n" +
                "\t\t}";
    }

    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    private String wrap(String receiptDetails) {
        return chequeInfo != null?receiptDetails.replaceAll("\\^","\\\\n"):"";
    }
}


