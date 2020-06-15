package com.autoresto.model;

import com.google.gson.annotations.SerializedName;

public class ChangePassword {

    @SerializedName("old_password")
    private String old_password;

    @SerializedName("new_password")
    private String new_password;

    @SerializedName("confirm_new_password")
    private String confirm_new_password;

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getConfirm_new_password() {
        return confirm_new_password;
    }

    public void setConfirm_new_password(String confirm_new_password) {
        this.confirm_new_password = confirm_new_password;
    }

    public ChangePassword(String old_password, String new_password, String confirm_new_password) {
        this.old_password = old_password;
        this.new_password = new_password;
        this.confirm_new_password = confirm_new_password;
    }
}
