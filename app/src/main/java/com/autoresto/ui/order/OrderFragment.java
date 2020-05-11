package com.autoresto.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.autoresto.MainActivity;
import com.autoresto.R;
import com.autoresto.model.Order;
import com.autoresto.ui.orderdetail.OrderDetailActivity;
import com.autoresto.utils.Constans;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {

    private Toolbar toolbar;

    private ActionBar actionBar;

    private RecyclerView recyclerView;

    private ListOrderAdapter listOrderAdapter;

    private List<Order> orderList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_order, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.rv_order);

        showRecyclerList();

        return root;
    }

    private void showRecyclerList() {
        orderList = new ArrayList<>();

        orderList.addAll(OrderData.getListData());

        listOrderAdapter = new ListOrderAdapter(this, orderList);
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
}