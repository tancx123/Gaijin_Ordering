package com.example.orderandinventorysystem.ui.invoice;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.orderandinventorysystem.ConnectionPhpMyAdmin;
import com.example.orderandinventorysystem.Model.Invoice;
import com.example.orderandinventorysystem.Model.Sales;
import com.example.orderandinventorysystem.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class edit_new_invoice extends AppCompatActivity {

    Invoice invoiceEdit;
    EditText dueDate;
    String invoiceLatestID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_invoice);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Invoice");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        invoiceEdit = (Invoice) intent.getSerializableExtra("InvoiceEdit");
        TextView tx1 = findViewById(R.id.text_customer_name_input);
        tx1.setText(invoiceEdit.getInvCustName());
        TextView text_order_num_input = findViewById(R.id.text_order_num_input);
        text_order_num_input.setText(invoiceEdit.getSalesID());
        TextView text_invoice_date_input = findViewById(R.id.text_invoice_date_input);
        text_invoice_date_input.setText(invoiceEdit.getSalesID());
        dueDate = findViewById(R.id.text_due_date_input);
        dueDate.setText(invoiceEdit.getInvDueDate());
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

                    AddInvoice addInvoice = new AddInvoice(dueDate.getText().toString());
                    addInvoice.execute("");
                    setResult(5, getIntent().putExtra("Invoice", invoiceEdit.getInvID()));
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

    public class AddInvoice extends AsyncTask<String,String,String> {

        String dueDate;
        String checkConnection = "";
        boolean isSuccess = false;

        AddInvoice(String dueDate) {
            this.dueDate = dueDate;
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

                    String query = "UPDATE INVOICE SET INVDUEDATE='" + dueDate + "' WHERE SALESID='" + invoiceEdit.getSalesID() + "'";
                    Log.d("HAHA", invoiceEdit.getSalesID());
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
