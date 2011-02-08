package uz.sportloto.paynet.model.response;

public class Category extends AbstractModel {
    private String name;
    private Integer categoryId;

    public Category() {
    }

    public Category(String name, Integer categoryId) {
        this.name = name;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String toString() {
        return "Category {id = "+categoryId+"; name="+name+" }";
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public boolean equals(Object obj) {
        return obj instanceof Category && obj.toString().equals(this.toString());
    }
}
