package com.autoresto.ui.history;

import com.autoresto.model.Order;

import java.util.ArrayList;
import java.util.List;

public class HistoryData {
    public static String[][] data = new String[][] {
            {"3124124", "Order selesai", "25 April 2020, 21:26"},
            {"3124125", "Order selesai", "25 April 2020, 22:10"},
            {"3124126", "Order selesai", "26 April 2020, 22:10"},
            {"3124127", "Order selesai", "26 April 2020, 22:10"},
            {"3124128", "Order selesai", "26 April 2020, 22:10"}
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
