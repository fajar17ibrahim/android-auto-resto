package com.autoresto.ui.menu.drink;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.autoresto.R;
import com.autoresto.model.Drink;
import com.autoresto.model.Food;

import java.util.List;

public class ListDrinkAdapter extends RecyclerView.Adapter<ListDrinkAdapter.ListViewHolder> {

    private DrinkFragment drinkFragment;

    private List<Drink> drinkList;

    public ListDrinkAdapter(DrinkFragment drinkFragment, List<Drink> drinkList) {
        this.drinkFragment = drinkFragment;
        this.drinkList = drinkList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_drink, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
