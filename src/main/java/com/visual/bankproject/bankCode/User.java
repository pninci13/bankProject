package com.visual.bankproject.bankCode;

public abstract class User {
    private String email;
    private String password;
    private String userType;

    public User(String email, String password, String userType) {
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}