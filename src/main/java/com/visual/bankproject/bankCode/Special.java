package com.visual.bankproject.bankCode;

public class Special extends Account{
    private float limit;

    public Special(int accountNumber) {
        super(accountNumber, "Special");
    }

    public void deposit(float value) {
        setBalance(getBalance() + value);
        getAccountStatement().add(value);
    }

    public void withdraw(float value) {
        if(getBalance() > 0 && (getBalance() + this.limit) >= value){
            setBalance(getBalance() - value);
            if(getBalance() < 0){
                this.limit = getBalance() + this.limit;
                setBalance(0);
            }

            getAccountStatement().add(-value);
        }else if(getBalance() == 0 && this.limit > 0 && this.limit >= value){
            this.limit -= value;
            setBalance(0);
            getAccountStatement().add(-value);
        }else{
            System.out.println("It was not possible to withdraw your value");
        }
    }

    public void accountBalanceStatus() {
        System.out.println("Account balance: " + getBalance());
        System.out.println("Limit balance: " + this.limit);
    }
    public void accountStatement() {
        System.out.println("   Account transactions history");
        System.out.println("----------------------------------");
        for(Float number : accountStatement){
            System.out.println(number);
        }
    }
}