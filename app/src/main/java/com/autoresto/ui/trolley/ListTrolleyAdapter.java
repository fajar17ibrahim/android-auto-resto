package com.autoresto.ui.trolley;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.autoresto.R;
import com.autoresto.model.Trolley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class ListTrolleyAdapter extends RecyclerView.Adapter<ListTrolleyAdapter.ListViewHolder> {

    private TrolleyActivity trolleyActivity;

    private List<Trolley> trolleyList;

    public ListTrolleyAdapter(TrolleyActivity trolleyActivity, List<Trolley> trolleyList) {
        this.trolleyActivity = trolleyActivity;
        this.trolleyList = trolleyList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_trolley, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {
        final Trolley trolley = trolleyList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(trolley.getPhoto())
                .apply(new RequestOptions().override(150, 205))
                .into(holder.imgPhoto);

        holder.tvName.setText(trolley.getName());
        holder.tvPrice.setText(trolley.getPrice());
        holder.tvQty.setText(String.valueOf(trolley.getQty()));
        holder.tvCount.setText(String.valueOf(trolley.getQty()));

        if (trolley.getNote() == "") {
            holder.tvNote.setText("Tambah catatan");
        } else {
            holder.tvNote.setText(trolley.getNote());
        }

        holder.tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trolleyList.get(position).setQty(trolleyList.get(position).getQty()+1);
                int price = Integer.parseInt(trolleyList.get(position).getPrice());
                int qty = trolleyList.get(position).getQty();
                int total = qty*price;
                trolleyList.get(position).setTotal(String.valueOf(total));
                holder.tvCount.setText(String.valueOf(qty));
                holder.tvQty.setText(String.valueOf(qty));
            }
        });

        holder.tvMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trolley.getQty() > 1 ) {
                    trolleyList.get(position).setQty(trolleyList.get(position).getQty() - 1);
                }
                int price = Integer.parseInt(trolleyList.get(position).getPrice());
                int qty = trolleyList.get(position).getQty();
                int total = qty*price;
                trolleyList.get(position).setTotal(String.valueOf(total));
                holder.tvCount.setText(String.valueOf(qty));
                holder.tvQty.setText(String.valueOf(qty));
            }
        });
    }

    @Override
    public int getItemCount() {
        return trolleyList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvNote, tvPrice, tvQty, tvPlus, tvMin, tvCount;
        ImageView imgPhoto;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvNote = (TextView) itemView.findViewById(R.id.tv_note);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            tvQty = (TextView) itemView.findViewById(R.id.tv_qty);
            tvPlus = (TextView) itemView.findViewById(R.id.btn_add);
            tvMin = (TextView) itemView.findViewById(R.id.btn_min);
            tvCount = (TextView) itemView.findViewById(R.id.tv_count);
            imgPhoto = (ImageView) itemView.findViewById(R.id.img_menu);
        }
    }

    public List<Trolley> getSelected() {
        List<Trolley> selected = new ArrayList<>();
        for (int i = 0; i < trolleyList.size(); i++) {
                selected.add(trolleyList.get(i));
        }
        return selected;
    }
}
