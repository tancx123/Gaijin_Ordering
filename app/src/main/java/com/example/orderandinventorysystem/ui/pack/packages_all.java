package com.example.orderandinventorysystem.ui.pack;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderandinventorysystem.Model.Package;
import com.example.orderandinventorysystem.Model.Sales;
import com.example.orderandinventorysystem.R;
import com.example.orderandinventorysystem.ui.sales.SalesListAdapter;
import com.example.orderandinventorysystem.ui.sales.SalesOrderMainFragment;

import java.util.ArrayList;


public class packages_all extends Fragment implements PackageListAdapter.ItemClickListener   {

    PackageListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_packages_all, container, false);
        ArrayList<Package> packList = new ArrayList<>();
        packList.add(new Package("PAC-00001", "12 May 2020", "SO-00001"));
        packList.add(new Package("PAC-00002", "13 May 2020", "SO-00002"));
        RecyclerView recyclerView = root.findViewById(R.id.pack_all_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PackageListAdapter(getContext(), packList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onItemClick(View view, int position) {
        startActivity(new Intent(getContext(), PackageMain.class));
    }
}
