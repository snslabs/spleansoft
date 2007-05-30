package com.sns.model;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class Category {
    private Integer id;
    private String title;
    private Set adverts;
    private List phoneList  = new ArrayList();

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

    public List getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List phoneList) {
        this.phoneList = phoneList;
    }
}
