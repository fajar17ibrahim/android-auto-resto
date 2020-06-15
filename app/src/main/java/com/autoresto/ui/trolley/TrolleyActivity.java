package com.autoresto.ui.trolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.autoresto.R;
import com.autoresto.model.OrderDetail;
import com.autoresto.model.OrderSend;
import com.autoresto.model.Trolley;
import com.autoresto.model.User;
import com.autoresto.network.ApiClient;
import com.autoresto.network.ApiInterface;
import com.autoresto.session.TroliSession;
import com.autoresto.ui.account.AccountContract;
import com.autoresto.ui.account.AccountPresenter;
import com.autoresto.utils.ApiUtils;
import com.autoresto.utils.Constans;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrolleyActivity extends AppCompatActivity implements AccountContract.View {

    private SharedPreferences sharedPreferences;

    private String token;

    private AccountPresenter accountPresenter;

    private TextView tvNoData;

    private ProgressBar progressBar;

    private Animation anim_show;
    private Animation anim_hide;

    private RecyclerView recyclerView;

    private ListTrolleyAdapter listTrolleyAdapter;

    private List<Trolley> trolleyList;

    private TextView tvTotalPrice;

    private TextView tvSaldo;

    private RelativeLayout rlSaldo;

    private Button btnOrder;

    private ProgressDialog loading;

    private Context mContext;

    private LinearLayoutManager mLayoutManager;

    private ProgressBar pbLoading;

    //private List<Trolley> trolleys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trolley);
        mContext = this;


        sharedPreferences = getSharedPreferences(Constans.MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(Constans.TAG_TOKEN, "token");
        Log.d("Token ", token);

        pbLoading = (ProgressBar) findViewById(R.id.pb_loading);

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

        accountPresenter = new AccountPresenter(this);
        accountPresenter.requestDataFromServer(token);

        showRecyclerList();

        setListeners();

    }

    private void showRecyclerList() {
        trolleyList = new ArrayList<>();

        String json = sharedPreferences.getString("Orderan", "");
        Log.d("orderan", json);

        TroliSession troliSession = TroliSession.getInstance();
        troliSession.getTroliDataList();

//        trolleyList.add(troliSession.getTroliDataList());

        listTrolleyAdapter = new ListTrolleyAdapter(this, troliSession.getTroliDataList());
        mLayoutManager  = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(listTrolleyAdapter);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listTrolleyAdapter.getSelected().size() > 0) {
                    int qty1 = 0;
                    float price1 = 0;

                    List<OrderDetail> orderDetails = new ArrayList<>();

                    for (int i = 0; i < listTrolleyAdapter.getSelected().size(); i++) {

                        int id = listTrolleyAdapter.getSelected().get(i).getMenu().getId();
                        int qty = listTrolleyAdapter.getSelected().get(i).getQty();
                        String note = listTrolleyAdapter.getSelected().get(i).getNote();
                        float price = listTrolleyAdapter.getSelected().get(i).getSub_total();
                        qty1 = qty + qty1;
                        price1 = price1 + price;

                        OrderDetail orderDetail = new OrderDetail(id, note, qty1);
                        orderDetail.setId(id);
                        orderDetail.setNote(note);
                        orderDetail.setQty(qty1);
                        orderDetails.add(orderDetail);
                    }

                    OrderSend orderSend = new OrderSend(1, orderDetails);

                    loading = ProgressDialog.show(mContext, null, "Proses...", true, false);
                    sendOrder(token, orderSend);

                } else {
                    tvTotalPrice.setText(" no item");
                }
            }
        });
    }

    public void sendOrder(String token, OrderSend orderSend) {
        ApiInterface apiService = ApiClient.getClient(ApiUtils.BASE_URL_API).create(ApiInterface.class);

        Call<OrderSend> call = apiService.sendOrder("bearer " + token, orderSend);
        call.enqueue(new Callback<OrderSend>() {
            @Override
            public void onResponse(Call<OrderSend> call, Response<OrderSend> response) {
                loading.dismiss();
                Toast.makeText(mContext, "Order Sukses", Toast.LENGTH_LONG).show();
                OrderSend orderSendData = response.body();
                Log.d("Response Body ", orderSendData.toString());
            }

            @Override
            public void onFailure(Call<OrderSend> call, Throwable t) {
                loading.dismiss();
                Log.d("Error Response ", t.toString());
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

    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void setDataToViews(User user) {
        tvSaldo.setText("Rp. " + user.getBalance() + ",-");
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.d("Error Response ", throwable.toString());
    }
}
