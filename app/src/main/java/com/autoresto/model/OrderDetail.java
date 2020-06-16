package com.autoresto.model;

import com.google.gson.annotations.SerializedName;

public class OrderDetail {

    @SerializedName("id")
    private int id;

    @SerializedName("note")
    private String note;

    @SerializedName("qty")
    private int qty;

    @SerializedName("subtotal")
    private float subtotal;

    @SerializedName("menu")
    private Menu menu;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

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

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public OrderDetail(int id, String note, int qty, float subtotal, Menu menu) {
        this.id = id;
        this.note = note;
        this.qty = qty;
        this.subtotal = subtotal;
        this.menu = menu;
    }
}
