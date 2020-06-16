package com.autoresto.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderSend {

    @SerializedName("table_id")
    private int table_id;

    @SerializedName("order_details")
    private List<OrderSendDetail> order_details;

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public List<OrderSendDetail> getOrder_details() {
        return order_details;
    }

    public void setOrder_details(List<OrderSendDetail> order_details) {
        this.order_details = order_details;
    }

    public OrderSend(int table_id, List<OrderSendDetail> order_details) {
        this.table_id = table_id;
        this.order_details = order_details;
    }
}
