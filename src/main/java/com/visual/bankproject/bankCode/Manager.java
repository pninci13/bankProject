package com.visual.bankproject.bankCode;

import java.util.ArrayList;

public class Manager extends User{
    private final ArrayList<Client> clients = new ArrayList<>();

    public Manager(String email, String password) {
        super(email, password, "manager");
    }

    public ArrayList<Client> getClients(){
        return clients;
    }


}