package com.autoresto.ui.menu.drink;

import android.content.Context;
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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.autoresto.R;
import com.autoresto.holder.TroliHolder;
import com.autoresto.model.Menu;
import com.autoresto.utils.Constans;

import java.util.ArrayList;
import java.util.List;

public class DrinkFragment extends Fragment implements DrinkContract.View, ShowEmptyView {

    private SharedPreferences sharedPreferences;

    private String token;

    private TextView tvNoData;

    private ProgressBar progressBar;

    private DrinkPresenter drinkPresenter;

    private List<Menu> drinkList;

    private ListDrinkAdapter listDrinkAdapter;

    private RecyclerView recyclerView;
    TroliHolder troliHolder;

    public void onAttachToParentFragment(Fragment childfragment) {
        try {

            troliHolder = (TroliHolder) childfragment;
        } catch (ClassCastException e) {
            throw new IllegalStateException("Parent Frag e t must implement EmployeeDataHolder interface");
        }

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_drink, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.rv_drink);

        sharedPreferences = getActivity().getSharedPreferences(Constans.MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        token = sharedPreferences.getString(Constans.TAG_TOKEN, "token");
        Log.d("Token ", token);

        progressBar = (ProgressBar) root.findViewById(R.id.pb_loading);

        tvNoData = (TextView) root.findViewById(R.id.tv_no_data);

        drinkPresenter = new DrinkPresenter(this);
        drinkPresenter.requestDataFromServer(token);

        showRecyclerList();

//        onAttachToParentFragment(getParentFragment());

        return root;
    }

    private void showRecyclerList() {
        drinkList = new ArrayList<>();

        listDrinkAdapter = new ListDrinkAdapter(this, drinkList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(listDrinkAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        drinkPresenter.onDestroy();
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
    public void setDataToViews(List<Menu> menuList) {
        drinkList.addAll(menuList);
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
