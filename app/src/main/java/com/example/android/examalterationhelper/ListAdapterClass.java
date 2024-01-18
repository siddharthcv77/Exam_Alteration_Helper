package com.example.android.examalterationhelper;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
@SuppressWarnings("unchecked")
public class ListAdapterClass extends ArrayAdapter<subjects> {

    Context context;
    List<subjects> valueList;

    public ListAdapterClass(view_timetable view_timetable, int listview, ArrayList deptList) {
        super(view_timetable, 0, deptList);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.layout_items, parent, false);
        }


        final subjects subject = getItem(position);

        TextView textView1 = convertView.findViewById(R.id.textView1);
        TextView textView2 = convertView.findViewById(R.id.textView2);
        TextView textView3 = convertView.findViewById(R.id.textView3);
        TextView textView4 = convertView.findViewById(R.id.textView4);

        textView1.setText(subject.getEroom());
        textView2.setText(subject.getEtime());
        textView3.setText(subject.getEname());
        textView4.setText(subject.getEblock());

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getContext(),update_timetable.class);
//                i.putExtra("Room",subject.getEblock());
//            }
//        });


        return convertView;
    }
}

