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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ListOrderDetailAdapter extends RecyclerView.Adapter<ListOrderDetailAdapter.ListViewHolder> {

    private OrderDetailActivity orderDetailActivity;

    private List<OrderDetail> orderDetailList;

    public ListOrderDetailAdapter(OrderDetailActivity orderDetailActivity, List<OrderDetail> orderDetailList) {
        this.orderDetailActivity = orderDetailActivity;
        this.orderDetailList = orderDetailList;
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

//            Glide.with(holder.itemView.getContext())
//            .load(orderDetail.getPhoto())
//            .apply(new RequestOptions().override(150, 205))
//            .into(holder.imgPhoto);

           // holder.tvName.setText(orderDetail.getName());
            holder.tvNote.setText(orderDetail.getNote());
           // holder.tvPrice.setText(orderDetail.getPrice());
            holder.tvQty.setText(orderDetail.getQty());
          //  holder.tvTotal.setText("Rp "+orderDetail.getTotal());

            }

    @Override
    public int getItemCount() {
            return orderDetailList.size();
            }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvNote, tvPrice, tvQty, tvTotal;
        ImageView imgPhoto;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvNote = (TextView) itemView.findViewById(R.id.tv_note);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            tvQty = (TextView) itemView.findViewById(R.id.tv_qty);
            tvTotal = (TextView) itemView.findViewById(R.id.tv_total_price);
            imgPhoto = (ImageView) itemView.findViewById(R.id.img_menu);

        }
    }
}
