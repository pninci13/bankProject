package com.visual.bankproject.bankCode;

import com.visual.bankproject.bankCode.Account;

public class Saving extends Account {
    private double accountInterestRate;

    public Saving() {
        super("Saving");
    }

    public void deposit(float value) {
        this.setBalance(this.getBalance() + value);
        getAccountStatement().add(value);
    }

    public void withdraw(float value) {
        if(getBalance() > 0 && value <= getBalance()){
            setBalance(getBalance() - value);
            getAccountStatement().add(-value);
        }else{
            System.out.println("It was not possible to withdraw your value");
        }
    }

    public void accountBalanceStatus() {
        System.out.println("Account balance: " + getBalance());
    }

    public void accountStatement() {
        System.out.println("   Account transactions history");
        System.out.println("----------------------------------");
        for(Float number : accountStatement){
            System.out.println(number);
        }
    }
}