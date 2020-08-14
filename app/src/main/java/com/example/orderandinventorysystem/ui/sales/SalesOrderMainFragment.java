package com.example.orderandinventorysystem.ui.sales;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.orderandinventorysystem.Adapter.SectionsPagerAdapter;
import com.example.orderandinventorysystem.ConnectionPhpMyAdmin;
import com.example.orderandinventorysystem.Model.Invoice;
import com.example.orderandinventorysystem.Model.ItemOrder;
import com.example.orderandinventorysystem.Model.Sales;
import com.example.orderandinventorysystem.ui.invoice.InvoiceMainFragment;
import com.example.orderandinventorysystem.ui.invoice.add_new_invoice;
import com.example.orderandinventorysystem.ui.pack.PackageMain;
import com.google.android.material.tabs.TabLayout;
import com.example.orderandinventorysystem.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SalesOrderMainFragment extends AppCompatActivity {

    Menu menu1;
    ViewPager viewPager;
    TabLayout tabLayout;
    ArrayList<ItemOrder> ioList;
    Sales sales;
    boolean packCheck=false, invoiceCheck=false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sales_order_main);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sales Order");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        String intentSalesID = intent.getStringExtra("Sales");
        SalesMainInfo salesMainInfo = new SalesMainInfo(intentSalesID);
        salesMainInfo.execute("");


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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sales_order_main_menu, menu);
        menu1 = menu;
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.download_sales: {

                pdfGenerate();
                return true;
            }

            case R.id.edit_sales: {
                Intent intent = new Intent(this, edit_sales_orders.class);
                intent.putExtra("SalesEdit", sales);
                startActivityForResult(intent, 10);
                return true;
            }

            case R.id.create_package: {

                AddPackage addPackage = new AddPackage();
                addPackage.execute("");
                return true;
            }

            case R.id.create_invoice: {

                Intent intent = new Intent(this, add_new_invoice.class);
                intent.putExtra("SalesEdit", sales);
                startActivityForResult(intent, 11);
                return true;
            }

            case R.id.delete: {
                DeleteSales deleteSales = new DeleteSales(sales.getSalesID());
                deleteSales.execute("");
                this.finish();
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            finish();

        }

        else if (resultCode == 3) {

            Intent intent = new Intent(this, SalesOrderMainFragment.class);
            intent.putExtra("Sales", sales.getSalesID());
            finish();
            startActivity(intent);
        }

        else if (resultCode == 4) {

            Intent intent = new Intent(this, SalesOrderMainFragment.class);
            intent.putExtra("Sales", sales.getSalesID());
            finish();
            startActivity(intent);

            intent = new Intent(this, InvoiceMainFragment.class);
            intent.putExtra("Invoice", data.getExtras().getString("Invoice"));
            startActivityForResult(intent, 16);
        }
    }

    public void pdfGenerate() {

        Bitmap bmp, scaledbmp;
        int pageWidth = 1200, pageHeight = 1960;
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.gaijin);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 250, 125, false);

        PdfDocument salesPdf = new PdfDocument();
        Paint paint = new Paint();

        PdfDocument.PageInfo pageInfo1 = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create();
        PdfDocument.Page page1 = salesPdf.startPage(pageInfo1);
        Canvas canvas = page1.getCanvas();

        //draw Gaijin Logo Title
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
        paint.setTextSize(45);
        canvas.drawText("Gaijin Company", 925, 325, paint);

        //draw Gaijin Logo
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.inventory);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 200,125,false);
        canvas.drawBitmap(scaledbmp,810,150, paint);

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(60);
        canvas.drawText("Sales Order", 925, 505, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(25);
        canvas.drawText("From : ", 100,185, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setTextSize(20);
        canvas.drawText("Gaijin Company", 100,225, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(20);
        canvas.drawText("No.4 Taman Nanas,", 100,265, paint);        // Remember change Y - axis

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(20);
        canvas.drawText("14535 Kedah, Malaysia", 100,305, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(20);
        canvas.drawText("Email : gaijin_888@gmail.com", 100,345, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(20);
        canvas.drawText("Contact : 012-4428888", 100,385, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(25);
        canvas.drawText("To : ", 100,465, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setTextSize(20);
        canvas.drawText("Abu Muhammad", 100,505, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(20);
        canvas.drawText("25, Jalan Pisang", 100,545, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(20);
        canvas.drawText("55302, Melaka Malaysia", 100,585, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(20);
        canvas.drawText("Email : abu_0821@gmail.com", 100,625, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(20);
        canvas.drawText("Contact: 0123938472", 100,665, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(20);
        canvas.drawText("Contact: 0123938472", 100,665, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(20);
        canvas.drawText("Order ID : ", 800,585, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(20);
        canvas.drawText("Order Date : ", 800,625, paint);

        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(20);
        canvas.drawText("Sales Person : ", 800,665, paint);

        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(20);
        canvas.drawText("OD-00001", 1050,585, paint);

        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(20);
        canvas.drawText("14 AUG 2020", 1050,625, paint);

        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(20);
        canvas.drawText("SpongeBob", 1050,665, paint);

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(25);
        canvas.drawText("Authorized By", 273,1230, paint);

        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        paint.setTextSize(25);
        canvas.drawText("Received By", 880,1230, paint);

        //Draw Line
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        canvas.drawLine(100, 1200, 450, 1200, paint);
        canvas.drawLine(700, 1200, 1050, 1200, paint);

        salesPdf.finishPage(page1);

        File file = new File (getExternalFilesDir(null),"SalesOrder.pdf");

        try {
            salesPdf.writeTo(new FileOutputStream(file));
            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(),
                    Toast.LENGTH_LONG).show();
        }

        salesPdf.close();
    }

    private void setUpViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new Sales_Details(), "DETAILS");
        adapter.addFragment(new Sales_Packages(), "PACKAGE");
        adapter.addFragment(new Sales_Invoices(), "INVOICE");

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        setResult(2);
        finish();
        return true;
    }

    public class SalesMainInfo extends AsyncTask<String,String,String> {

        String salesID;
        Sales salesDB;
        ArrayList <ItemOrder> ioListDB= new ArrayList<>();

        public Sales getSales() {
            return salesDB;
        }

        public ArrayList<ItemOrder> getIoList() {
            return ioList;
        }

        SalesMainInfo(String salesID) {
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

                        salesDB = new Sales(rs.getString(1), rs.getString(2),
                                rs.getString(3), rs.getString(4),
                                rs.getDouble(6), rs.getString(5));
                    }

                    query = " SELECT * FROM INVOICE WHERE salesID ='" + salesID + "'";
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    if (rs.next()) {

                        invoiceCheck = true;

                    }

                    query = " SELECT * FROM ITEMORDER WHERE orderID ='" + salesID + "'";
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {

                        ioListDB.add(new ItemOrder(rs.getString(1), rs.getString(2),
                                rs.getString(3), rs.getDouble(4),
                                rs.getDouble(5), rs.getInt(6), rs.getDouble(7)));
                    }

                    query = " SELECT * FROM PACKAGE WHERE salesID ='" + salesID + "'";
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(query);
                    if (rs.next()) {

                        packCheck = true;
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

            sales = salesDB;
            ioList = ioListDB;

            TextView salesID = findViewById(R.id.sales_order_id);
            salesID.setText(sales.getSalesID());

            TextView custName = findViewById(R.id.cust_name);
            custName.setText(sales.getSaleCustName());

            TextView salesPrice = findViewById(R.id.sales_order_price);
            salesPrice.setText(String.format("MYR%.2f", sales.getSalesPrice()));

            TextView salesDate = findViewById(R.id.sales_order_date);
            salesDate.setText(sales.getSalesDate());

            TextView salesStatus = findViewById(R.id.sales_order_status);
            salesStatus.setText(sales.getSalesStatus());

            if(sales.getSalesStatus().equals("Removed")) {

                MenuItem menuItem = menu1.findItem(R.id.create_package);
                menuItem.setVisible(false);
                MenuItem menuItem2 = menu1.findItem(R.id.delete);
                menuItem2.setVisible(false);
                menuItem2 = menu1.findItem(R.id.edit_sales);
                menuItem2.setVisible(false);
                menuItem = menu1.findItem(R.id.create_invoice);
                menuItem.setVisible(false);
            }

            if(packCheck) {
                MenuItem menuItem = menu1.findItem(R.id.create_package);
                menuItem.setVisible(false);
                MenuItem menuItem2 = menu1.findItem(R.id.delete);
                menuItem2.setVisible(false);
                menuItem2 = menu1.findItem(R.id.edit_sales);
                menuItem2.setVisible(false);
            }

            if(invoiceCheck) {

                MenuItem menuItem = menu1.findItem(R.id.create_invoice);
                menuItem.setVisible(false);
                MenuItem menuItem2 = menu1.findItem(R.id.delete);
                menuItem2.setVisible(false);
                menuItem = menu1.findItem(R.id.edit_sales);
                menuItem.setVisible(false);
            }


        }
    }

    public class DeleteSales extends AsyncTask<String,String,String> {

        String salesID;

        DeleteSales(String salesID) {
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

                    String query = " UPDATE SALES SET SALESSTATUS='Removed' WHERE SALESID ='" + salesID + "'";
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
        protected void onPostExecute(String s) {

        }
    }

    public class AddPackage extends AsyncTask<String,String,String> {

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

                    String query = "SELECT * FROM PACKAGE ORDER BY PACKID DESC LIMIT 1";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    String latestID;

                    if (rs.next()) {
                        latestID = rs.getString(1);
                        int numID = Integer.parseInt(latestID.substring(3,8)) + 1;
                        if (numID < 10)
                            latestID = "PK-0000" + Integer.toString(numID);
                        else if (numID < 100)
                            latestID = "PK-000" + Integer.toString(numID);
                        else if (numID < 1000)
                            latestID = "PK-00" + Integer.toString(numID);
                        else if (numID < 10000)
                            latestID = "PK-0" + Integer.toString(numID);
                        else if (numID < 100000)
                            latestID = "PK-" + Integer.toString(numID);

                    }

                    else {
                        latestID = "PK-00001";
                    }

                    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                    query = " INSERT INTO PACKAGE VALUES('" + latestID + "','" + currentDate + "','"+ sales.getSalesID() + "','PACKED')" ;
                    stmt = con.createStatement();
                    stmt.executeUpdate(query);

                    for (int i=0; i < ioList.size(); i++) {

                        query = "UPDATE ITEM SET ITEMQUANTITYPHY= ITEMQUANTITYPHY - '" + ioList.get(i).getQuantity() + "' WHERE ITEMID='" + ioList.get(i).getItemID() + "'";
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
            finish();
            startActivity(getIntent());
        }
    }
}
