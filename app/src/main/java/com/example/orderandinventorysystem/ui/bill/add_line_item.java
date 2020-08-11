package com.example.orderandinventorysystem.ui.bill;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.orderandinventorysystem.R;

public class add_line_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_line_item);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Line Item");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.done_main_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
