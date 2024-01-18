package com.example.android.examalterationhelper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class register_fac extends AppCompatActivity {

    EditText ed_username1,ed_password1,ed_name1,ed_email1,ed_phno1;
    public  static  String dummy_username=null,dummy_password=null;

    String str_username1,str_password1,str_email1,str_phno1,str_name1;
    String url = "https://examalthelper.000webhostapp.com/register.php";
    private ProgressDialog progressDialog;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    public static char first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_fac);
        ed_username1 = findViewById(R.id.un);
        ed_password1 = findViewById(R.id.ps);
        ed_email1 = findViewById(R.id.em);
        ed_phno1 = findViewById(R.id.phno);
        ed_name1 = findViewById(R.id.ed_name);

        saveLoginCheckBox=(CheckBox)findViewById(R.id.checkbox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            ed_username1.setText(loginPreferences.getString("username", ""));
            ed_password1.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }
    }
    public void reg(View view){

        str_username1 = ed_username1.getText().toString();
        str_password1 = ed_password1.getText().toString();
        str_email1 = ed_email1.getText().toString();
        str_phno1 = ed_phno1.getText().toString();
        str_name1 = ed_name1.getText().toString();
        global_test.run = str_username1;
        global_test.rps = str_password1;
        global_test.remi = str_email1;
        global_test.rphno = str_phno1;
        global_test.rnm = str_name1;
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        final AsyncHttpClient client = new AsyncHttpClient();
        final RequestParams params = new RequestParams();

        params.add("username", str_username1);
        params.add("password", str_password1);
        params.add("name", str_name1);
        params.add("phone_number", str_phno1);
        params.add("email", str_email1);

        client.post("https://examalthelper.000webhostapp.com/register.php", params, new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        progressDialog.dismiss();
                        Toast.makeText(register_fac.this, new String(responseBody), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });

    }
}
