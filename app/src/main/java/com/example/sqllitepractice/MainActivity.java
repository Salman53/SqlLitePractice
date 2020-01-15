package com.example.sqllitepractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

DbHelper db;


    EditText editTextName , editTextId , editTextMobile , editTextEmail;
    Button btnSave , btnSearch , btnDelete , btnView , btnUpdate;

    String id;
    String name;
    String email;
    String mobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail =  findViewById(R.id.id_edtEmail);
        editTextId = findViewById(R.id.id_edtId);
        editTextMobile = findViewById(R.id.id_edtMobile);
        editTextName = findViewById(R.id.id_edtName);

        btnSave = findViewById(R.id.id_btnSave);
        btnSearch = findViewById(R.id.id_btnSearch);
        btnDelete = findViewById(R.id.id_btnDelete);
        btnView = findViewById(R.id.id_btnView);
        btnUpdate = findViewById(R.id.id_btnUpdate);

        btnSave.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

        db = new DbHelper(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.id_btnSave:
                name = editTextName.getText().toString();
                email = editTextEmail.getText().toString();
                mobile = editTextMobile.getText().toString();
                if (name.equals("") | email.equals("") | mobile.equals(""))
                {
                    Toast.makeText(MainActivity.this , "Please fill fields" , Toast.LENGTH_SHORT).show();
                }else
                {
                    db.insertStudent(name  , email , mobile);
                    editTextId.setText("");
                    editTextName.setText("");
                    editTextEmail.setText("");
                    editTextMobile.setText("");
                    Toast.makeText(MainActivity.this , "Saved SucessFully" , Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_btnView:
                Intent intent = new Intent(getApplicationContext() , View_sqlite_data.class);
                startActivity(intent);
                break;

            case R.id.id_btnDelete:

                id = editTextId.getText().toString();
                if (id.equals(""))
                {
                    Toast.makeText(MainActivity.this , "Please fill id first" , Toast.LENGTH_SHORT).show();
                }
                else
                {
                    long l = Long.parseLong(id);
                    db.deleteStudent(l);
                    editTextId.setText("");
                    editTextEmail.setText("");
                    editTextMobile.setText("");
                    editTextName.setText("");

                    Toast.makeText(MainActivity.this , "Deleted SuccesFully" , Toast.LENGTH_SHORT).show();
                }
            case R.id.id_btnUpdate:

                id = editTextId.getText().toString();
                name = editTextName.getText().toString();
                email = editTextEmail.getText().toString();
                mobile = editTextMobile.getText().toString();

                if (id.equals("") | name.equals("") | email.equals("") | mobile.equals(""))
                {
                    Toast.makeText(MainActivity.this , "Please fill all fields first" ,Toast.LENGTH_SHORT).show();
                }else
                {
                    long l = Long.parseLong(id);
                    db.updateStudent( l, name , email , mobile);
                    editTextName.setText("");
                    editTextMobile.setText("");
                    editTextEmail.setText("");
                    editTextId.setText("");
                    Toast.makeText(MainActivity.this , "Updated SucessFully" , Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.id_btnSearch:
                id = editTextId.getText().toString();
                if (id.equals(""))
                {
                    Toast.makeText(MainActivity.this ,"Please Fill the Id First" , Toast.LENGTH_SHORT).show();
                }else
                {
                    try {
                        long l1 = Long.parseLong(id);
                        name = db.getName(l1);
                        email = db.getEmail(l1);
                        mobile = db.getMobile(l1);

                        editTextName.setText(name);
                        editTextEmail.setText(email);
                        editTextMobile.setText(mobile);
                        Toast.makeText(MainActivity.this , "Searched SucesssFully" , Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(MainActivity.this , "Id not Available" , Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }


    }
}
