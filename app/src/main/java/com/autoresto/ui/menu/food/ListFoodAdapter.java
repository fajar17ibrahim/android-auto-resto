package com.autoresto.ui.menu.food;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.autoresto.R;
import com.autoresto.model.Menu;
import com.autoresto.session.TroliData;
import com.autoresto.session.TroliSession;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class ListFoodAdapter extends RecyclerView.Adapter<ListFoodAdapter.ListViewHolder> {

    private FoodFragment foodFragment;

    private List<Menu> foodList;


    public ListFoodAdapter(FoodFragment foodFragment, List<Menu> foodList) {
        this.foodFragment = foodFragment;
        this.foodList = foodList;
    }

    public void setFoodList(List<Menu> foodList) {
        this.foodList = new ArrayList<>();
        this.foodList = foodList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_food, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Menu food = foodList.get(position);

        if(food.getPhoto() == null ) {
            holder.imgPhoto.setImageResource(R.drawable.ic_menu);
        } else {
            Glide.with(holder.itemView.getContext())
                    .load(food.getPhoto())
                    .apply(new RequestOptions().override(150, 205))
                    .into(holder.imgPhoto);
        }

        holder.tvName.setText(food.getName());
        holder.tvDescription.setText(food.getDescription());
        holder.tvPrice.setText(String.valueOf("Rp. " + food.getPrice() + ",-"));

        holder.bind(foodList.get(position));

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPrice, tvDescription, tvStok;
        ImageView imgPhoto, imgBgOrder, imgOrder;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_food);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvStok = itemView.findViewById(R.id.tv_stok);
            tvDescription = itemView.findViewById(R.id.tv_description);
            imgPhoto = itemView.findViewById(R.id.img_item_food);
            imgBgOrder = itemView.findViewById(R.id.bg_select_menu);
            imgOrder = itemView.findViewById(R.id.img_select_menu);

        }

        void bind(final Menu food) {
            tvStok.setVisibility(View.GONE);
            imgBgOrder.setVisibility(View.GONE);
            imgOrder.setVisibility(View.GONE);

            if(food.getStok() < 0) {
                imgBgOrder.setVisibility(View.VISIBLE);
                imgOrder.setVisibility(View.GONE);
                tvStok.setVisibility(View.VISIBLE);
            } else {
                tvStok.setVisibility(View.GONE);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        food.setChecked(!food.isChecked());
                        imgBgOrder.setVisibility(food.isChecked() ? View.VISIBLE : View.GONE);
                        imgOrder.setVisibility(food.isChecked() ? View.VISIBLE : View.GONE);

                        TroliData data = new TroliData();
                        data.setMenu(food);
                        data.setQty(1);
                        data.setNote("");
                        data.setSub_total(food.getPrice());

                        TroliSession troliSession = TroliSession.getInstance();

                        if (food.isChecked()) {
                            troliSession.addtroliData(data);
                        } else {
                            troliSession.removetroliData(data);
                        }
                    }
                });
            }
        }
    }

        public List<Menu> getAll() {
            return foodList;
        }

        public List<Menu> getSelected() {
            List<Menu> selected = new ArrayList<>();
            for (int i = 0; i < foodList.size(); i++) {
                if (foodList.get(i).isChecked()) {
                    selected.add(foodList.get(i));
                }
            }
            return selected;
        }

}
