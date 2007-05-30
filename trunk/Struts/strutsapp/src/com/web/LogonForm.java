package com.web;

import org.apache.struts.validator.ValidatorForm;

public class LogonForm extends ValidatorForm {
    private String username;
    private String password;

    public LogonForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
