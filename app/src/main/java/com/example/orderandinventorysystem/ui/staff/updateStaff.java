package com.example.orderandinventorysystem.ui.staff;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.orderandinventorysystem.R;

public class updateStaff extends AppCompatActivity {
    private Spinner spnDpt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_staff);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Update Staff");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        spnDpt1 = (Spinner) findViewById(R.id.update_spin_department);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.staff_dpt, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDpt1.setAdapter(adapter);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}