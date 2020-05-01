package com.autoresto.model;

public class Food {

    private String name;

    private String photo;

    private String price;

    private boolean isChecked = false;


    public Food() {

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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Food(String name, String photo, String price, boolean isChecked) {
        this.name = name;
        this.photo = photo;
        this.price = price;
        this.isChecked = isChecked;
    }
}
