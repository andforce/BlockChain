package com.andforce.block.bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Block {

    private long proof;
    private long timestamp;
    private ArrayList<Transactions> transactions;
    private String previousHash;
    private long index;


    public Block() {

    }

    public Block(JSONObject json) {

        this.proof = json.optLong("proof");
        this.timestamp = json.optLong("timestamp");

        this.transactions = new ArrayList<Transactions>();
        JSONArray arrayTransactions = json.optJSONArray("transactions");
        if (null != arrayTransactions) {
            int transactionsLength = arrayTransactions.length();
            for (int i = 0; i < transactionsLength; i++) {
                JSONObject item = arrayTransactions.optJSONObject(i);
                if (null != item) {
                    this.transactions.add(new Transactions(item));
                }
            }
        } else {
            JSONObject item = json.optJSONObject("transactions");
            if (null != item) {
                this.transactions.add(new Transactions(item));
            }
        }

        this.previousHash = json.optString("previous_hash");
        this.index = json.optInt("index");

    }

    public long getProof() {
        return this.proof;
    }

    public void setProof(long proof) {
        this.proof = proof;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<Transactions> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(ArrayList<Transactions> transactions) {
        this.transactions = transactions;
    }

    public String getPreviousHash() {
        return this.previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public long getIndex() {
        return this.index;
    }

    public void setIndex(long index) {
        this.index = index;
    }


}
