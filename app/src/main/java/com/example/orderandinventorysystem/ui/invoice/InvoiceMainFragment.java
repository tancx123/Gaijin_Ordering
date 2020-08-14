package com.example.orderandinventorysystem.ui.invoice;

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
import com.example.orderandinventorysystem.Model.ItemOrder;
import com.example.orderandinventorysystem.Model.Sales;
import com.example.orderandinventorysystem.R;
import com.example.orderandinventorysystem.ui.payment.PaymentMain;
import com.example.orderandinventorysystem.ui.sales.SalesOrderMainFragment;
import com.example.orderandinventorysystem.ui.sales.edit_sales_orders;
import com.google.android.material.tabs.TabLayout;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class InvoiceMainFragment extends AppCompatActivity {

    Menu menu1;
    boolean paymentCheck=false;
    ViewPager viewPager;
    TabLayout tabLayout;
    Invoice invoice;
    String intentInvoiceID;
    String latestID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_invoice_main);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Invoice");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        intentInvoiceID = intent.getStringExtra("Invoice");
        InvoiceMainInfo invoiceMainInfo = new InvoiceMainInfo(intentInvoiceID);
        invoiceMainInfo.execute("");

        viewPager = findViewById(R.id.viewpager_all_2);
        tabLayout = findViewById(R.id.tabLayout_2);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.edit_sales: {
                Intent intent = new Intent(this, edit_new_invoice.class);
                intent.putExtra("InvoiceEdit", invoice);
                startActivityForResult(intent, 15);
                return true;
            }

            case R.id.delete: {
                setResult(3);
                DeleteInvoice deleteInvoice = new DeleteInvoice(intentInvoiceID);
                deleteInvoice.execute("");
                this.finish();
                return true;
            }

            case R.id.payment: {
                setResult(4, getIntent().putExtra("Invoice", intentInvoiceID));
                AddPayment addPayment = new AddPayment(intentInvoiceID);
                addPayment.execute("");
                this.finish();
//                Intent intent;
//                intent = new Intent(this, PaymentMain.class);
//                intent.putExtra("Payment", intentInvoiceID);
//                startActivityForResult(intent, 16);
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 5) {

            setResult(3);
            finish();
            Intent intent;
            intent = new Intent(this, InvoiceMainFragment.class);
            intent.putExtra("Invoice", data.getExtras().getString("Invoice"));
            startActivityForResult(intent, 16);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.invoice_main_menu, menu);
        menu1 = menu;
        return true;
    }


    private void setUpViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Invoice_Details(), "DETAILS");
        adapter.addFragment(new Invoice_Payments(), "PAYMENT");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    public class DeleteInvoice extends AsyncTask<String,String,String> {

        String invoiceID;

        DeleteInvoice(String invoiceID) {
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

                    String query = " DELETE FROM INVOICE WHERE INVID ='" + invoiceID + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(query);

                    ArrayList<ItemOrder> ioList = new ArrayList<>();

                    query = " SELECT * FROM ITEMORDER WHERE orderID ='" + invoice.getSalesID() + "'";
                    stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {

                        ioList.add(new ItemOrder(rs.getString(1), rs.getString(2),
                                rs.getString(3), rs.getDouble(4),
                                rs.getDouble(5), rs.getInt(6), rs.getDouble(7)));
                    }

                    for (int i=0; i < ioList.size(); i++) {

                        query = "UPDATE ITEM SET ITEMQUANTITY= ITEMQUANTITY + '" + ioList.get(i).getQuantity() + "' WHERE ITEMID='" + ioList.get(i).getItemID() + "'";
                        stmt = con.createStatement();
                        stmt.executeUpdate(query);

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

        }
    }

    public class InvoiceMainInfo extends AsyncTask<String,String,String> {

        String invoiceID;

        InvoiceMainInfo(String invoiceID) {
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

                    String query = " SELECT * FROM INVOICE WHERE invID ='" + invoiceID + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next()) {

                        invoice = new Invoice(rs.getString(1), rs.getString(2),
                                rs.getString(3), rs.getString(4),
                                rs.getString(5), rs.getDouble(6), rs.getString(7));
                    }

                    query = " SELECT * FROM PAYMENT WHERE invID ='" + invoiceID + "'";
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    if (rs.next()) {

                        paymentCheck = true;
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

            TextView invoice_id = findViewById(R.id.invoice_id);
            invoice_id.setText(invoice.getInvID());

            TextView custName = findViewById(R.id.cust_name);
            custName.setText(invoice.getInvCustName());

            TextView invoice_price = findViewById(R.id.invoice_price);
            invoice_price.setText(String.format("MYR%.2f", invoice.getInvPrice()));

            TextView invoice_status = findViewById(R.id.invoice_status);
            invoice_status.setText(invoice.getInvStatus());

            TextView salesID = findViewById(R.id.salesID);
            salesID.setText(invoice.getSalesID());

            if(paymentCheck) {
                MenuItem menuItem = menu1.findItem(R.id.payment);
                menuItem.setVisible(false);
                menuItem = menu1.findItem(R.id.delete);
                menuItem.setVisible(false);
                menuItem = menu1.findItem(R.id.edit_sales);
                menuItem.setVisible(false);
            }
        }
    }

    public class AddPayment extends AsyncTask<String,String,String> {

        String invoiceID;

        AddPayment(String invoiceID) {
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
                    String query = "SELECT * FROM PAYMENT ORDER BY PAYID DESC LIMIT 1";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    if (rs.next()) {
                        latestID = rs.getString(1);
                        int numID = Integer.parseInt(latestID.substring(3,8)) + 1;
                        if (numID < 10)
                            latestID = "PA-0000" + Integer.toString(numID);
                        else if (numID < 100)
                            latestID = "PA-000" + Integer.toString(numID);
                        else if (numID < 1000)
                            latestID = "PA-00" + Integer.toString(numID);
                        else if (numID < 10000)
                            latestID = "PA-0" + Integer.toString(numID);
                        else if (numID < 100000)
                            latestID = "PA-" + Integer.toString(numID);

                        Log.d("ID", latestID);
                    }

                    else {
                        latestID = "PA-00001";
                        Log.d("ID", latestID);
                    }

                    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                    query = "INSERT INTO PAYMENT VALUES('" + latestID + "', '" + invoice.getInvID() + "', '" +
                            invoice.getInvCustName() + "', '" + currentDate + "', '" + invoice.getInvPrice() + "')";

                    stmt = con.createStatement();
                    stmt.executeUpdate(query);

                    query = "UPDATE INVOICE SET INVSTATUS='PAID' WHERE INVID='" + invoice.getInvID() + "'";
                    stmt = con.createStatement();
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
        protected void onPostExecute(String s) {
        }
    }
}
