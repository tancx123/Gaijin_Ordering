package com.example.orderandinventorysystem.ui.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.orderandinventorysystem.R;

public class CustomerFragment extends Fragment {

    private CustomerViewModel customerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        customerViewModel =
                ViewModelProviders.of(this).get(CustomerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_customer, container, false);
        return root;
    }
}