package com.visual.bankproject.bankCode;

public class Simple extends Account{

    public Simple() {
        super("Simple");
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