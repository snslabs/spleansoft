package uz.sportloto.paynet.model.response;

import java.io.Serializable;

public class Field extends AbstractModel implements Serializable{
    private String name;
    private String type;
    private Integer size;
    private String label;
    private String required;
    private ServiceType serviceType;

    public Field() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }


    public String toString() {
        return "\t\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\tserviceTypeId = \""+getServiceType().getServiceTypeId()+"\",\n" +
                "\t\t\t\t\t\t\t\tname    = \""+name+"\",\n" +
                "\t\t\t\t\t\t\t\ttype    = \""+type+"\",\n" +
                "\t\t\t\t\t\t\t\tsize    = \""+size+"\",\n" +
                "\t\t\t\t\t\t\t\tlabel   = \""+label+"\",\n" +
                "\t\t\t\t\t\t\t\trequired = \""+required+"\",\n" +
                "\t\t\t\t\t\t\t}";
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public boolean isFieldRequired(){
        return "true".equalsIgnoreCase(getRequired());
    }


    public boolean equals(Object obj) {
        return obj instanceof Field && ((Field)obj).name.equals(this.name) && ((Field) obj).getServiceType().getServiceTypeId().equals(this.getServiceType().getServiceTypeId());
    }

    public int hashCode() {
        return this.name.hashCode()*1024*(this.serviceType.getServiceTypeId());
    }
}
