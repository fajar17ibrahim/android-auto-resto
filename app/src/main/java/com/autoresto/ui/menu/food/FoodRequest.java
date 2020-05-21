package com.autoresto.ui.menu.food;

import android.util.Log;

import com.autoresto.model.Menu;
import com.autoresto.network.ApiClient;
import com.autoresto.network.ApiInterface;
import com.autoresto.utils.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodRequest implements FoodContract.Model {
    @Override
    public void getFood(final OnFinishedListener onFinishedListener, String token) {

        ApiInterface apiService = ApiClient.getClient(ApiUtils.BASE_URL_API).create(ApiInterface.class);

        Call<List<Menu>> call = apiService.getFoods("bearer " + token);
        call.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                List<Menu> menu = response.body();
                Log.d("Response Body ", response.toString());
                onFinishedListener.onFinished(menu);
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Log.d("Error Response ", t.toString());
                onFinishedListener.onFailure(t);
            }
        });

    }
}
