package com.visual.bankproject.bankCode;

import java.util.ArrayList;

public class Client extends User{
    private final ArrayList<Account> accounts = new ArrayList<>();

    public Client(String email, String password) {
        super(email, password, "client");
    }

    public ArrayList<Account> getAccounts(){
        return accounts;
    }

}