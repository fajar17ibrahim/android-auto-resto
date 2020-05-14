package com.autoresto.ui.trolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autoresto.R;
import com.autoresto.model.Trolley;

import java.util.ArrayList;
import java.util.List;

public class TrolleyActivity extends AppCompatActivity {

    private Animation anim_show;
    private Animation anim_hide;

    private RecyclerView recyclerView;

    private ListTrolleyAdapter listTrolleyAdapter;

    private List<Trolley> trolleyList;

    private TextView tvTotalPrice;
    private TextView tvSaldo;

    private RelativeLayout rlSaldo;

    private Button btnOrder;

    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trolley);

        tvTotalPrice = (TextView) findViewById(R.id.tv_total_price);
        tvSaldo = (TextView) findViewById(R.id.tv_saldo);

        rlSaldo = (RelativeLayout) findViewById(R.id.rl_saldo);

        anim_show = AnimationUtils.loadAnimation(this, R.anim.alpha_show);
        anim_hide = AnimationUtils.loadAnimation(this, R.anim.alpha_hide);

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
        mLayoutManager  = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
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

                // Hide and show Saldo
                if (dy > 0 && rlSaldo.getVisibility() == View.VISIBLE) {
                    rlSaldo.startAnimation(anim_hide);
                    rlSaldo.setVisibility(View.GONE);
                } else if (dy < 0 && rlSaldo.getVisibility() != View.VISIBLE) {
                    rlSaldo.startAnimation(anim_show);
                    rlSaldo.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
