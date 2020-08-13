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
import com.example.orderandinventorysystem.Model.Pack;
import com.example.orderandinventorysystem.Model.Shipment;
import com.example.orderandinventorysystem.R;
import com.example.orderandinventorysystem.ui.pack.PackageMain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Sales_Packages extends Fragment {

    Shipment shipment;
    Pack pack;
    View root;
    boolean packCheck=false, shipCheck=false;
    ImageButton img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_sales_order_packages, container, false);
        Intent intent = getActivity().getIntent();
        String intentSalesID = intent.getStringExtra("Sales");
        PackageDetail packageDetail = new PackageDetail(intentSalesID);
        packageDetail.execute("");

        img = root.findViewById(R.id.delivery_button);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PackageMain.class);
                intent.putExtra("Package", pack.getPackID());
                startActivityForResult(intent, 3);
            }
        });

        return root;
    }

    public class PackageDetail extends AsyncTask<String,String,String> {

        String salesID;

        PackageDetail(String salesID) {
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

                    String query = " SELECT * FROM PACKAGE WHERE salesID ='" + salesID + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs.next()) {

                        pack = new Pack(rs.getString(1), rs.getString(2),
                                rs.getString(3), rs.getString(4));
                        packCheck = true;
                    }

                    if (packCheck) {

                        query = " SELECT * FROM SHIPMENT WHERE packID ='" + pack.getPackID() + "'";
                        stmt = con.createStatement();
                        rs = stmt.executeQuery(query);
                        if (rs.next()) {

                            shipment = new Shipment(rs.getString(1), rs.getString(2),
                                    rs.getString(3), rs.getString(4));
                            shipCheck = true;
                        }
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

            if (packCheck) {

                TableRow tb1 = root.findViewById(R.id.deliveryDate);
                tb1.setVisibility(View.VISIBLE);
                TextView packStatus = root.findViewById(R.id.sales_order_status_2);
                TextView packDate = root.findViewById(R.id.packDate);
                packStatus.setText(pack.getPackStatus());
                packDate.setText(pack.getPackDate());
                img.setVisibility(View.VISIBLE);
                TextView click = root.findViewById(R.id.click);
                click.setVisibility(View.VISIBLE);
            }

            if (shipCheck) {

                TableRow tb2 = root.findViewById(R.id.shipDate);
                tb2.setVisibility(View.VISIBLE);
                TextView shipDate = root.findViewById(R.id.shipmentDate);
                shipDate.setText(shipment.getShipDate());
                TextView click = root.findViewById(R.id.click);
                click.setVisibility(View.VISIBLE);
            }
        }
    }

}
