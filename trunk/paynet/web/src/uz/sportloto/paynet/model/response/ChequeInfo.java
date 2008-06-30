package uz.sportloto.paynet.model.response;

public class ChequeInfo extends AbstractModel {
    private Integer serviceId;
    private String info;

    public ChequeInfo() {
    }

    public ChequeInfo(Integer serviceId, String info) {
        this.serviceId = serviceId;
        this.info = info;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String toString() {
        return info;
    }

}
