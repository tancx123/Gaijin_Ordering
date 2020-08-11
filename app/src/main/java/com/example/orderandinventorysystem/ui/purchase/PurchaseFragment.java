package com.example.orderandinventorysystem.ui.purchase;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderandinventorysystem.Model.Purchase;
import com.example.orderandinventorysystem.R;

import java.util.ArrayList;

public class PurchaseFragment extends Fragment implements PurchaseListAdapter.ItemClickListener{

    PurchaseListAdapter padapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_purchase, container, false);

        ArrayList<Purchase> purchaseList = new ArrayList<>();
        purchaseList.add(new Purchase("Leong Chai Chao", "Malaysia", "PO-00001", "3-8-2020", "8-8-2020", "RM 300.00"));
        purchaseList.add(new Purchase("Lim Abbu", "Singapore", "PO-00002", "2-8-2020", "7-8-2020","rm 750.00"));

        RecyclerView recyclerView = root.findViewById(R.id.purchase_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        padapter = new PurchaseListAdapter(getContext(), purchaseList);
        padapter.setClickListener(this);
        recyclerView.setAdapter(padapter);

        return root;
    }

    @Override
    public void onItemClick(View view, int position) {
        startActivity(new Intent(getContext(), PurchaseMain.class));
    }
}