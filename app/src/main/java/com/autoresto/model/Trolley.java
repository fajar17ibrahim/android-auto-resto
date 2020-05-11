package com.autoresto.model;

public class Trolley {

    private String id;

    private String name;

    private String price;

    private String photo;

    private String category;

    private String note;

    private int qty;

    private String total;

    public Trolley() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Trolley(String id, String name, String price, String photo, String category, String note, int qty, String total) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.photo = photo;
        this.category = category;
        this.note = note;
        this.qty = qty;
        this.total = total;
    }
}
