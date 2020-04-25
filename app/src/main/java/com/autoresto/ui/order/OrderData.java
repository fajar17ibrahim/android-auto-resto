package com.autoresto.ui.order;

import com.autoresto.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderData {
    public static String[][] data = new String[][] {
            {"3124124", "Sedang dipesan", "25 April 2020, 21:26"},
            {"3124125", "Sedang dipesan", "25 April 2020, 22:10"}
    };

    public static List<Order> getListData() {
        List<Order> list = new ArrayList<>();
        for(String[] aData : data) {
            Order order = new Order();
            order.setId(aData[0]);
            order.setStatus(aData[1]);
            order.setCreated_at(aData[2]);

            list.add(order);
        }
        return list;
    }
}
