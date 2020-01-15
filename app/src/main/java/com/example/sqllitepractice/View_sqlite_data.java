package com.example.sqllitepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class View_sqlite_data extends AppCompatActivity {

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    ListView listView;
    DbHelper db;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sqlite_data);



         db = new DbHelper(this);
         textView = findViewById(R.id.id_textView);
        //listItem = new ArrayList<>();
        String data = db.getData();
        textView.setText(data);
        textView.setMovementMethod(new ScrollingMovementMethod());


    }

    /*private void viewData() {

        Cursor cursor = db.viewData();

        if (cursor.getCount() == 0) {

            Toast.makeText(this, "No data to show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                //Log.i("message","Data got");

                listItem.add(cursor.getString(0));
                listItem.add(cursor.getString(1));
                listItem.add(cursor.getString(2));
                listItem.add(cursor.getString(3));// Adding data received to a Listview
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            listView.setAdapter(adapter);

        }
    }*/
}
