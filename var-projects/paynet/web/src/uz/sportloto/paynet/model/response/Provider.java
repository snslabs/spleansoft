package uz.sportloto.paynet.model.response;

import java.util.List;
import java.util.ArrayList;

public class Provider extends AbstractModel {
    private Integer providerId;
    private Integer categoryId;
    private String name;
    private List<Service> services = new ArrayList<Service>();
    private Category category;

    public Provider() {
    }

    public Provider(Integer providerId, String name, Integer categoryId) {
        this.providerId = providerId;
        this.name = name;

        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String toString() {
        return "\n{\tname = \""+name+"\",\n" + "\tid = \""+providerId+"\",\n"+"\tservices = {"+

                toLua(services)

                +"\n\t}\n}";
    }

    private String toLua(List list){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            Object o =  list.get(i);
            sb.append(o.toString());
            if(i<list.size()-1){
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public boolean equals(Object obj) {
        return obj instanceof Provider && obj.toString().equals(this.toString());
    }
}
