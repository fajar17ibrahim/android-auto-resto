package com.autoresto.ui.history;

import com.autoresto.model.Order;

import java.util.List;

public interface HistoryContract {

    interface Model {

        interface OnFinishedListener {

            void onFinished(List<Order> orderList);

            void onFailure(Throwable t);
        }

        void getHistory(OnFinishedListener onFinishedListener, String token);
    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToViews(List<Order> orderList);

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {

        void onDestroy();

        void requestDataFromServer(String token);
    }
}
