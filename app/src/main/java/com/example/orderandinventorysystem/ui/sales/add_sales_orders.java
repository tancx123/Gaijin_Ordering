package com.example.orderandinventorysystem.ui.sales;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderandinventorysystem.ConnectionPhpMyAdmin;
import com.example.orderandinventorysystem.Model.Customer;
import com.example.orderandinventorysystem.Model.Item;
import com.example.orderandinventorysystem.Model.ItemOrder;
import com.example.orderandinventorysystem.R;
import com.example.orderandinventorysystem.ui.customer.CustomerFragment;
import com.example.orderandinventorysystem.ui.customer.CustomerListAdapter;
import com.example.orderandinventorysystem.ui.customer.CustomerMain;
import com.example.orderandinventorysystem.ui.purchase.add_line_item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class add_sales_orders extends AppCompatActivity implements CustomerListAdapter.ItemClickListener {

    private Button BtnAddLine;
    CustomerListAdapter adapter;
    ItemOrderListAdapter adapter2;
    ArrayList<ItemOrder> ioList;
    ArrayList<Customer> custList;
    String custID;
    RecyclerView recyclerView, recyclerView2;
    EditText editText;
    private boolean shouldRefreshOnResume = false;

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

        ShowCustList showCustList = new ShowCustList();
        showCustList.execute("");
        custList = new ArrayList<>();
        ioList = new ArrayList<>();

        recyclerView = findViewById(R.id.custSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomerListAdapter(this, custList);

        recyclerView2 = findViewById(R.id.itemLine);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        adapter2 = new ItemOrderListAdapter(this, ioList);
        recyclerView2.setAdapter(adapter2); //<<<<<<<<<<<<<<<-----------------------

        adapter.setClickListener(this);

        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        TextView date =  findViewById(R.id.text_sales_order_date_input);
        date.setText(currentDate);
        editText = findViewById(R.id.text_customer_name_input_sales);
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

        BtnAddLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddSalesLineItem();
            }
        });
    }

    @Override
    public void onItemClick(View view, int position, String id, String name) {

        custID = id;
        editText.setText(name);
        editText.clearFocus();
    }

    public void filter(String text) {
        ArrayList<Customer> filteredList = new ArrayList<>();

        for (Customer item : custList) {
            if (item.getCustName().toLowerCase().contains(text.toLowerCase())) {
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

    public void openAddSalesLineItem(){
        Intent intent = new Intent(this, add_sales_line_item.class);
        startActivityForResult(intent, 1);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                ItemOrder io = (ItemOrder) data.getSerializableExtra("itemOrder");
                RelativeLayout relativeLayout = findViewById(R.id.itemLineLayout);
                relativeLayout.setVisibility(View.VISIBLE);
                boolean check = false;
                for (int i = 0 ; i < ioList.size() ; i++) {

                    if (ioList.get(i).getItemID().equals(io.getItemID())) {

                        ioList.get(i).setQuantity(ioList.get(i).getQuantity() + io.getQuantity());
                        ioList.get(i).setTotal(ioList.get(i).getQuantity() * ioList.get(i).getSellPrice());
                        check = true;
                        break;
                    }
                }
                if (check == false)
                    ioList.add(io);

                recyclerView2.setLayoutManager(new LinearLayoutManager(this));
                adapter2 = new ItemOrderListAdapter(this, ioList);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    public class ShowCustList extends AsyncTask<String,String,String> {

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

                    String query = " SELECT * FROM CUSTOMER ";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    while (rs.next()) {
                        custList.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)));
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
