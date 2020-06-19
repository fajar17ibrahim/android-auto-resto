package com.autoresto.ui.selecttable;

import android.util.Log;

import com.autoresto.model.Table;
import com.autoresto.network.ApiClient;
import com.autoresto.network.ApiInterface;
import com.autoresto.utils.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectTableRequest implements SelectTableContract.Model{
    @Override
    public void getTableData(final OnFinishedListener onFinishedListener) {

        ApiInterface apiInterface = ApiClient.getClient(ApiUtils.BASE_URL_API).create(ApiInterface.class);

        Call<List<Table>> call = apiInterface.getTable();
        call.enqueue(new Callback<List<Table>>() {
            @Override
            public void onResponse(Call<List<Table>> call, Response<List<Table>> response) {
                List<Table> tables = response.body();
                Log.d("Response Body ", tables.toString());
                onFinishedListener.onFinished(tables);
            }

            @Override
            public void onFailure(Call<List<Table>> call, Throwable t) {
                Log.d("Response Error ", t.toString());
                onFinishedListener.onFailure(t);
            }
        });

    }

}
