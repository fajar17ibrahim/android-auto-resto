package com.autoresto.ui.menu.drink;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.autoresto.R;
import com.autoresto.model.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkFragment extends Fragment {

    private List<Drink> drinkList;

    private ListDrinkAdapter listDrinkAdapter;

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_drink, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.rv_drink);

        shoeRecyclerList();

        return root;
    }

    private void shoeRecyclerList() {
        drinkList = new ArrayList<>();
        drinkList.addAll(DrinkData.getListData());

        listDrinkAdapter = new ListDrinkAdapter(this, drinkList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(listDrinkAdapter);
    }
}
