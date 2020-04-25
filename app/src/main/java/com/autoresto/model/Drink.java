package com.autoresto.model;

public class Drink {

    private String name;

    private String photo;

    private String price;

    public Drink() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Drink(String name, String photo, String price) {
        this.name = name;
        this.photo = photo;
        this.price = price;
    }
}
