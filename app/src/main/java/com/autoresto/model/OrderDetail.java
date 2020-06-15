package com.autoresto.model;

import com.google.gson.annotations.SerializedName;

public class OrderDetail {

    @SerializedName("id")
    private int id;

    @SerializedName("note")
    private String note;

    @SerializedName("qty")
    private int qty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public OrderDetail(int id, String note, int qty) {
        this.id = id;
        this.note = note;
        this.qty = qty;
    }
}
