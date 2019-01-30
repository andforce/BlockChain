package com.andforce.block.bean;

import org.json.JSONObject;


public class Transactions {

    private double amount;
    private String recipient;
    private String sender;


    public Transactions() {

    }

    public Transactions(JSONObject json) {

        this.amount = json.optDouble("amount");
        this.recipient = json.optString("recipient");
        this.sender = json.optString("sender");

    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


}
