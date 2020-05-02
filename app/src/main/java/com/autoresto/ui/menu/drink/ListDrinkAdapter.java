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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class ListDrinkAdapter extends RecyclerView.Adapter<ListDrinkAdapter.ListViewHolder> {

    private DrinkFragment drinkFragment;

    private List<Drink> drinkList;

    public ListDrinkAdapter(DrinkFragment drinkFragment, List<Drink> drinkList) {
        this.drinkFragment = drinkFragment;
        this.drinkList = drinkList;
    }

    public void setDrinkList(List<Drink> drinkList) {
        this.drinkList = new ArrayList<>();
        this.drinkList = drinkList;
        notifyDataSetChanged();
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

        holder.bind(drinkList.get(position));

    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPrice;
        ImageView imgPhoto, imgBgOrder, imgOrder;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_drink);
            tvPrice = itemView.findViewById(R.id.tv_price);
            imgPhoto = itemView.findViewById(R.id.img_item_drink);
            imgPhoto = itemView.findViewById(R.id.img_item_drink);
            imgBgOrder = itemView.findViewById(R.id.bg_select_menu);
            imgOrder = itemView.findViewById(R.id.img_select_menu);
        }

        public void bind(final Drink drink) {
            imgBgOrder.setVisibility(drink.isChecked() ? View.VISIBLE : View.GONE);
            imgOrder.setVisibility(drink.isChecked() ? View.VISIBLE : View.GONE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drink.setChecked(!drink.isChecked());
                    imgBgOrder.setVisibility(drink.isChecked() ? View.VISIBLE : View.GONE);
                    imgOrder.setVisibility(drink.isChecked() ? View.VISIBLE : View.GONE);

                }
            });
        }
    }

    public List<Drink> getAll() {
        return drinkList;
    }

    public List<Drink> getSelected() {
        List<Drink> selected = new ArrayList<>();
        for (int i = 0; i < drinkList.size(); i++) {
            if (drinkList.get(i).isChecked()) {
                selected.add(drinkList.get(i));
            }
        }
        return selected;
    }
}
