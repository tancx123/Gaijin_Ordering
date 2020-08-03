package com.example.orderandinventorysystem.ui.invoice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.orderandinventorysystem.Model.Invoice;
import com.example.orderandinventorysystem.R;

import java.util.List;

public class InvoiceListAdapter extends RecyclerView.Adapter<InvoiceListAdapter.ViewHolder> {

    private List<Invoice> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public InvoiceListAdapter(Context context, List<Invoice> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.invoice_list_layout, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Invoice inv = mData.get(position);
        holder.name.setText(inv.getInvCustName());
        holder.id.setText(inv.getSalesID());
        holder.id.setText(inv.getInvID());
        holder.date.setText(inv.getInvDate());
        holder.status.setText(inv.getInvStatus());
        holder.price.setText(String.format("%.2f", inv.getInvPrice()));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, id, date, status, price, id2;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cust_name_view);
            id = itemView.findViewById(R.id.invoice_id);
            id2 = itemView.findViewById(R.id.sales_order_id);
            date = itemView.findViewById(R.id.invoice_date);
            status = itemView.findViewById(R.id.inv_order_status2);
            price = itemView.findViewById(R.id.inv_order_price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public Invoice getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
