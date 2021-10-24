package com.visual.bankproject.bankCode;
import java.util.ArrayList;
import java.util.Random;

public abstract class Account {
    private final int accountNumber;
    private float balance;
    private final String accountType;
    ArrayList<Float> accountStatement = new ArrayList<>();

    Random random = new Random();

    public Account(String accountType) {
        this.accountNumber = random.nextInt(100000)+1;
        this.accountType = accountType;
    }

    public void deposit(float value) {
        this.setBalance(this.getBalance() + value);
        getAccountStatement().add(value);
    }

    public abstract void withdraw(float value);

    public int getAccountNumber() {
        return accountNumber;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public ArrayList<Float> getAccountStatement() {
        return accountStatement;
    }

    public void setAccountStatement(ArrayList<Float> accountStatement) {
        this.accountStatement = accountStatement;
    }

    public String getAccountType(){
        return accountType;
    }
}