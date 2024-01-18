package com.example.android.examalterationhelper;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class select_exam_date extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_exam_date);
        Spinner spinner = (Spinner) findViewById(R.id.dates_spinner);
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
        global.date = date;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void proceed(View view) {
        startActivity(new Intent(getApplicationContext(),view_timetable.class));
    }
}
