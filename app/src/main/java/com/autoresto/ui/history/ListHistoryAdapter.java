package com.autoresto.ui.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.autoresto.R;
import com.autoresto.model.Order;

import java.util.List;

public class ListHistoryAdapter extends RecyclerView.Adapter<ListHistoryAdapter.ListViewHolder> {

    private HistoryActivity historyActivity;

    private List<Order> orderList;

    public ListHistoryAdapter(HistoryActivity historyActivity, List<Order> orderList) {
        this.historyActivity = historyActivity;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_history, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Order order = orderList.get(position);

        holder.tvOrderId.setText(order.getId());
        holder.tvStatus.setText(order.getStatus());
        holder.tvCreatedAt.setText(order.getCreated_at());

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tvOrderId, tvStatus, tvCreatedAt;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOrderId = (TextView) itemView.findViewById(R.id.tv_order_id);
            tvStatus = (TextView) itemView.findViewById(R.id.tv_status);
            tvCreatedAt = (TextView) itemView.findViewById(R.id.tv_created_at);
        }
    }
}
