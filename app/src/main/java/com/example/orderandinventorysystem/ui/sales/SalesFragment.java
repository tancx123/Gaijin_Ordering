package com.example.orderandinventorysystem.ui.sales;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.orderandinventorysystem.Adapter.SectionsPagerAdapter;
import com.example.orderandinventorysystem.ConnectionPhpMyAdmin;
import com.example.orderandinventorysystem.Model.Customer;
import com.example.orderandinventorysystem.Model.Sales;
import com.example.orderandinventorysystem.R;
import com.example.orderandinventorysystem.ui.customer.CustomerFragment;
import com.example.orderandinventorysystem.ui.customer.CustomerListAdapter;
import com.example.orderandinventorysystem.ui.customer.CustomerMain;
import com.example.orderandinventorysystem.ui.customer.new_customer;
import com.example.orderandinventorysystem.ui.item.ItemMain;
import com.example.orderandinventorysystem.ui.pack.packages_all;
import com.example.orderandinventorysystem.ui.pack.packages_delivered;
import com.example.orderandinventorysystem.ui.pack.packages_packed;
import com.example.orderandinventorysystem.ui.pack.packages_shipped;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SalesFragment extends Fragment  {

    View root;
    ViewPager viewPager;
    TabLayout tabLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_packages, container, false);
        viewPager = root.findViewById(R.id.viewpager_all);
        tabLayout = root.findViewById(R.id.tabLayout);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new sales_avail(), "AVAILABLE");
        adapter.addFragment(new sales_remove(), "REMOVED");
        viewPager.setAdapter(adapter);
    }



}