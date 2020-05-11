package com.autoresto.ui.orderdetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.autoresto.R;
import com.autoresto.model.Menu;
import com.autoresto.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ListOrderDetailAdapter listOrderDetailAdapter;

    private List<OrderDetail> orderDetailList;

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

        recyclerView = (RecyclerView) findViewById(R.id.rv_order);

        showRecyclerList();
    }

    private void showRecyclerList() {
        orderDetailList = new ArrayList<>();

        orderDetailList.addAll(OrderDetailData.getListData());

        listOrderDetailAdapter = new ListOrderDetailAdapter(this, orderDetailList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listOrderDetailAdapter);


    }
}
