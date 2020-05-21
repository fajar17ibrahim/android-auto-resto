package com.autoresto.ui.menu.food;

import com.autoresto.model.Menu;

import java.util.List;

public interface FoodContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(List<Menu> menuList);

            void onFailure(Throwable t);
        }

        void getFood(OnFinishedListener onFinishedListener, String token);
    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToViews(List<Menu> menuList);

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {

        void onDestroy();

        void requestDataFromServer(String token);
    }
}
