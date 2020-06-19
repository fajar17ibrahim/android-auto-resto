package com.autoresto.ui.trolley;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.autoresto.R;
import com.autoresto.ui.trolley.session.TroliData;
import com.autoresto.ui.trolley.session.TroliSession;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ListTrolleyAdapter extends RecyclerView.Adapter<ListTrolleyAdapter.ListViewHolder> {

    private TrolleyActivity trolleyActivity;

    private List<TroliData> trolleyList;

    private Context mContext;

    private Dialog dialog;

    private OnItemClickCallback onItemClickCallback;

    public ListTrolleyAdapter(TrolleyActivity trolleyActivity, List<TroliData> trolleyList) {
        this.mContext = trolleyActivity;
        this.trolleyActivity = trolleyActivity;
        this.trolleyList = trolleyList;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_trolley, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, final int position) {
        final TroliData trolley = trolleyList.get(position);

        if (trolley.getMenu().getPhoto() == null) {
            holder.imgPhoto.setImageResource(R.drawable.ic_menu);
        } else {
            Glide.with(holder.itemView.getContext())
                    .load(trolley.getMenu().getPhoto())
                    .apply(new RequestOptions().override(150, 205))
                    .into(holder.imgPhoto);
        }
        holder.tvName.setText(trolley.getMenu().getName());
        holder.tvDescription.setText(trolley.getMenu().getDescription());
        holder.tvCount.setText(String.valueOf(trolley.getQty()));
        holder.tvSubTotal.setText("Rp. "+ trolley.getSub_total() +",-");

        if (trolley.getNote() == "") {
            holder.tvNote.setText("Tambah catatan");
        } else {
            holder.tvNote.setText(trolley.getNote());
        }

        holder.tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = trolleyList.get(position).getQty();
                if (qty < trolleyList.get(position).getMenu().getStock()) {
                    trolleyList.get(position).setQty(qty+1);
                    float price = trolleyList.get(position).getMenu().getPrice();
                    float total = trolleyList.get(position).getQty()*price;
                    trolleyList.get(position).setSub_total(total);
                    holder.tvCount.setText(String.valueOf(trolleyList.get(position).getQty()));
                    holder.tvSubTotal.setText("Rp. "+ total +",-");
                } else {
                    new AlertDialog.Builder(mContext)
                            .setTitle("Alert")
                            .setMessage("Stok habis! Tersisa " + trolley.getMenu().getStock())
                            .show();
                }
            }
        });

        holder.tvMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trolleyList.get(position).getQty() > 1) {
                    trolleyList.get(position).setQty(trolleyList.get(position).getQty() - 1);
                    float price = trolleyList.get(position).getMenu().getPrice();
                    int qty = trolleyList.get(position).getQty();
                    float total = qty * price;
                    trolleyList.get(position).setSub_total(total);
                    holder.tvCount.setText(String.valueOf(qty));
                    holder.tvSubTotal.setText("Rp. " + total + ",-");
                }
                else {
                    TroliSession troliSession = TroliSession.getInstance();
                    troliSession.removetroliData(trolleyList.get(position).getMenu().getId());
                    notifyDataSetChanged();
                }
            }
        });

        holder.tvNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClick(trolleyList.get(holder.getAdapterPosition()));

                dialog = new Dialog(mContext);
                dialog.setContentView(R.layout.dialog_note);
                final EditText eNote = (EditText) dialog.findViewById(R.id.et_note);
                eNote.setHint(trolleyList.get(position).getMenu().getDescription());
                Button btnAddNote = (Button) dialog.findViewById(R.id.btn_add_note);

                btnAddNote.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.tvNote.setText(eNote.getText());
                        trolleyList.get(position).setNote(holder.tvNote.getText().toString());
                        dialog.hide();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return trolleyList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvNote, tvDescription, tvPlus, tvMin, tvCount, tvSubTotal;
        ImageView imgPhoto;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvNote = (TextView) itemView.findViewById(R.id.tv_note);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
            tvPlus = (TextView) itemView.findViewById(R.id.btn_add);
            tvMin = (TextView) itemView.findViewById(R.id.btn_min);
            tvCount = (TextView) itemView.findViewById(R.id.tv_count);
            tvSubTotal = (TextView) itemView.findViewById(R.id.tv_sub_total);
            imgPhoto = (ImageView) itemView.findViewById(R.id.img_menu);
        }
    }

    public List<TroliData> getAll() {
        return trolleyList;
    }

}
