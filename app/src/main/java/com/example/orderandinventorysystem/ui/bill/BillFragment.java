package com.example.orderandinventorysystem.ui.bill;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.orderandinventorysystem.R;

public class BillFragment extends Fragment {

    private BillViewModel billViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        billViewModel =
                ViewModelProviders.of(this).get(BillViewModel.class);
        View root = inflater.inflate(R.layout.fragment_customer, container, false);
        return root;
    }
}