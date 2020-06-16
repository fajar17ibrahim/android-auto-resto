package com.autoresto.ui.orderdetail;

import com.autoresto.model.OrderDetail;

import java.util.List;

public class OrderDetailsPresenter implements OrderDetailsContract.Presenter, OrderDetailsContract.Model.OnFinishhedListener {

    private OrderDetailsContract.Model model;

    private OrderDetailsContract.View view;

    public OrderDetailsPresenter(OrderDetailsContract.View view) {
        this.view = view;
        this.model = new OrderDetailsRequest();
    }

    @Override
    public void onFinished(List<OrderDetail> orderDetailList) {
        view.showDataToViews(orderDetailList);
        if ( view != null ) {
            view.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        if ( view != null ) {
            view.hideProgress();
        }
        view.onResponseFailure(throwable);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void requestDataFromServer(String token, int order_id) {
        if ( view != null ) {
            view.showProgress();
        }
        model.getOrderDetail(this, token, order_id);
    }
}
