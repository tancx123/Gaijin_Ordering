package com.example.orderandinventorysystem.ui.sales;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.orderandinventorysystem.R;
import com.example.orderandinventorysystem.ui.purchase.add_line_item;

public class add_sales_orders extends AppCompatActivity {

    private Button BtnAddLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales_orders);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Sales Order");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        BtnAddLine = findViewById(R.id.add_sales_line_item_btn);

        BtnAddLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddSalesLineItem();
            }
        });
    }

    public void openAddSalesLineItem(){
        Intent intent = new Intent(this, add_sales_line_item.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_main_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
