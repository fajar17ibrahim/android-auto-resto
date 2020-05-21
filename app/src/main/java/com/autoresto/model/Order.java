package com.autoresto.model;

import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("id")
    private int id;

    @SerializedName("qr_code")
    private String qr_code;

    @SerializedName("total")
    private float total;

    @SerializedName("date")
    private String date;

    @SerializedName("payed")
    private boolean payed;

    @SerializedName("accepted")
    private String accepted;

    @SerializedName("rejected")
    private String rejected;

    @SerializedName("finished")
    private String finished;

    @SerializedName("note")
    private String note;

    @SerializedName("created_at")
    private String created_at;

    private User user;

    public Order() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public String getAccepted() {
        return accepted;
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    public String getRejected() {
        return rejected;
    }

    public void setRejected(String rejected) {
        this.rejected = rejected;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order(int id, String qr_code, float total, String date, boolean payed, String accepted, String rejected, String finished, String note, String created_at, User user) {
        this.id = id;
        this.qr_code = qr_code;
        this.total = total;
        this.date = date;
        this.payed = payed;
        this.accepted = accepted;
        this.rejected = rejected;
        this.finished = finished;
        this.note = note;
        this.created_at = created_at;
        this.user = user;
    }
}
