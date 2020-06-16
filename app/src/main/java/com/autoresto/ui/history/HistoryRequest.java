package com.autoresto.ui.history;

import android.util.Log;

import com.autoresto.model.Order;
import com.autoresto.network.ApiClient;
import com.autoresto.network.ApiInterface;
import com.autoresto.utils.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryRequest implements HistoryContract.Model {
    @Override
    public void getHistory(final OnFinishedListener onFinishedListener, String token) {
        ApiInterface apiService = ApiClient.getClient(ApiUtils.BASE_URL_API).create(ApiInterface.class);

        Call<List<Order>> call = apiService.getHistories("bearer " + token);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                List<Order> order = response.body();
                Log.d("Response Body ", order.toString());
                onFinishedListener.onFinished(order);
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d("Error Response ", t.toString());
                onFinishedListener.onFailure(t);
            }
        });

    }

}
