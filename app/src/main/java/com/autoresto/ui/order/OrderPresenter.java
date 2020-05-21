package com.autoresto.ui.order;

import com.autoresto.model.Order;

import java.util.List;

public class OrderPresenter implements OrderContract.Presenter, OrderContract.Model.OnFinishedListener {

    private OrderContract.Model orderModel;

    private OrderContract.View orderView;


    public OrderPresenter(OrderContract.View orderView) {
        this.orderView = orderView;
        this.orderModel = new OrderRequest();
    }

    @Override
    public void onFinished(List<Order> orderList) {
        if( orderView != null ) {
            orderView.hideProgress();
        }
        orderView.setDataToViews(orderList);
    }

    @Override
    public void onFailure(Throwable t) {
        if( orderView != null ) {
            orderView.hideProgress();
        }
        orderView.onResponseFailure(t);
    }

    @Override
    public void onDestroy() {
        orderView = null;
    }

    @Override
    public void requestDataFromServer(String token) {
        if( orderView != null ) {
            orderView.showProgress();
        }
        orderModel.getOrder(this, token);
    }
}
