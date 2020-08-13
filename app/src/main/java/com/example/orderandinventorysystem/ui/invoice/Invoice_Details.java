package com.example.orderandinventorysystem.ui.invoice;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderandinventorysystem.ConnectionPhpMyAdmin;
import com.example.orderandinventorysystem.Model.Invoice;
import com.example.orderandinventorysystem.Model.ItemOrder;
import com.example.orderandinventorysystem.Model.Sales;
import com.example.orderandinventorysystem.R;
import com.example.orderandinventorysystem.ui.sales.ItemOrderListAdapter;
import com.example.orderandinventorysystem.ui.sales.Sales_Details;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Invoice_Details extends Fragment {

    boolean shipCheck=false;
    ArrayList<ItemOrder> ioList;
    Invoice invoice;
    View root;
    double total=0,subtotal=0, discountTotal=0;
    RecyclerView recyclerView;
    ItemOrderListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_invoice_details, container, false);

        Intent intent = getActivity().getIntent();
        String intentInvoiceID = intent.getStringExtra("Invoice");
        InvoiceDetails invoiceDetails = new InvoiceDetails(intentInvoiceID);
        invoiceDetails.execute("");
        ioList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.itemLine_2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ItemOrderListAdapter(getContext(), ioList);

        return root;

    }

    public class InvoiceDetails extends AsyncTask<String,String,String> {

        String invoiceID;

        InvoiceDetails(String invoiceID) {
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

//                    query = " SELECT * FROM PACKAGE WHERE salesID ='" + salesID + "'";
//                    stmt = con.createStatement();
//                    rs = stmt.executeQuery(query);
//                    if (rs.next()) {
//
//                        packCheck = true;
//                    }

                    query = " SELECT * FROM ITEMORDER WHERE orderID ='" + invoice.getSalesID() + "'";
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {

                        ioList.add(new ItemOrder(rs.getString(1), rs.getString(2),
                                rs.getString(3), rs.getDouble(4),
                                rs.getDouble(5), rs.getInt(6), rs.getDouble(7)));
                        subtotal += rs.getDouble(5);
                        discountTotal += (rs.getDouble(5) * rs.getDouble(7)/100);
                        total = subtotal - discountTotal;
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
            TextView invoice_date = root.findViewById(R.id.invoice_date);
            invoice_date.setText(invoice.getInvDate());
            TextView invoice_due_date = root.findViewById(R.id.invoice_due_date);
            invoice_due_date.setText(invoice.getInvDueDate());
            TextView dis = root.findViewById(R.id.discountTotal);
            dis.setText(String.format("- MYR%.2f", discountTotal));
            TextView sales_order_sub_total = root.findViewById(R.id.sales_order_sub_total);
            sales_order_sub_total.setText(String.format("MYR%.2f", subtotal));
            TextView sales_order_total = root.findViewById(R.id.sales_order_total);
            sales_order_total.setText(String.format("MYR%.2f", total));

            recyclerView.setAdapter(adapter);

        }
    }

}
