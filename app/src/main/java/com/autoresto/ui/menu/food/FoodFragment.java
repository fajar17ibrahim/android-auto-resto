package com.autoresto.ui.menu.food;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
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

        if (listFoodAdapter.getSelected() != null) {
//                Intent iRincian = new Intent(getActivity(), RincianPesananActivity.class);
//                Bundle data = new Bundle();
//                data.putString("slot_id", listSlotAdapter.getSelected().getId());
//                iRincian.putExtras(data);
//                startActivity(iRincian);

        } else {
//            showToast("Anda belum memilih slot");
        }
    }

    private void createList() {
        for (int i = 0; i < 20; i++) {
            Food food = new Food();
            food.setName("Employee " + (i + 1));

            // for example to show at least one selection
            if (i == 0) {
                food.setChecked(true);
            }
            //

            foodList.add(food);
        }
        listFoodAdapter.setFoodList(foodList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
