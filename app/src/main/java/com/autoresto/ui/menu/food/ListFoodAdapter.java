package com.autoresto.ui.menu.food;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import com.autoresto.R;
import com.autoresto.model.Food;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ListFoodAdapter extends RecyclerView.Adapter<ListFoodAdapter.ListViewHolder> {

    private FoodFragment foodFragment;

    private List<Food> foodList;

    public ListFoodAdapter(FoodFragment foodFragment, List<Food> foodList) {
        this.foodFragment = foodFragment;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_food, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Food food = foodList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(food.getPhoto())
                .apply(new RequestOptions().override(100, 155))
                .into(holder.imgPhoto);

        holder.tvName.setText(food.getName());
        holder.tvPrice.setText(food.getPrice());

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvPrice;
        ImageView imgPhoto;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_food);
            tvPrice = itemView.findViewById(R.id.tv_price);
            imgPhoto = itemView.findViewById(R.id.img_item_food);


        }
    }
}
