package com.sns.model;

import java.util.List;
import java.util.ArrayList;

public class User {
    private Integer id;
    private String name;
    private String password;
    private UserDetails userDetails;
    private List phoneList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
        phoneList = new ArrayList();
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public List getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List phoneList) {
        this.phoneList = phoneList;
    }

    public String toString() {
        return "[id="+id+";name="+name+";password="+password+":"+userDetails+" : phoneList="+phoneList+"]";
    }
}
