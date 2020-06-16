package com.autoresto.model;

import com.google.gson.annotations.SerializedName;

public class OrderSendDetail {

    @SerializedName("menu_id")
    private int menu_id;

    @SerializedName("note")
    private String note;

    @SerializedName("qty")
    private int qty;

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
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


    public OrderSendDetail(int menu_id, String note, int qty) {
        this.menu_id = menu_id;
        this.note = note;
        this.qty = qty;
    }
}
