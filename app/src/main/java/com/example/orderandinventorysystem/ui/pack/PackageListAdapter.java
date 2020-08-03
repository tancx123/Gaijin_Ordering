package com.example.orderandinventorysystem.ui.pack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.orderandinventorysystem.Model.Invoice;
import com.example.orderandinventorysystem.Model.Package;
import com.example.orderandinventorysystem.R;

import java.util.List;

public class PackageListAdapter extends RecyclerView.Adapter<PackageListAdapter.ViewHolder> {

    private List<Package> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public PackageListAdapter(Context context, List<Package> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.pack_list_layout, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Package pack = mData.get(position);
        //holder.name.setText();
        holder.id.setText(pack.getPackID());
        holder.id2.setText(pack.getSalesID());
        holder.date.setText(pack.getPackDate());
        //holder.status.setText(pack.get());
        //holder.quantity.setText(String.format("%.d", pack.()));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, id, date, status, quantity, id2;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cust_name_view);
            id = itemView.findViewById(R.id.pack_id);
            id2 = itemView.findViewById(R.id.sales_order_id);
            date = itemView.findViewById(R.id.pack_date);
            status = itemView.findViewById(R.id.pack_status2);
            quantity = itemView.findViewById(R.id.item_quantity);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public Package getItem(int id) {
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
