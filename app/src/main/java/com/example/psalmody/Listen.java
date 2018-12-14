package com.example.psalmody;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Listen extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listen);

        listView = findViewById(R.id.listview);
        arrayList = new ArrayList<String>();
        Field[] fields = R.raw.class.getFields();
//
        for (int i =0; i < fields.length; i++){
            arrayList.add(fields[i].getName());
        }
        //arrayAdapter = new ArrayAdapter<String>(Listen.this, android.R.layout.simple_list_item_1, arrayList);
        //listView.setAdapter(arrayAdapter);

    }
}
