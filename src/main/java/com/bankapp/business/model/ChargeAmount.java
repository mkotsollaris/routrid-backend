package com.bankapp.business.model;

public class ChargeAmount {

    String currency;
    String amount;

    public String getCurrency() {
        return currency;
    }

    public ChargeAmount(String currency, String amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


}
