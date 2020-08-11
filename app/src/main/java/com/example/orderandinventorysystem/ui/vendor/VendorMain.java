package com.example.orderandinventorysystem.ui.vendor;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.orderandinventorysystem.R;


public class VendorMain extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendor_main);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("(Sample) Vendor Lee");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.vendor_main_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
