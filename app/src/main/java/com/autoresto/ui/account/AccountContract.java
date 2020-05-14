package com.autoresto.ui.account;

import com.autoresto.model.User;

public interface AccountContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(User user);

            void onFailure(Throwable t);
        }

        void getUser(OnFinishedListener onFinishedListener, String token);
    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToViews(User user);

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {

        void onDestroy();

        void requestDataFromServer(String token);
    }
}
