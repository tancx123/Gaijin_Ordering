package com.example.orderandinventorysystem.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.orderandinventorysystem.R;
import com.example.orderandinventorysystem.ui.bill.add_new_bill;
import com.example.orderandinventorysystem.ui.invoice.add_new_invoice;
import com.example.orderandinventorysystem.ui.sales.add_sales_orders;
import com.example.orderandinventorysystem.ui.staff.loginStaff;
import com.getbase.floatingactionbutton.FloatingActionButton;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        FloatingActionButton fab = root.findViewById(R.id.purchFB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), loginStaff.class));
            }
        });
        FloatingActionButton fab2 = root.findViewById(R.id.invFB);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), add_new_invoice.class));
            }
        });
        FloatingActionButton fab3 = root.findViewById(R.id.salesFB);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), add_sales_orders.class));
            }
        });
        FloatingActionButton fab4 = root.findViewById(R.id.billFB);
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), add_new_bill.class));
            }
        });
        return root;
    }
}