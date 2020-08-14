package com.example.orderandinventorysystem.ui.payment;

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
import androidx.viewpager.widget.ViewPager;

import com.example.orderandinventorysystem.Adapter.SectionsPagerAdapter;
import com.example.orderandinventorysystem.ConnectionPhpMyAdmin;
import com.example.orderandinventorysystem.Model.Invoice;
import com.example.orderandinventorysystem.Model.Payment;
import com.example.orderandinventorysystem.R;
import com.example.orderandinventorysystem.ui.invoice.Invoice_Details;
import com.example.orderandinventorysystem.ui.invoice.Invoice_Payments;
import com.example.orderandinventorysystem.ui.invoice.Invoice_Sales;
import com.google.android.material.tabs.TabLayout;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PaymentMain extends AppCompatActivity {

    Payment payment;
    Invoice invoice;
    String intentInvoiceID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_receipt);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Payment Receipt");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        intentInvoiceID = intent.getStringExtra("Payment");
        PaymentInfo paymentInfo = new PaymentInfo(intentInvoiceID);
        paymentInfo.execute("");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    public class PaymentInfo extends AsyncTask<String,String,String> {

        String invoiceID;

        PaymentInfo(String invoiceID) {
            this.invoiceID = invoiceID;
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

                    String query = " SELECT * FROM PAYMENT WHERE invID ='" + invoiceID + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next()) {

                        payment = new Payment(rs.getString(1), rs.getString(2),
                                rs.getString(3), rs.getString(4),
                                rs.getDouble(5));
                    }

                    query = " SELECT * FROM INVOICE WHERE invID ='" + invoiceID + "'";
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    if (rs.next()) {

                        invoice = new Invoice(rs.getString(1), rs.getString(2),
                                rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getDouble(6), rs.getString(7));
                    }

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
        protected void onPostExecute(String s) {

            TextView sales_order_id = findViewById(R.id.sales_order_id);
            sales_order_id.setText(payment.getPayID());

            TextView custName = findViewById(R.id.cust_name);
            custName.setText(invoice.getInvCustName());

            TextView sales_order_price = findViewById(R.id.sales_order_price);
            sales_order_price.setText(String.format("MYR%.2f", invoice.getInvPrice()));

            TextView sales_order_date = findViewById(R.id.sales_order_date);
            sales_order_date.setText(payment.getPayDate());

            TextView invoice_id = findViewById(R.id.invoice_id);
            invoice_id.setText(invoice.getInvID());

            TextView invoice_date = findViewById(R.id.invoice_date);
            invoice_date.setText(invoice.getInvDate());

            TextView item_total_price = findViewById(R.id.item_total_price);
            item_total_price.setText(String.format("MYR%.2f", invoice.getInvPrice()));

        }
    }
}
