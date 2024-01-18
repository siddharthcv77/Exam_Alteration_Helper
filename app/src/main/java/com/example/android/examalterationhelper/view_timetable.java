package com.example.android.examalterationhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.CertificatePinner;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import javax.security.auth.Subject;

public class view_timetable extends AppCompatActivity {

    String val;
    ListView simpleList;
    ArrayList<subjects> deptList = new ArrayList<>();
    //ProgressBar progressBar;
    Response response;
    JSONObject depts=new JSONObject();
    //ProgressBar progressBarSubject;
    String ServerURL = "https://examalthelper.000webhostapp.com/display_timetable.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // Toast.makeText(view_timetable.this,global.date,Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_timetable);
        simpleList = (ListView) findViewById(R.id.listview1);

//        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                Intent intent = new Intent(view_timetable.this, update_timetable.class);
////                intent.putExtra("room", simpleList.getItemAtPosition(i).toString());
////                startActivity(intent);
//                  String item = (String) simpleList.getItemAtPosition(i);
//
//                  Toast.makeText(view_timetable.this,"You selected : " + item,Toast.LENGTH_LONG).show();
////                String temp = adapterView.getItemAtPosition(i).toString();
////                String temp = simpleList.getItemAtPosition(i).toString();
////                Toast.makeText(view_timetable.this,temp,Toast.LENGTH_LONG).show();
//            }
//        });
        //progressBarSubject = (ProgressBar) findViewById(R.id.progressBar);


        new GetHttpResponse().execute();
    }


    class GetHttpResponse extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
//            Toast.makeText(getBaseContext(), "Loading", Toast.LENGTH_LONG).show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                OkHttpClient client = new OkHttpClient();
                client.setProtocols(Arrays.asList(Protocol.HTTP_1_1));
                Request request = new Request.Builder()
                        .url(ServerURL)
                        .build();
                response = client.newCall(request).execute();
                depts = new JSONObject(response.body().string());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            try {
                JSONArray jsonArray = depts.getJSONArray("data");
                JSONObject jsonObject0 = jsonArray.getJSONObject(0);
                JSONObject jsonObject1 = jsonArray.getJSONObject(1);
                JSONObject jsonObject2 = jsonArray.getJSONObject(2);
                JSONObject jsonObject3 = jsonArray.getJSONObject(3);
                JSONArray jsonArray0 = jsonObject0.getJSONArray("room");
                JSONArray jsonArray1 = jsonObject1.getJSONArray("time");
                JSONArray jsonArray2 = jsonObject2.getJSONArray("name");
                JSONArray jsonArray3 = jsonObject3.getJSONArray("block");
                for (int i = 0; i < jsonArray0.length(); i++) {
                    subjects subject = new subjects(jsonArray0.getString(i), jsonArray1.getString(i), jsonArray2.getString(i), jsonArray3.getString(i));
                    deptList.add(subject);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ListAdapterClass listAdapterClass = new ListAdapterClass(view_timetable.this, R.layout.layout_items, deptList);
            simpleList.setAdapter(listAdapterClass);
            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.GONE);
            simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Toast.makeText(view_timetable.this, deptList.get(position).getEroom().toString()+"", Toast.LENGTH_LONG).show();
                    global.eroom = deptList.get(position).getEroom().toString();
                    global.ename = deptList.get(position).getEname().toString();
                    global.etime = deptList.get(position).getEtime().toString();
                    global.eblock = deptList.get(position).getEblock().toString();
                    if(global.username.equals("cb.en.adm001")){
                        startActivity(new Intent(getApplicationContext(),update_timetable.class));
                    }
                }
            });
            super.onPostExecute(aVoid);
        }
    }
}
