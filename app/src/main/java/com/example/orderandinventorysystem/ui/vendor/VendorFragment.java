package com.example.orderandinventorysystem.ui.vendor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.orderandinventorysystem.R;

public class VendorFragment extends Fragment {

    private VendorViewModel vendorViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vendorViewModel =
                ViewModelProviders.of(this).get(VendorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_item, container, false);
        return root;
    }
}