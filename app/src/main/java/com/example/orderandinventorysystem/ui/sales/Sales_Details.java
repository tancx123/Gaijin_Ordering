package com.example.orderandinventorysystem.ui.sales;

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
import com.example.orderandinventorysystem.Model.ItemOrder;
import com.example.orderandinventorysystem.Model.Pack;
import com.example.orderandinventorysystem.Model.Sales;
import com.example.orderandinventorysystem.Model.Shipment;
import com.example.orderandinventorysystem.R;
import com.example.orderandinventorysystem.ui.item.ItemListAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Sales_Details extends Fragment {

    boolean shipCheck=false;
    ArrayList<ItemOrder> ioList;
    Sales sales;
    View root;
    double total;
    RecyclerView recyclerView;
    ItemOrderListAdapter adapter;
    boolean shipmentCheck = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_sales_order_details, container, false);

        Intent intent = getActivity().getIntent();
        String intentSalesID = intent.getStringExtra("Sales");
        SalesDetails salesDetails = new SalesDetails(intentSalesID);
        salesDetails.execute("");
        ioList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.itemLine_2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ItemOrderListAdapter(getContext(), ioList);
        return root;
    }



    public class SalesDetails extends AsyncTask<String,String,String> {

        String salesID;

        public Sales getSales() {
            return sales;
        }

        public ArrayList<ItemOrder> getIoList() {
            return ioList;
        }

        SalesDetails(String salesID) {
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

                    String query = " SELECT * FROM SALES WHERE salesID ='" + salesID + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next()) {

                        sales = new Sales(rs.getString(1), rs.getString(2),
                                rs.getString(3), rs.getString(4),
                                rs.getDouble(6), rs.getString(5));
                    }

//                    query = " SELECT * FROM PACKAGE WHERE salesID ='" + salesID + "'";
//                    stmt = con.createStatement();
//                    rs = stmt.executeQuery(query);
//                    if (rs.next()) {
//
//                        packCheck = true;
//                    }


                    String packID="";

                    query = " SELECT * FROM PACKAGE WHERE salesID ='" + salesID + "'";
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    if (rs.next()) {

                        packID = rs.getString(1);
                    }

                    if (packID != "") {

                        query = " SELECT * FROM SHIPMENT WHERE packID ='" + packID + "'";
                        stmt = con.createStatement();
                        rs = stmt.executeQuery(query);
                        if (rs.next()) {

                            shipCheck=true;
                        }
                    }

                    query = " SELECT * FROM ITEMORDER WHERE orderID ='" + salesID + "'";
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {

                        ioList.add(new ItemOrder(rs.getString(1), rs.getString(2),
                                rs.getString(3), rs.getDouble(4),
                                rs.getDouble(5), rs.getInt(6)));
                        total += rs.getDouble(5);
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
            TextView subtotalTV = root.findViewById(R.id.sales_order_sub_total);
            TextView totalTV = root.findViewById(R.id.sales_order_total);
            subtotalTV.setText(String.format("MYR%.2f", total));
            totalTV.setText(String.format("MYR%.2f", total));
            TextView salesStatus = root.findViewById(R.id.sales_order_status_2);
            salesStatus.setText(sales.getSalesStatus());
            recyclerView.setAdapter(adapter);
            if(shipCheck) {
                TextView shipment_status = root.findViewById(R.id.shipment_status);
                shipment_status.setText("Shipped");
            }
        }
    }

}
