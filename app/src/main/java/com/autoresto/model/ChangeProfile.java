package com.autoresto.model;

import com.google.gson.annotations.SerializedName;

public class ChangeProfile {

    @SerializedName("name")
    private String name;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("gender")
    private String gender;

    @SerializedName("birth_day")
    private String birth_day;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(String birth_day) {
        this.birth_day = birth_day;
    }

    public ChangeProfile(String name, String username, String email, String phone, String gender, String birth_day) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birth_day = birth_day;
    }
}
