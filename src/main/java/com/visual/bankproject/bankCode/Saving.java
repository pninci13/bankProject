package com.visual.bankproject.bankCode;

import com.visual.bankproject.bankCode.Account;

public class Saving extends Account {
    public float getAccountInterestRate() {
        return accountInterestRate;
    }

    public void setAccountInterestRate(float accountInterestRate) {
        this.accountInterestRate = accountInterestRate;
    }

    private float accountInterestRate;

    public Saving() {
        super("Saving");
    }

    @Override
    public boolean withdraw(float value) {
        if(getBalance() > 0 && value <= getBalance()){
            setBalance(getBalance() - value);
            getAccountStatement().add(-value);
            return true;
        }else{
            System.out.println("It was not possible to withdraw your value");
            return false;
        }
    }
}