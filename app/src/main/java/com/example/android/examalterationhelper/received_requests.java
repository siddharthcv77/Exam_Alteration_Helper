package com.example.android.examalterationhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;


import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class received_requests extends AppCompatActivity {
    TextView tev1;
    TextView tev2;
    TextView tev3;
    TextView tev4;
    static ListView reqlist;
    static ArrayList<requests> request_list = new ArrayList<>();
    static String url = "https://examalthelper.000webhostapp.com/requests_rcvd.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_requests);
        tev1=findViewById(R.id.tv1);
        tev2=findViewById(R.id.tv2);
        tev3=findViewById(R.id.tv3);
        tev4=findViewById(R.id.tv4);
//        reqlist=(ListView) findViewById(R.id.listview2);
        displaylist();
    }

    public void displaylist(){
        log.v("datazz= ","yes");
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("https://examalthelper.000webhostapp.com/requests_rcvd.php", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String results = new String(responseBody);
                String[] data = results.split(" ");
                int len = data.length;
                log.v("datazz= ",results+" len= "+len+" data= "+data[4]);
                int k=1;
//                Toast.makeText(received_requests.this, "data="+data[0]+" "+data[1], Toast.LENGTH_SHORT).show();
                for(int i=3;i<len;i++){
                    if(i%3==2){
                        if(k==1){
                            tev1.setText(data[i]);
                            k++;
                        }
                        else
                        if(k==2){
                            tev2.setText(data[i]);
                            k++;
                        }
                        else
                        if(k==3){
                            tev3.setText(data[i]);
                            k++;
                        }
                        else
                        if(k==4){
                            tev4.setText(data[i]);
                            k++;
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

}
