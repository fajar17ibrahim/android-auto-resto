package com.autoresto.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderSend {

    @SerializedName("table_id")
    private int table_id;

    @SerializedName("order_details")
    private List<OrderDetail> order_details;

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public List<OrderDetail> getOrder_details() {
        return order_details;
    }

    public void setOrder_details(List<OrderDetail> order_details) {
        this.order_details = order_details;
    }

    public OrderSend(int table_id, List<OrderDetail> order_details) {
        this.table_id = table_id;
        this.order_details = order_details;
    }
}
