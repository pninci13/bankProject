package com.visual.bankproject.bankCode;

import com.visual.bankproject.bankCode.Account;

public class Saving extends Account {
    private double accountInterestRate;

    public Saving() {
        super("Saving");
    }

    @Override
    public void withdraw(float value) {
        if(getBalance() > 0 && value <= getBalance()){
            setBalance(getBalance() - value);
            getAccountStatement().add(-value);
        }else{
            System.out.println("It was not possible to withdraw your value");
        }
    }
}