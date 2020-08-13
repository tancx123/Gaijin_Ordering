package com.example.orderandinventorysystem.ui.sales;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.orderandinventorysystem.ConnectionPhpMyAdmin;
import com.example.orderandinventorysystem.Model.Invoice;
import com.example.orderandinventorysystem.Model.Pack;
import com.example.orderandinventorysystem.Model.Shipment;
import com.example.orderandinventorysystem.R;
import com.example.orderandinventorysystem.ui.invoice.InvoiceMainFragment;
import com.example.orderandinventorysystem.ui.pack.PackageMain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Sales_Invoices extends Fragment {


    Invoice invoice;
    View root;
    boolean invoiceCheck=false;
    ImageButton img;
    String intentSalesID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_sales_order_invoices, container, false);
        Intent intent = getActivity().getIntent();
        intentSalesID = intent.getStringExtra("Sales");
        InvoiceDetail invoiceDetail = new InvoiceDetail(intentSalesID);
        invoiceDetail.execute("");
        img = root.findViewById(R.id.invoice_button);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InvoiceMainFragment.class);
                intent.putExtra("Invoice", invoice.getInvID());
                startActivityForResult(intent, 16);
            }
        });
        return root;
    }

    public class InvoiceDetail extends AsyncTask<String,String,String> {

        String salesID;

        InvoiceDetail(String salesID) {
            this.salesID = salesID;
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

                    String query = " SELECT * FROM INVOICE WHERE salesID ='" + salesID + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next()) {

                        invoice = new Invoice(rs.getString(1), rs.getString(2),
                                rs.getString(3), rs.getString(4), rs.getString(5),
                                rs.getDouble(6), rs.getString(7));
                        invoiceCheck = true;
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

            if (invoiceCheck) {

                TableRow tb1 = root.findViewById(R.id.invRow1);
                tb1.setVisibility(View.VISIBLE);
                tb1 = root.findViewById(R.id.invRow2);
                tb1.setVisibility(View.VISIBLE);
                tb1 = root.findViewById(R.id.invRow3);
                tb1.setVisibility(View.VISIBLE);

                TextView invoice_status = root.findViewById(R.id.invoice_status);
                TextView invoice_id = root.findViewById(R.id.invoice_id);
                TextView sales_order_status_2 = root.findViewById(R.id.sales_order_status_2);
                invoice_status.setText(invoice.getInvStatus());
                invoice_id.setText(invoice.getInvID());
                sales_order_status_2.setText(String.format("%.2f", invoice.getInvPrice()));

                TextView invoice_date = root.findViewById(R.id.invoice_date);
                invoice_date.setText(invoice.getInvDate());

                TextView invoice_due_date = root.findViewById(R.id.invoice_due_date);
                invoice_due_date.setText(invoice.getInvDueDate());

                img.setVisibility(View.VISIBLE);

                TextView click = root.findViewById(R.id.click);
                click.setVisibility(View.VISIBLE);
            }

        }
    }

}
