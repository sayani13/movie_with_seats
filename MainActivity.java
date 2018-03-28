package com.example.mahe.tired;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    //public static String EXTRA_MESSAGE="com.example.mahe.tired";
    //public static String EXTRA_MESSAGE1="com.example.mahe.tired";
    public SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqLiteDatabase = openOrCreateDatabase("movie",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Student(name VARCHAR primary key,number INTEGER);");
    }
    public void SubmitClick(View view) {
        EditText Text = findViewById(R.id.editText);
        String name = Text.getText().toString();
        EditText numb = findViewById(R.id.editText2);
        int n = Integer.parseInt(numb.getText().toString());
        // String n2=Integer.toString(n);
        Cursor resultSet = sqLiteDatabase.rawQuery("select  *from Student where name='" + name + "'", null);
        resultSet.moveToFirst();
        if (resultSet.getCount() == 0)
            sqLiteDatabase.execSQL("INSERT INTO Student VALUES('" + name + "'," + n + ");");
        else {
            resultSet.moveToFirst();
            ContentValues cv = new ContentValues();
            cv.put("name", name);
            cv.put("number", n + resultSet.getInt(1));
            sqLiteDatabase.update("Student", cv, "name='" + name + "'", null);
        }
        Toast.makeText(this,"Submitted",Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(this, Main2Activity.class);
        //intent1.putExtra(EXTRA_MESSAGE1, name);
        //intent1.putExtra(EXTRA_MESSAGE1,n2);
        startActivity(intent1);
    }
}