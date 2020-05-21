package com.autoresto.ui.order;

import com.autoresto.model.Order;

import java.util.List;

public interface OrderContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(List<Order> orderList);

            void onFailure(Throwable t);
        }

        void getOrder(OnFinishedListener onFinishedListener, String token);
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
