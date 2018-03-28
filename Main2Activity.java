package com.example.mahe.tired;


import  android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;

public class Main2Activity extends Activity {
    public SQLiteDatabase sqLiteDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ListView listView = findViewById(R.id.list);
        sqLiteDatabase = openOrCreateDatabase("movie",MODE_PRIVATE,null);
        Cursor resultSet = sqLiteDatabase.rawQuery("select distinct name,number from Student",null);

        resultSet.moveToFirst();
        final ArrayList<String> arrayList = new ArrayList<>();
        final ArrayList<CharSequence> movieName = new ArrayList<>();
        while(!resultSet.isAfterLast())
        {
            String name = resultSet.getString(0);
            int num = resultSet.getInt(1);
            movieName.add(name+"");
            arrayList.add(num+"");
            resultSet.moveToNext();
        }

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_list_item_1, android.R.id.text1, movieName);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String num = arrayList.get(i);
                final String name=movieName.get(i).toString();
                String text = "Name : "+name+"\nseats :"+num;
                Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
            }
        });
        listView.setAdapter(adapter);
    }
}



