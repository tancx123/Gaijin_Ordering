package com.example.orderandinventorysystem.ui.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderandinventorysystem.Model.Customer;
import com.example.orderandinventorysystem.R;
import com.example.orderandinventorysystem.ui.payment.PaymentMain;

import java.util.ArrayList;

public class CustomerFragment extends Fragment implements CustomerListAdapter.ItemClickListener {

    CustomerListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_customer, container, false);

        ArrayList<Customer> custList = new ArrayList<>();
        custList.add(new Customer("Lee Chong Wei", "992233-08-2722", "LALA Sdn Bhd", "lala@gmail.com", "04-1231231", "012-1231231", 'B', 'M'));
        custList.add(new Customer("Lee Abbee", "992333-08-2722", "777 Sdn Bhd", "777@gmail.com", "04-1231231", "012-1231231", 'I', 'F'));
        custList.add(new Customer("Chong Bee Wei", "992233-08-2722", "ABC Sdn Bhd", "abc@gmail.com", "04-1231231", "012-1231231", 'B', 'M'));

        RecyclerView recyclerView = root.findViewById(R.id.cust_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CustomerListAdapter(getContext(), custList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onItemClick(View view, int position) {
        startActivity(new Intent(getContext(), CustomerMain.class));
    }
}