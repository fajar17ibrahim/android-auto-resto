package com.autoresto.ui.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.autoresto.R;
import com.autoresto.model.Order;

import java.util.List;

public class ListOrderAdapter extends RecyclerView.Adapter<ListOrderAdapter.ListViewHolder> {

    private List<Order> orderList;

    private OrderFragment orderFragment;

    private OnItemClickCallback onItemClickCallback;

    public ListOrderAdapter(List<Order> orderList, OrderFragment orderFragment) {
        this.orderList = orderList;
        this.orderFragment = orderFragment;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListOrderAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_order, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListOrderAdapter.ListViewHolder holder, int position) {
        Order order = orderList.get(position);

        holder.tvOrderId.setText(order.getQr_code());
        holder.tvStatus.setText("Pesanan sedang diproses");
        holder.tvCreatedAt.setText(order.getCreated_at());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(orderList.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView tvOrderId, tvStatus, tvCreatedAt;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOrderId = itemView.findViewById(R.id.tv_order_id);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvCreatedAt = itemView.findViewById(R.id.tv_created_at);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(Order order);
    }
}
