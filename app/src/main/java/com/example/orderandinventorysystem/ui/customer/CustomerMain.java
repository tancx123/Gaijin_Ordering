package com.example.orderandinventorysystem.ui.customer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.orderandinventorysystem.ConnectionPhpMyAdmin;
import com.example.orderandinventorysystem.Model.Customer;
import com.example.orderandinventorysystem.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerMain extends AppCompatActivity {

    Customer cust;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_main);
        Intent intent = getIntent();
        cust = (Customer) intent.getSerializableExtra("Customer");

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(cust.getCustName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView compName = (TextView)findViewById(R.id.comp_name);
        compName.setText(cust.getCompanyName());
        TextView custType = (TextView)findViewById(R.id.customer_type);
        custType.setText(cust.getCustType());
        TextView gender = (TextView)findViewById(R.id.gender);
        gender.setText(cust.getGender());
        TextView icNo = (TextView)findViewById(R.id.ic_number);
        icNo.setText(cust.getIcNo());
        TextView phone = (TextView)findViewById(R.id.phone);
        phone.setText(cust.getPhone());
        TextView mobile = (TextView)findViewById(R.id.mobile);
        mobile.setText(cust.getMobile());
        TextView address = (TextView)findViewById(R.id.address);
        address.setText(cust.getAddress());
        TextView email = (TextView)findViewById(R.id.email);
        email.setText(cust.getEmail());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.customer_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle item selection
        switch (item.getItemId()) {

            case R.id.delete: {
                DeleteCust deleteCust = new DeleteCust(cust.getIcNo());
                deleteCust.execute("");
                this.finish();

                return true;
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

    public class DeleteCust extends AsyncTask<String,String,String> {
        String ic;

        DeleteCust(String ic) {

            this.ic = ic;
        }

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
                    checkConnection = "Please check your internet connection.";
                } else {

                    String query = " DELETE FROM CUSTOMER WHERE icNo ='" + ic + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(query);
                    Log.d("Success", "Done");
                    checkConnection = "Done";
                    isSuccess = true;

                }
            } catch (Exception ex) {
                Log.d("Error", ex.toString());
                isSuccess = false;
                checkConnection = "Exceptions" + ex;
            }

            return checkConnection;
        }

        @Override
        protected void onPostExecute(String s) {}
    }
}
