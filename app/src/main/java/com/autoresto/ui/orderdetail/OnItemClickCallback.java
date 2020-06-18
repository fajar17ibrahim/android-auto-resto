package com.autoresto.ui.orderdetail;

import com.autoresto.model.Order;
import com.autoresto.model.OrderDetail;

import java.util.List;

public interface OnItemClickCallback {

    void onItemClick(OrderDetail orderDetail);
}
