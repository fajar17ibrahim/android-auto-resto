package com.autoresto.ui.selecttable;

import com.autoresto.model.Table;

import java.util.List;

public class SelectTablePresenter implements SelectTableContract.Presenter, SelectTableContract.Model.OnFinishedListener {

    private SelectTableContract.Model model;

    private SelectTableContract.View view;

    public SelectTablePresenter(SelectTableContract.View view) {
        this.view = view;
        this.model = new SelectTableRequest();
    }

    @Override
    public void onFinished(List<Table> tableList) {
        view.setDataToViews(tableList);
        if ( view != null ) {
            view.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        view.onResponseFailure(throwable);
        if ( view != null ) {
            view.hideProgress();
        }
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void requestDataFromServer() {
        if ( view != null ) {
            view.showProgress();
        }
        model.getTableData(this);
    }
}
