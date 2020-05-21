package com.autoresto.ui.menu.drink;

import com.autoresto.model.Menu;

import java.util.List;

public interface DrinkContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(List<Menu> menuList);

            void onFailure(Throwable t);
        }

        void getDrink(OnFinishedListener onFinishedListener, String token);
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
