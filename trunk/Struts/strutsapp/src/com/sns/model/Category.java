package com.sns.model;

import java.util.Set;

public class Category {
    private Integer id;
    private String title;
    private Set adverts;

    public Category() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set getAdverts() {
        return adverts;
    }

    public void setAdverts(Set adverts) {
        this.adverts = adverts;
    }
}
