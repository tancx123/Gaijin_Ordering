package com.example.orderandinventorysystem.ui.purchase;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.orderandinventorysystem.R;


public class add_new_purchase extends AppCompatActivity {

    private Button BtnAddLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_purchase);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Purchase Order");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        BtnAddLine = findViewById(R.id.add_line_item_btn);

        BtnAddLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddLineItem();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_main_menu, menu);
        return true;
    }

    public void openAddLineItem(){
        Intent intent = new Intent(this, add_line_item.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
