package com.example.orderandinventorysystem.ui.vendor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderandinventorysystem.Model.Vendor;
import com.example.orderandinventorysystem.R;

import java.util.ArrayList;

public class VendorFragment extends Fragment implements VendorListAdapter.ItemClickListener {

    VendorListAdapter vadapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_vendor, container, false);

        ArrayList<Vendor> vendList = new ArrayList<>();
        vendList.add(new Vendor("Leong Chao Chao", "GENTING Sdn Bhd", "lala@gmail.com", "012-3456789", "04-1231231"));
        vendList.add(new Vendor("Lee Abbee", "ASSA Sdn Bhd", "assa@gmail.com", "012-3456789", "04-2233233"));
        vendList.add(new Vendor("Chong Bee Wei", "BB Sdn Bhd", "bb@gmail.com", "012-3456789", "04-3233233"));

        RecyclerView recyclerView = root.findViewById(R.id.vendor_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        vadapter = new VendorListAdapter(getContext(), vendList);
        vadapter.setClickListener(this);
        recyclerView.setAdapter(vadapter);

        return root;
    }

    @Override
    public void onItemClick(View view, int position) {
        startActivity(new Intent(getContext(), VendorMain.class));
    }
}