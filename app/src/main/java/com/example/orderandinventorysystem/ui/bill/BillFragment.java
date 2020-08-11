package com.example.orderandinventorysystem.ui.bill;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderandinventorysystem.Model.Bill;
import com.example.orderandinventorysystem.R;

import java.util.ArrayList;

public class BillFragment extends Fragment implements BillListAdapter.ItemClickListener{

    BillListAdapter badapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bill, container, false);

        ArrayList<Bill> billList = new ArrayList<>();
        billList.add(new Bill("Bruce Lee", "PO-00001", "02-08-2020", "02-08-2022", "Exclusion"));
        billList.add(new Bill("Ali Baba", "PO-00002", "01-08-2020", "01-08-2022", "Inclusion"));

        RecyclerView recyclerView = root.findViewById(R.id.bill_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        badapter = new BillListAdapter(getContext(), billList);
        badapter.setClickListener(this);
        recyclerView.setAdapter(badapter);

        return root;
    }

    @Override
    public void onItemClick(View view, int position) {
        startActivity(new Intent(getContext(), BillMain.class));
    }
}