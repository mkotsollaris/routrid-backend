package com.bankapp.business.model;

public class PaymentEvent {

    String from ;
    String to;
    String chargeBearer;
    ChargeAmount chargeAmt;


    public PaymentEvent(String from, String to, String chargeBearer, ChargeAmount chargeAmt) {
        this.from = from;
        this.to = to;
        this.chargeBearer = chargeBearer;
        this.chargeAmt = chargeAmt;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getChargeBearer() {
        return chargeBearer;
    }

    public void setChargeBearer(String chargeBearer) {
        this.chargeBearer = chargeBearer;
    }

    public ChargeAmount getChargeAmt() {
        return chargeAmt;
    }

    public void setChargeAmt(ChargeAmount chargeAmt) {
        this.chargeAmt = chargeAmt;
    }
}
