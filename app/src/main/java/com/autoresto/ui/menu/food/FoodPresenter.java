package com.autoresto.ui.menu.food;

import com.autoresto.model.Menu;

import java.util.List;

public class FoodPresenter implements FoodContract.Presenter, FoodContract.Model.OnFinishedListener {

    private FoodContract.Model foodModel;

    private FoodContract.View foodView;

    public FoodPresenter(FoodContract.View foodView) {
        this.foodView = foodView;
        this.foodModel = new FoodRequest();
    }


    @Override
    public void onFinished(List<Menu> menuList) {
        if( foodView != null ) {
            foodView.hideProgress();
        }
        foodView.setDataToViews(menuList);
    }

    @Override
    public void onFailure(Throwable t) {
        if( foodView != null ) {
            foodView.hideProgress();
        }
        foodView.onResponseFailure(t);
    }

    @Override
    public void onDestroy() {
        foodView = null;
    }

    @Override
    public void requestDataFromServer(String token) {
        if( foodView != null ) {
            foodView.showProgress();
        }
        foodModel.getFood(this, token);

    }
}
