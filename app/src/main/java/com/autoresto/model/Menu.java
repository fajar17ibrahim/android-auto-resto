package com.autoresto.model;

import java.util.List;

public class Menu {

    private String id;

    private String name;

    private String price;

    private String photo;

    private String category;

    public Menu() {

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

    public Menu(String id, String name, String price, String photo, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.photo = photo;
        this.category = category;
    }
}
