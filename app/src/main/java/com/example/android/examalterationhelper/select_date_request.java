package com.example.android.examalterationhelper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class select_date_request extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String date;
    EditText time;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date_request);
        time=findViewById(R.id.time_edit);
        Spinner spinner = (Spinner) findViewById(R.id.req_dates_spinner);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.dates_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        date = parent.getItemAtPosition(pos).toString();
        global.req_date = date;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void proceed(View view) {
        global.req_time=time.getText().toString();
//        progressDialog.setMessage("Updating...");
//        progressDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("date",global.req_date);
        params.add("time",global.req_time);
        params.add("username",global.username);
        client.post("https://examalthelper.000webhostapp.com/update_req.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
               // progressDialog.dismiss();
                Toast.makeText(select_date_request.this, new String(responseBody), Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), admin_pg.class);
                startActivity(i);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //progressDialog.dismiss();
                Toast.makeText(select_date_request.this, "Process Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
