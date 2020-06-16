package com.autoresto.ui.orderdetail;

import com.autoresto.model.OrderDetail;

import java.util.List;

public interface OrderDetailsContract {

    interface Model {

        interface OnFinishhedListener {

            void onFinished(List<OrderDetail> orderDetailList);

            void onFailure(Throwable throwable);

        }

        void getOrderDetail(OnFinishhedListener onFinishedListener, String token, int order_id);

    }

    interface View {

        void showProgress();

        void hideProgress();

        void showDataToViews(List<OrderDetail> orderDetailList);

        void onResponseFailure(Throwable throwable);

    }

    interface Presenter {

        void onDestroy();

        void requestDataFromServer(String token, int order_id);
    }

}
