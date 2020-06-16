package com.autoresto.ui.orderdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;

import com.autoresto.R;
import com.autoresto.model.Menu;
import com.autoresto.model.OrderDetail;
import com.autoresto.utils.Constans;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity implements OrderDetailsContract.View {

    private OrderDetailsPresenter orderDetailsPresenter;

    private SharedPreferences sharedPreferences;

    private String token;

    private int orderId;

    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    private ListOrderDetailAdapter listOrderDetailAdapter;

    private List<OrderDetail> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sharedPreferences = getSharedPreferences(Constans.MY_SHARED_PREFERENCES, MODE_PRIVATE);
        token = sharedPreferences.getString(Constans.TAG_TOKEN, "token");

        Bundle bundle = getIntent().getExtras();
        orderId = bundle.getInt(Constans.TAG_ORDER_ID);
        Log.d("Order ID ", String.valueOf(orderId));

        recyclerView = (RecyclerView) findViewById(R.id.rv_order);

        progressBar = (ProgressBar) findViewById(R.id.pb_loading);

        showRecyclerList();

        orderDetailsPresenter = new OrderDetailsPresenter(this);
        orderDetailsPresenter.requestDataFromServer(token, orderId);
    }

    private void showRecyclerList() {
        list = new ArrayList<>();

        listOrderDetailAdapter = new ListOrderDetailAdapter(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listOrderDetailAdapter);

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
    public void showDataToViews(List<OrderDetail> orderDetailList) {
        list.addAll(orderDetailList);
        listOrderDetailAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.d("Error Response ", throwable.toString());
    }
}
