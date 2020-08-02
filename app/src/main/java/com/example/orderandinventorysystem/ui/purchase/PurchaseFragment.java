package com.example.orderandinventorysystem.ui.purchase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.orderandinventorysystem.R;

public class PurchaseFragment extends Fragment {

    private PurchaseViewModel purchaseViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        purchaseViewModel =
                ViewModelProviders.of(this).get(PurchaseViewModel.class);
        View root = inflater.inflate(R.layout.fragment_customer, container, false);
        return root;
    }
}