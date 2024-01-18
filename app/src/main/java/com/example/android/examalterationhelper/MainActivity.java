package com.example.android.examalterationhelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText ed_username,ed_password,et_username,et_password;
    public  static  String dummy_username=null,dummy_password=null;
    public  static String pass_username=null,pass_password = null;
    String str_username,str_password;
    String url = "https://examalthelper.000webhostapp.com/login.php";
    private ProgressDialog progressDialog;
    private CheckBox remember;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private Boolean saveLogin;
    public static char first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_username = findViewById(R.id.username_input);
        ed_password = findViewById(R.id.password_input1);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        et_username = findViewById(R.id.username_input);
        et_password = findViewById(R.id.password_input1);
        remember    = findViewById(R.id.checkbox);

        if(sharedPreferences.getBoolean(KEY_REMEMBER, false))
            remember.setChecked(true);
        else
            remember.setChecked(false);

        et_username.setText(sharedPreferences.getString(KEY_USERNAME,""));
        et_password.setText(sharedPreferences.getString(KEY_PASS,""));

        remember.setOnCheckedChangeListener(this);

//        saveLoginCheckBox=(CheckBox)findViewById(R.id.checkbox);
//        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
//        loginPrefsEditor = loginPreferences.edit();
//
//        saveLogin = loginPreferences.getBoolean("saveLogin", false);
//        if (saveLogin == true) {
//            ed_username.setText(loginPreferences.getString("username", ""));
//            ed_password.setText(loginPreferences.getString("password", ""));
//            saveLoginCheckBox.setChecked(true);
//        }


    }


    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }


    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        managePrefs();
    }


    public void afterTextChanged(Editable editable) {

    }


    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        managePrefs();
    }

    private void managePrefs(){
        if(remember.isChecked()){
            editor.putString(KEY_USERNAME,ed_username.getText().toString().trim());
            editor.putString(KEY_PASS, ed_password.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        }else{
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);//editor.putString(KEY_PASS,"");
            editor.remove(KEY_USERNAME);//editor.putString(KEY_USERNAME, "");
            editor.apply();
        }
    }

    public void Login(View view){
        global_test.username = ed_username.toString();
        global_test.password = ed_password.toString();

//        Toast.makeText(MainActivity.this, "test2", Toast.LENGTH_SHORT).show();

        if(ed_username.getText().toString().equals("")){
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        }
        else if(ed_password.getText().toString().equals("")){
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }
        else{


            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Please Wait..");
            str_username = ed_username.getText().toString().trim();
            str_password = ed_password.getText().toString().trim();

           progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    pass_username=str_username.toString();
                    pass_password=str_password.toString();
                    global.username=pass_username;
                    global.password=pass_password;
                    progressDialog.dismiss();
                    ed_username.setText("");
                    ed_password.setText("");
//                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    String s = new String(response.trim());
                    if(s.equals("success")){
                        Toast.makeText(MainActivity.this, "login successful", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),admin_pg.class));
                    }
                    if(s.equals("failed")){
                        Toast.makeText(MainActivity.this, "invalid credentials", Toast.LENGTH_LONG).show();
                    }
                }
            },new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "failed   ", Toast.LENGTH_LONG).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("username",str_username);
                    params.put("password",str_password);
                    return params;

                }
            };

//            Volley.newRequestQueue(this).add(request);

            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(request);




        }
    }

    public void forgot_password(View view){
        startActivity(new Intent(getApplicationContext(),forgot_password.class));
    }


}
