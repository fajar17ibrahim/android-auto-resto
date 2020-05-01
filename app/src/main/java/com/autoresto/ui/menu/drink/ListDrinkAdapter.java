package com.autoresto.ui.menu.drink;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.autoresto.R;
import com.autoresto.model.Drink;
import com.autoresto.model.Food;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

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

        Drink drink = drinkList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(drink.getPhoto())
                .apply(new RequestOptions().override(150, 205))
                .into(holder.imgPhoto);

        holder.tvName.setText(drink.getName());
        holder.tvPrice.setText(drink.getPrice());

    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPrice;
        ImageView imgPhoto;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_drink);
            tvPrice = itemView.findViewById(R.id.tv_price);
            imgPhoto = itemView.findViewById(R.id.img_item_drink);
        }
    }
}
