package com.example.orderandinventorysystem.ui.invoice;

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

import com.example.orderandinventorysystem.Model.Invoice;
import com.example.orderandinventorysystem.Model.Item;
import com.example.orderandinventorysystem.R;
import com.example.orderandinventorysystem.ui.item.ItemListAdapter;
import com.example.orderandinventorysystem.ui.item.ItemMain;

import java.util.ArrayList;

public class InvoiceFragment extends Fragment implements InvoiceListAdapter.ItemClickListener{

    InvoiceListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_invoice, container, false);
        ArrayList<Invoice> invList = new ArrayList<>();
//        invList.add(new Invoice("INV-00001", "SO-00001", "Lee Chong Wei", "12 May 2020", "PAID",12.00));
//        invList.add(new Invoice("INV-00002", "SO-00002", "Hehehe Wei", "12 Aug 2020", "PAID", 100.00));

        RecyclerView recyclerView = root.findViewById(R.id.inv_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new InvoiceListAdapter(getContext(), invList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onItemClick(View view, int position) {
        startActivity(new Intent(getContext(), InvoiceMainFragment.class));
    }
}