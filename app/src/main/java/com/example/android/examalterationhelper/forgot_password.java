package com.example.android.examalterationhelper;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class forgot_password extends AppCompatActivity {

    public static EditText username2;
    String un =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        username2=(EditText)findViewById(R.id.edit_fp_un);
    }
    public void send_password(View view){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        un=username2.getText().toString().trim();
//        Log.d("fp", un);
        params.add("username", un);
        client.post("https://examalthelper.000webhostapp.com/send_password.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String ans = new String(responseBody).trim();
                String[] data = ans.split("_", 2);
                String no = data[1].toString().trim();
                String msg = data[0].toString().trim();
                Log.d("number", no);
                Log.d("password123", msg);
                Intent intent=new Intent(getApplicationContext(),forgot_password.class);
                PendingIntent pi= PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(no,null,msg,pi,null);
                Toast.makeText(forgot_password.this,"Password Sent",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
