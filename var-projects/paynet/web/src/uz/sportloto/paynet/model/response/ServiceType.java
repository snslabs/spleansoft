package uz.sportloto.paynet.model.response;

import java.util.List;
import java.util.ArrayList;

public class ServiceType extends AbstractModel {
    private Integer serviceTypeId;
    private List<Field> fields = new ArrayList<Field>();

    public ServiceType() {
    }

    public ServiceType(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }


    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{\n");
        for (Field field : fields) {
            sb.append(field).append(",\n");
        }
        sb.append("\t\t\t}");
        return sb.toString();
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }
}
