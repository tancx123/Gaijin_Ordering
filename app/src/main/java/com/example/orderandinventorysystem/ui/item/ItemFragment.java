package com.example.orderandinventorysystem.ui.item;

import android.content.Intent;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderandinventorysystem.Model.Item;
import com.example.orderandinventorysystem.R;

import java.util.ArrayList;

public class ItemFragment extends Fragment implements ItemListAdapter.ItemClickListener {

    ItemListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_item, container, false);
        ArrayList<Item> itemList = new ArrayList<>();
//        itemList.add(new Item("Machine A", "S228378VJWU232", "pcs", "Good machine", 0,13.00, 5.00));
//        itemList.add(new Item("Machine B", "S88KDMWIH12312", "box", "Haha", 0,120.00, 100.00));

        RecyclerView recyclerView = root.findViewById(R.id.item_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ItemListAdapter(getContext(), itemList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        return root;
    }

    @Override
    public void onItemClick(View view, int position, String id, String name, double price) {
        startActivity(new Intent(getContext(), ItemMain.class));
    }
}