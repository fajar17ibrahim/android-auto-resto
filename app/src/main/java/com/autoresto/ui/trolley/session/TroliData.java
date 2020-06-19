package com.autoresto.ui.trolley.session;

import com.autoresto.model.Menu;

public class TroliData {
    private Menu menu;
    private Integer qty;
    private Float sub_total;
    private String note;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getQty() {
        return qty;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
