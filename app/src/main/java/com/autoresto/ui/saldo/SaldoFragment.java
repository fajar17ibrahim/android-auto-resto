package com.autoresto.ui.saldo;

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

import com.autoresto.R;
import com.autoresto.model.Order;
import com.autoresto.ui.order.ListOrderAdapter;
import com.autoresto.ui.order.OrderData;

import java.util.ArrayList;
import java.util.List;

public class SaldoFragment extends Fragment {

    private Toolbar toolbar;

    private ActionBar actionBar;

    private RecyclerView recyclerView;

    private ListOrderAdapter listOrderAdapter;

    private List<Order> orderList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_saldo, container, false);

        showRecyclerList();

        return root;
    }

    private void showRecyclerList() {


    }
}