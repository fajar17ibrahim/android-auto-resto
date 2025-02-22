package com.autoresto.ui.menu.drink;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.autoresto.R;
import com.autoresto.model.Menu;
import com.autoresto.ui.trolley.session.TroliData;
import com.autoresto.ui.trolley.session.TroliSession;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class ListDrinkAdapter extends RecyclerView.Adapter<ListDrinkAdapter.ListViewHolder> {

    private DrinkFragment drinkFragment;

    private List<Menu> drinkList;

    public ListDrinkAdapter(DrinkFragment drinkFragment, List<Menu> drinkList) {
        this.drinkFragment = drinkFragment;
        this.drinkList = drinkList;
    }

    public void setDrinkList(List<Menu> drinkList) {
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

        Menu drink = drinkList.get(position);

        if (drink.getPhoto() == null) {
            holder.imgPhoto.setImageResource(R.drawable.ic_menu);
        } else {
            Glide.with(holder.itemView.getContext())
                    .load(drink.getPhoto())
                    .apply(new RequestOptions().override(150, 205))
                    .into(holder.imgPhoto);
        }

        holder.tvName.setText(drink.getName());
        holder.tvDescription.setText(drink.getDescription());
        holder.tvPrice.setText("Rp. " + String.valueOf(drink.getPrice()) + ",-");

        holder.bind(drinkList.get(position));

    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPrice, tvDescription, tvStok;
        ImageView imgPhoto, imgBgOrder, imgOrder;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_drink);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvStok = itemView.findViewById(R.id.tv_stok);
            tvDescription = itemView.findViewById(R.id.tv_description);
            imgPhoto = itemView.findViewById(R.id.img_item_drink);
            imgBgOrder = itemView.findViewById(R.id.bg_select_menu);
            imgOrder = itemView.findViewById(R.id.img_select_menu);
        }

        public void bind(final Menu drink) {
            imgOrder.setVisibility(View.GONE);
            imgBgOrder.setVisibility(View.GONE);
            tvStok.setVisibility(View.GONE);

            if (drink.getStock() <= 0) {
                imgBgOrder.setVisibility(View.VISIBLE);
                tvStok.setVisibility(View.VISIBLE);
                imgOrder.setVisibility(View.GONE);
            } else {
                tvStok.setVisibility(View.GONE);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        drink.setChecked(!drink.isChecked());
                        imgBgOrder.setVisibility(drink.isChecked() ? View.VISIBLE : View.GONE);
                        imgOrder.setVisibility(drink.isChecked() ? View.VISIBLE : View.GONE);

                        TroliData data = new TroliData();
                        data.setMenu(drink);
                        data.setNote("");
                        data.setQty(1);
                        data.setSub_total(drink.getPrice());

                        TroliSession troliSession = TroliSession.getInstance();
                        if (drink.isChecked()) {
                            troliSession.addtroliData(data);
                        } else {
                            troliSession.removetroliData(drink.getId());
                        }
                    }
                });
            }
        }
    }

}
