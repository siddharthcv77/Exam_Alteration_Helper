package com.example.android.examalterationhelper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.okhttp.ResponseBody;

import cz.msebera.android.httpclient.Header;

public class update_timetable extends AppCompatActivity {

    TextView blocke,roome,timee;
    EditText namee;
    String datee;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_timetable);
        blocke = (TextView) findViewById(R.id.block_edit);
        roome = (TextView) findViewById(R.id.room_edit);
        namee = (EditText) findViewById(R.id.name_edit);
        timee = (TextView) findViewById(R.id.time_edit);
        datee = global.date;
        disp();
    }

    private void disp() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("date",global.date);
        params.add("room",global.eroom);
        params.add("time",global.etime);
        params.add("block",global.eblock);
        client.post("https://examalthelper.000webhostapp.com/set_tt_update.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                Toast.makeText(update_timetable.this,global.date,Toast.LENGTH_LONG).show();
                String ans = new String(responseBody);
//                Toast.makeText(update_timetable.this,ans,Toast.LENGTH_LONG).show();
                String[] data1 = ans.split("_",4);
//                Toast.makeText(update_timetable.this,data1[0],Toast.LENGTH_LONG).show();
                roome.setText(""+data1[0]);
                timee.setText(""+data1[1]);
                namee.setText(""+data1[2]);
                blocke.setText(""+data1[3]);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public  void update(View view){
        String blk = blocke.getText().toString();
        String rm = roome.getText().toString();
        String nm = namee.getText().toString();
        String tm = timee.getText().toString();
        global_test.block = blk;
        global_test.room = rm;
        global_test.uname = nm;
        global_test.utime = tm;
//        progressDialog.setMessage("Updating...");
//        progressDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("room",rm);
        params.add("time",tm);
        params.add("block",blk);
        params.add("name",nm);
        client.post("https://examalthelper.000webhostapp.com/update_tt.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                progressDialog.dismiss();
                Toast.makeText(update_timetable.this,"Records Updated",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), admin_pg.class);
                startActivity(i);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

}
