package com.example.orderandinventorysystem.ui.invoice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.orderandinventorysystem.R;

public class Invoice_Payments extends Fragment {


 public Invoice_Payments() {

 }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_invoice_payment, container, false);
    }

}
