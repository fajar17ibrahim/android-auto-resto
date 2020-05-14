package com.autoresto.ui.account;

import com.autoresto.model.User;

public class AccountPresenter implements AccountContract.Presenter, AccountContract.Model.OnFinishedListener {

    private AccountContract.Model userModel;

    private AccountContract.View userView;

    public AccountPresenter(AccountContract.View userView) {
        this.userView = userView;
        this.userModel = new AccountRequest();
    }

    @Override
    public void onDestroy() {
        userView = null;
    }

    @Override
    public void requestDataFromServer(String token) {
        if( userView != null ) {
            userView.showProgress();
        }
        userModel.getUser(this, token);
    }

    @Override
    public void onFinished(User user) {
        if( userView != null ) {
            userView.hideProgress();
        }
        userView.setDataToViews(user);
    }

    @Override
    public void onFailure(Throwable t) {
        if( userView != null ) {
            userView.hideProgress();
        }
        userView.onResponseFailure(t);

    }
}
