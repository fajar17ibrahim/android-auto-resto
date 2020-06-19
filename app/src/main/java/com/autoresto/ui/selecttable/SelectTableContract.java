package com.autoresto.ui.selecttable;

import com.autoresto.model.Table;

import java.util.List;

public interface SelectTableContract {

    interface Model {

        interface OnFinishedListener {

            void onFinished(List<Table> tableList);

            void onFailure(Throwable throwable);
        }

        void getTableData(OnFinishedListener onFinishedListener);
    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToViews(List<Table> tableList);

        void onResponseFailure(Throwable throwable);

    }

    interface Presenter {

        void onDestroy();

        void requestDataFromServer();

    }
}
