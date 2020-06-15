package com.autoresto.ui.order;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.autoresto.R;
import com.autoresto.model.Order;
import com.autoresto.ui.orderdetail.OrderDetailActivity;
import com.autoresto.utils.Constans;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment implements OrderContract.View, ShowEmptyView {

    private SharedPreferences sharedPreferences;

    private String token;

    private TextView tvNoData;

    private ProgressBar progressBar;

    private OrderPresenter orderPresenter;

    private RecyclerView recyclerView;

    private ListOrderAdapter listOrderAdapter;

    private List<Order> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_order, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.rv_order);

        sharedPreferences = getActivity().getSharedPreferences(Constans.MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(Constans.TAG_TOKEN, "token");
        Log.d("Token ", token);

        progressBar = (ProgressBar) root.findViewById(R.id.pb_loading);

        tvNoData = (TextView) root.findViewById(R.id.tv_no_data);

        orderPresenter = new OrderPresenter(this);
        orderPresenter.requestDataFromServer(token);

        showRecyclerList();

        return root;
    }

    private void showRecyclerList() {
        list = new ArrayList<>();

        listOrderAdapter = new ListOrderAdapter(list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listOrderAdapter);

        listOrderAdapter.setOnItemClickCallback(new ListOrderAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Order order) {
                Intent iOrderDetail = new Intent(getActivity(), OrderDetailActivity.class);
                iOrderDetail.putExtra(Constans.TAG_ORDER_ID, order.getId());
                startActivity(iOrderDetail);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        orderPresenter.onDestroy();
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
        Toast.makeText(getActivity(), "Data gagal dimuat.", Toast.LENGTH_LONG).show();
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