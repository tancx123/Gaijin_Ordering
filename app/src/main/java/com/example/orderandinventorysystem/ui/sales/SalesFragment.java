package com.example.orderandinventorysystem.ui.sales;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderandinventorysystem.Model.Sales;
import com.example.orderandinventorysystem.R;
import com.example.orderandinventorysystem.ui.item.ItemMain;

import java.util.ArrayList;

public class SalesFragment extends Fragment implements SalesListAdapter.ItemClickListener  {

    SalesListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_sales, container, false);
        ArrayList<Sales> salesList = new ArrayList<>();
        salesList.add(new Sales("SO-00001", "Lee Chong Wei", "12 May 2020", 12.00, "CLOSED"));
        salesList.add(new Sales("SO-00002", "Lee Abu", "13 May 2020", 14.00, "OPEN"));
        RecyclerView recyclerView = root.findViewById(R.id.sales_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SalesListAdapter(getContext(), salesList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onItemClick(View view, int position) {
        startActivity(new Intent(getContext(), SalesOrderMainFragment.class));
    }
}