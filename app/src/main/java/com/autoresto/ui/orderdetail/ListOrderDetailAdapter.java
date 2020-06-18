package com.autoresto.ui.orderdetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.autoresto.R;
import com.autoresto.model.OrderDetail;
import com.autoresto.model.OrderSendDetail;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ListOrderDetailAdapter extends RecyclerView.Adapter<ListOrderDetailAdapter.ListViewHolder> {

    private OrderDetailActivity orderDetailActivity;

    private List<OrderDetail> orderDetailList;

    public OnItemClickCallback onItemClickCallback;

    public ListOrderDetailAdapter(OrderDetailActivity orderDetailActivity, List<OrderDetail> orderDetailList) {
        this.orderDetailActivity = orderDetailActivity;
        this.orderDetailList = orderDetailList;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_order_detail, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        OrderDetail orderDetail = orderDetailList.get(position);

        if ( orderDetail.getMenu().getPhoto() == null) {
            holder.imgPhoto.setImageResource(R.drawable.ic_menu);
        } else {

            Glide.with(holder.itemView.getContext())
                    .load(orderDetail.getMenu().getPhoto())
                    .apply(new RequestOptions().override(150, 205))
                    .into(holder.imgPhoto);
        }

        holder.tvName.setText(orderDetail.getMenu().getName());

        if (orderDetail.getNote() == "") {
            holder.tvNote.setText("Tidak ada catatan");
        } else {
            holder.tvNote.setText(orderDetail.getNote());
        }

        holder.tvQty.setText(orderDetail.getQty() + " x "+ orderDetail.getMenu().getPrice());
        holder.tvTotal.setText("Rp "+orderDetail.getSubtotal() + ",-");

    }

    @Override
    public int getItemCount() {
        return orderDetailList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvNote, tvQty, tvTotal;
        ImageView imgPhoto;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvNote = (TextView) itemView.findViewById(R.id.tv_note);
            tvQty = (TextView) itemView.findViewById(R.id.tv_description);
            tvTotal = (TextView) itemView.findViewById(R.id.tv_total_price);
            imgPhoto = (ImageView) itemView.findViewById(R.id.img_menu);

        }
    }

    public List<OrderDetail> getAll() {
        return orderDetailList;
    }
}
