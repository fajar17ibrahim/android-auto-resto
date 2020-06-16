package com.autoresto.ui.orderdetail;

import android.util.Log;

import com.autoresto.model.OrderDetail;
import com.autoresto.network.ApiClient;
import com.autoresto.network.ApiInterface;
import com.autoresto.utils.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsRequest implements OrderDetailsContract.Model {

    @Override
    public void getOrderDetail(final OnFinishhedListener onFinishedListener, String token, int order_id) {

        ApiInterface apiInterface = ApiClient.getClient(ApiUtils.BASE_URL_API).create(ApiInterface.class);

        Call<List<OrderDetail>> call = apiInterface.getOrderDetails("Bearer " + token, order_id);
        call.enqueue(new Callback<List<OrderDetail>>() {
            @Override
            public void onResponse(Call<List<OrderDetail>> call, Response<List<OrderDetail>> response) {
                List<OrderDetail> orderDetailList = response.body();
                onFinishedListener.onFinished(orderDetailList);
            }

            @Override
            public void onFailure(Call<List<OrderDetail>> call, Throwable t) {
                Log.d("Error Response ", t.toString());
                onFinishedListener.onFailure(t);
            }
        });
    }
}
