package com.example.orderandinventorysystem.ui.bill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.orderandinventorysystem.Model.Bill;
import com.example.orderandinventorysystem.R;

import java.util.List;

public class BillListAdapter extends RecyclerView.Adapter<BillListAdapter.ViewHolder>{

    private List<Bill> mData;
    private LayoutInflater mInflater;
    private BillListAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    public BillListAdapter(Context context, List<Bill> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public BillListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.bill_list_layout, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(BillListAdapter.ViewHolder holder, int position) {
        Bill bil = mData.get(position);
        holder.myTextView.setText(bil.getVenName());
        holder.order.setText(bil.getBillNo());
        holder.bdate.setText(bil.getbDate());


    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView, order, bdate;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.vend_name_view);
            order = itemView.findViewById(R.id.order_view);
            bdate = itemView.findViewById(R.id.bdate_view);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public Bill getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(BillListAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
