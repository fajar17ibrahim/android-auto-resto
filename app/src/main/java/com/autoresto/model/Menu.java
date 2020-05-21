package com.autoresto.model;

import com.google.gson.annotations.SerializedName;

public class Menu {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private float price;

    @SerializedName("stok")
    private int stok;

    @SerializedName("photo")
    private String photo;

    private boolean checked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Menu(int id, String name, String description, float price, int stok, String photo, boolean checked) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stok = stok;
        this.photo = photo;
        this.checked = checked;
    }
}
