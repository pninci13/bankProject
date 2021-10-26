package com.visual.bankproject.bankCode;

public class Special extends Account{
    public float getLimit() {
        return limit;
    }

    public void setLimit(float limit) {
        this.limit = limit;
    }

    private float limit;

    public Special() {
        super("Special");
    }

    @Override
    public boolean withdraw(float value) {
        if(getBalance() > 0 && (getBalance() + this.limit) >= value){
            setBalance(getBalance() - value);
            if(getBalance() < 0){
                this.limit = getBalance() + this.limit;
                setBalance(0);
            }

            getAccountStatement().add(-value);
            return true;
        }else if(getBalance() == 0 && this.limit > 0 && this.limit >= value){
            this.limit -= value;
            setBalance(0);
            getAccountStatement().add(-value);
            return true;
        }else{
            System.out.println("It was not possible to withdraw your value");
            return false;
        }
    }



}