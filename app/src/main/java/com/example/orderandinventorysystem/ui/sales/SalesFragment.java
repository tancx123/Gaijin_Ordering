package com.example.orderandinventorysystem.ui.sales;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.orderandinventorysystem.R;

public class SalesFragment extends Fragment {

    private SalesViewModel salesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        salesViewModel =
                ViewModelProviders.of(this).get(SalesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_customer, container, false);
        return root;
    }
}