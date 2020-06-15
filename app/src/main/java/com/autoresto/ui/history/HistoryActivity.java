package com.autoresto.ui.history;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.autoresto.R;
import com.autoresto.model.Order;
import com.autoresto.ui.order.ShowEmptyView;
import com.autoresto.utils.Constans;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity implements HistoryContract.View, ShowEmptyView {

    private SharedPreferences sharedPreferences;

    private String token;

    private TextView tvNoData;

    private ProgressBar progressBar;

    private HistoryPresenter historyPresenter;

    private Toolbar toolbar;

    private RecyclerView recyclerView;

    private ListHistoryAdapter listHistoryAdapter;

    private List<Order> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Riwayat Pesanan");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rv_history);

        sharedPreferences = getSharedPreferences(Constans.MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(Constans.TAG_TOKEN, "token");
        Log.d("Token ", token);

        progressBar = (ProgressBar) findViewById(R.id.pb_loading);

        tvNoData = (TextView) findViewById(R.id.tv_no_data);

        historyPresenter = new HistoryPresenter(this);
        historyPresenter.requestDataFromServer(token);

        showRecyclerView();
    }

    private void showRecyclerView() {

        list = new ArrayList<>();

        listHistoryAdapter = new ListHistoryAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listHistoryAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        historyPresenter.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDataToViews(List<Order> orderList) {
        list.addAll(orderList);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e("Error Response ", throwable.getMessage());
        Toast.makeText(this, "Data gagal dimuat.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmptyView() {
        recyclerView.setVisibility(View.GONE);
        tvNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        recyclerView.setVisibility(View.VISIBLE);
        tvNoData.setVisibility(View.GONE);
    }
}
