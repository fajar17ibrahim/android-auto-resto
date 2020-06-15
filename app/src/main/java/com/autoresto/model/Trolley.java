package com.autoresto.model;

public class Trolley {

    private int id;

    private Menu menu;

    private Integer qty;
    private Float sub_total;
    private String note;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Float getSub_total() {
        return sub_total;
    }

    public void setSub_total(Float sub_total) {
        this.sub_total = sub_total;
    }

    public Trolley(int id, Menu menu, String note, Integer qty, Float sub_total, String note1) {
        this.id = id;
        this.menu = menu;
        this.note = note;
        this.qty = qty;
        this.sub_total = sub_total;
        this.note = note1;
    }
}
