package com.autoresto.ui.history;

import com.autoresto.model.Order;

import java.util.List;

public class HistoryPresenter implements HistoryContract.Presenter, HistoryContract.Model.OnFinishedListener{

    private HistoryContract.Model historyModel;

    private HistoryContract.View historyView;

    public HistoryPresenter(HistoryContract.View historyView) {
        this.historyView = historyView;
        historyModel = new HistoryRequest();
    }

    @Override
    public void onFinished(List<Order> orderList) {
        historyView.setDataToViews(orderList);
        if( historyView != null ) {
            historyView.hideProgress();
        }

    }

    @Override
    public void onFailure(Throwable t) {
        if( historyView != null ) {
            historyView.hideProgress();
        }
        historyView.onResponseFailure(t);
    }

    @Override
    public void onDestroy() {
        historyView = null;
    }

    @Override
    public void requestDataFromServer(String token) {
        if( historyView != null ) {
            historyView.showProgress();
        }
        historyModel.getHistory(this, token);
    }
}
