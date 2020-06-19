package com.autoresto.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Table {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("ip_address")
    private String ip_address;

    @SerializedName("description")
    private String description;

    @SerializedName("choosen")
    private boolean choosen;

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

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isChoosen() {
        return choosen;
    }

    public void setChoosen(boolean choosen) {
        this.choosen = choosen;
    }

    public Table(int id, String name, String ip_address, String description, boolean choosen) {
        this.id = id;
        this.name = name;
        this.ip_address = ip_address;
        this.description = description;
        this.choosen = choosen;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
