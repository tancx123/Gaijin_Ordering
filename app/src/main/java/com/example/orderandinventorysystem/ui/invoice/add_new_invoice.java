package com.example.orderandinventorysystem.ui.invoice;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.orderandinventorysystem.ConnectionPhpMyAdmin;
import com.example.orderandinventorysystem.Model.Customer;
import com.example.orderandinventorysystem.Model.Invoice;
import com.example.orderandinventorysystem.Model.ItemOrder;
import com.example.orderandinventorysystem.Model.Sales;
import com.example.orderandinventorysystem.R;
import com.example.orderandinventorysystem.ui.sales.SalesOrderMainFragment;
import com.example.orderandinventorysystem.ui.sales.edit_sales_orders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class add_new_invoice extends AppCompatActivity {

    Sales salesEdit;
    EditText dueDate;
    String invoiceLatestID, currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_invoice);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Invoice");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        LatestInvoiceID latestInvoiceID = new LatestInvoiceID();
        latestInvoiceID.execute("");
        Intent intent = getIntent();
        salesEdit = (Sales) intent.getSerializableExtra("SalesEdit");
        TextView tx1 = findViewById(R.id.text_customer_name_input);
        tx1.setText(salesEdit.getSaleCustName());
        TextView text_order_num_input = findViewById(R.id.text_order_num_input);
        text_order_num_input.setText(salesEdit.getSalesID());
        TextView text_invoice_date_input = findViewById(R.id.text_invoice_date_input);
        currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        text_invoice_date_input.setText(currentDate);
        dueDate = findViewById(R.id.text_due_date_input);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save: {

                if(!dueDate.getText().toString().isEmpty()){

                    Invoice invoice = new Invoice(invoiceLatestID, salesEdit.getSalesID(), salesEdit.getSaleCustName(), currentDate, "Confirmed", salesEdit.getSalesPrice(), dueDate.getText().toString());
                    AddInvoice addInvoice = new AddInvoice(invoice);
                    addInvoice.execute("");
                    setResult(4, getIntent().putExtra("Invoice", invoiceLatestID));
                    finish();

                }else{

                    dueDate.setError("Please enter a valid date !");
                }
                //constructor
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    public class LatestInvoiceID extends AsyncTask<String,String,String> {

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

                    String query =  "SELECT * FROM INVOICE ORDER BY INVID DESC LIMIT 1";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    String latestID;

                    if (rs.next()) {
                        latestID = rs.getString(1);
                        int numID = Integer.parseInt(latestID.substring(4,9)) + 1;
                        if (numID < 10)
                            latestID = "INV-0000" + Integer.toString(numID);
                        else if (numID < 100)
                            latestID = "INV-000" + Integer.toString(numID);
                        else if (numID < 1000)
                            latestID = "INV-00" + Integer.toString(numID);
                        else if (numID < 10000)
                            latestID = "INV-0" + Integer.toString(numID);
                        else if (numID < 100000)
                            latestID = "INV-" + Integer.toString(numID);

                        Log.d("ID", latestID);
                    }

                    else {
                        latestID = "INV-00001";
                        Log.d("ID", latestID);
                    }

                    invoiceLatestID = latestID;
                    Log.d("H", invoiceLatestID);
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
        }
    }

    public class AddInvoice extends AsyncTask<String,String,String> {

        Invoice invoice;
        String checkConnection = "";
        boolean isSuccess = false;

        AddInvoice(Invoice invoice) {
            this.invoice = invoice;
        }

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
                    String query = "INSERT INTO INVOICE VALUES('" + invoiceLatestID + "', '" + invoice.getSalesID() + "', '" +
                            invoice.getInvCustName() + "', '" + invoice.getInvDate() + "', '" + invoice.getInvStatus() + "', '" +
                            invoice.getInvPrice() + "','" + invoice.getInvDueDate()+ "')";

                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(query);

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

        }
    }

}
