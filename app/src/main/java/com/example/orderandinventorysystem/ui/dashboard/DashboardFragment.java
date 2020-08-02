package com.example.orderandinventorysystem.ui.dashboard;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.orderandinventorysystem.CustomerOrder;
import com.example.orderandinventorysystem.MainActivity;
import com.example.orderandinventorysystem.R;
import com.example.orderandinventorysystem.ui.invoice.InvoiceMainFragment;
import com.example.orderandinventorysystem.ui.pack.PackageMain;
import com.example.orderandinventorysystem.ui.payment.PaymentMain;
import com.example.orderandinventorysystem.ui.sales.SalesOrderMainFragment;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
                startActivity(new Intent(getContext(), PaymentMain.class));
            }
        });
        FloatingActionButton fab2 = root.findViewById(R.id.invFB);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), InvoiceMainFragment.class));
            }
        });
        FloatingActionButton fab3 = root.findViewById(R.id.salesFB);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SalesOrderMainFragment.class));
            }
        });
        FloatingActionButton fab4 = root.findViewById(R.id.billFB);
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PackageMain.class));
            }
        });
        return root;
    }
}