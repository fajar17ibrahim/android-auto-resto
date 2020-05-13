package com.autoresto.ui.trolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.autoresto.R;
import com.autoresto.model.Trolley;

import java.util.ArrayList;
import java.util.List;

public class TrolleyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ListTrolleyAdapter listTrolleyAdapter;

    private List<Trolley> trolleyList;

    private TextView tvTotalPrice;
    private TextView tvSaldo;

    private Button btnOrder;

    private int pageNo = 1;

    //Constants for load more
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trolley);

        tvTotalPrice = (TextView) findViewById(R.id.tv_total_price);
        tvSaldo = (TextView) findViewById(R.id.tv_saldo);

        btnOrder = (Button) findViewById(R.id.btn_order);

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

        setListeners();

    }

    private void showRecyclerList() {
        trolleyList = new ArrayList<>();

        trolleyList.addAll(TrolleyData.getListData());

        listTrolleyAdapter = new ListTrolleyAdapter(this, trolleyList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listTrolleyAdapter);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listTrolleyAdapter.getSelected().size() > 0) {
                    int qty1 = 0, price1 = 0;
                    for (int i = 0; i < listTrolleyAdapter.getSelected().size(); i++) {
                        int qty = listTrolleyAdapter.getSelected().get(i).getQty();
                        int price = Integer.parseInt(listTrolleyAdapter.getSelected().get(i).getTotal());
                        qty1 = qty + qty1;
                        price1 = price1 + price;
                    }
                    tvTotalPrice.setText(String.valueOf(qty1)+" Item | "+String.valueOf(price1)+" ( Total belanja )");
                } else {
                    tvTotalPrice.setText(" no item");
                }
            }
        });
    }

    private void setListeners() {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

                // Handling the infinite scroll
                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
//                if (!loading && (totalItemCount - visibleItemCount)
//                        <= (firstVisibleItem + visibleThreshold)) {
//                    movieListPresenter.getMoreData(pageNo);
//                    loading = true;
//                }

                // Hide and show Filter button
                if (dy > 0 && tvSaldo.getVisibility() == View.VISIBLE) {
                    tvSaldo.setVisibility(View.GONE);
                } else if (dy < 0 && tvSaldo.getVisibility() != View.VISIBLE) {
                    tvSaldo.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
