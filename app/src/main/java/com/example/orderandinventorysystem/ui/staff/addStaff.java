package com.example.orderandinventorysystem.ui.staff;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.orderandinventorysystem.R;

public class addStaff extends AppCompatActivity {

    private Spinner spnDpt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Staff");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        spnDpt = (Spinner) findViewById(R.id.spin_department);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.staff_dpt, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDpt.setAdapter(adapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}