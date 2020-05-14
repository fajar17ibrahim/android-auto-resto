package com.autoresto.ui.account;

import android.graphics.ColorSpace;
import android.util.Log;

import com.autoresto.model.User;
import com.autoresto.network.ApiClient;
import com.autoresto.network.ApiInterface;
import com.autoresto.ui.login.LoginActivity;
import com.autoresto.utils.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRequest implements AccountContract.Model {

    @Override
    public void getUser(final OnFinishedListener onFinishedListener, String token) {
        ApiInterface apiService = ApiClient.getClient(ApiUtils.BASE_URL_API).create(ApiInterface.class);

        Call<User> call = apiService.getProfile("Bearer " + token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Log.d("Response Body ", response.toString());
                onFinishedListener.onFinished(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Error Response ", t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }
}
