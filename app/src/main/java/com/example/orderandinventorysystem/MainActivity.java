package com.example.orderandinventorysystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.cust_order_but);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//              Doregister doregister = new Doregister();
//              doregister.execute("");
            }
        });
    }

    public class Doregister extends AsyncTask<String,String,String>
    {

        String z="";
        boolean isSuccess=false;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {

            ConnectionPhpMyAdmin connectionClass = new ConnectionPhpMyAdmin();
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {

                        String query=" select * from children where children_id='child_01'";
                        Statement stmt = con.createStatement();
                        ResultSet rs=stmt.executeQuery(query);

                        while (rs.next()) {
                            Log.d("HI", rs.getString(1));
                        }

                        z = "Register successfull";
                        isSuccess=true;


                    }
                }
                catch (Exception ex)
                {
                    Log.d("HI", ex.toString());
                    isSuccess = false;
                    z = "Exceptions"+ex;
                }

            return z;
        }

        @Override
        protected void onPostExecute(String s) {

            if(isSuccess) {
                startActivity(new Intent(MainActivity.this,CustomerOrder.class));

            }

        }
    }

}