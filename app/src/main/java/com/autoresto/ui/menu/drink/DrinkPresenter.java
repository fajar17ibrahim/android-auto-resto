package com.autoresto.ui.menu.drink;

import com.autoresto.model.Menu;

import java.util.List;

public class DrinkPresenter implements DrinkContract.Presenter, DrinkContract.Model.OnFinishedListener {

    private DrinkContract.Model drinkModel;

    private DrinkContract.View drinkView;

    public DrinkPresenter(DrinkContract.View drinkView) {
        this.drinkView = drinkView;
        this.drinkModel = new DrinkRequest();
    }

    @Override
    public void onFinished(List<Menu> menuList) {
        if( drinkView != null ) {
            drinkView.hideProgress();
        }
        drinkView.setDataToViews(menuList);
    }

    @Override
    public void onFailure(Throwable t) {
        if( drinkView != null ) {
            drinkView.hideProgress();
        }
        drinkView.onResponseFailure(t);
    }

    @Override
    public void onDestroy() {
        drinkView = null;
    }

    @Override
    public void requestDataFromServer(String token) {
        if( drinkView != null ) {
            drinkView.showProgress();
        }
        drinkModel.getDrink(this, token);
    }
}
