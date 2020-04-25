package com.autoresto.ui.menu.food;

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
import com.autoresto.model.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends Fragment {

    private List<Food> foodList;

    private ListFoodAdapter listFoodAdapter;

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_food, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.rv_food);

        showRecyclerList();

        return root;
    }

    private void showRecyclerList() {
        foodList = new ArrayList<>();
        foodList.addAll(FoodData.getListData());

        listFoodAdapter = new ListFoodAdapter(this, foodList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(listFoodAdapter);
    }
}
