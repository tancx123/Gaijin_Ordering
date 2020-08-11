package com.example.orderandinventorysystem.ui.sales;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderandinventorysystem.ConnectionPhpMyAdmin;
import com.example.orderandinventorysystem.Model.Customer;
import com.example.orderandinventorysystem.Model.Item;
import com.example.orderandinventorysystem.Model.ItemOrder;
import com.example.orderandinventorysystem.R;

import com.example.orderandinventorysystem.ui.customer.CustomerListAdapter;
import com.example.orderandinventorysystem.ui.customer.CustomerMain;
import com.example.orderandinventorysystem.ui.customer.new_customer;
import com.example.orderandinventorysystem.ui.item.ItemListAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class add_sales_line_item extends AppCompatActivity implements ItemListAdapter.ItemClickListener  {

    ItemOrder itemOrder;
    ItemListAdapter adapter;
    ArrayList<Item> itemList;
    RecyclerView recyclerView;
    String itemID, itemName;
    double itemPrice;
    EditText editText, quantityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales_line_item);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Line Item");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ShowItemList showItemList = new ShowItemList();
        showItemList.execute("");
        itemList = new ArrayList<>();
        recyclerView = findViewById(R.id.itemSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemListAdapter(this, itemList);
        adapter.setClickListener(this);
        editText = findViewById(R.id.text_item_input_sales);
        quantityEditText = findViewById(R.id.text_quantity_input_sales);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    recyclerView.setVisibility(View.INVISIBLE);
                }
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    public void filter(String text) {
        ArrayList<Item> filteredList = new ArrayList<>();

        for (Item item : itemList) {
            if (item.getItemName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);

        if (text.length() == 0 || filteredList.isEmpty()) {
            recyclerView.setVisibility(View.INVISIBLE);
        }

        else {
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.done_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done: {
                //constructor
                ItemOrder itemOrder = new ItemOrder(itemID, itemName, itemPrice, Integer.parseInt(quantityEditText.getText().toString()));
                Intent returnIntent = new Intent();
                returnIntent.putExtra("itemOrder", itemOrder);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onItemClick(View view, int position, String id, String name, double price) {

        itemPrice = price;
        itemName = name;
        itemID = id;
        editText.setText(name);
        editText.clearFocus();
    }

    public class ShowItemList extends AsyncTask<String,String,String> {

        String checkConnection = "";
        boolean isSuccess = false;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {

            ConnectionPhpMyAdmin connectionClass = new ConnectionPhpMyAdmin();
            try {
                Connection con = connectionClass.CONN();
                if (con == null) {
                    checkConnection = "No";
                } else {

                    String query = " SELECT * FROM ITEM ";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    while (rs.next()) {
                        itemList.add(new Item(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDouble(6), rs.getDouble(7)));
                        Log.d("Success", rs.getString(1));
                    }

                    checkConnection = "Yes";
                    isSuccess = true;

                }
            } catch (Exception ex) {
                Log.d("Error", ex.toString());
                isSuccess = false;
                checkConnection = "No";
            }

            return checkConnection;
        }

        @Override
        protected void onPostExecute(String s) {
            recyclerView.setAdapter(adapter);
        }
    }


}
