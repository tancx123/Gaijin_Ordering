package com.example.orderandinventorysystem.ui.sales;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.orderandinventorysystem.Model.ItemOrder;
import com.example.orderandinventorysystem.R;

import java.util.List;

public class ItemOrderListAdapter extends RecyclerView.Adapter<ItemOrderListAdapter.ViewHolder> {

    private List<ItemOrder> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public ItemOrderListAdapter(Context context, List<ItemOrder> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_order_list_layout, parent, false);
        return new ViewHolder(view);

    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ItemOrder itemOrder = mData.get(position);
        holder.name.setText(itemOrder.getItemName());
        holder.price_quantity.setText(String.format("%d x %.2f", itemOrder.getQuantity(), itemOrder.getSellPrice()));
        holder.price.setText(String.format("MYR%.2f", itemOrder.getTotal()));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView id, name, price_quantity, price;

        ViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.item_id);
            name = itemView.findViewById(R.id.item_name);
            price_quantity = itemView.findViewById(R.id.item_quantity_price);
            price = itemView.findViewById(R.id.item_price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick2(view, getAdapterPosition(), id.getText().toString(), name.getText().toString());
        }
    }

    // convenience method for getting data at click position
    public ItemOrder getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick2(View view, int position, String id, String name);
    }
}
