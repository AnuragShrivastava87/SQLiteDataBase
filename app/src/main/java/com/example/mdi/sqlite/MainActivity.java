package com.example.mdi.sqlite;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 EditText editname,editsurname,editmarks;
 DatabaseHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editname=(EditText)findViewById(R.id.editText);
        editsurname=(EditText)findViewById(R.id.editText2);
        editmarks=(EditText)findViewById(R.id.editText3);

        mydb = new DatabaseHelper(this);

    }

    public void add(View v)
    {
        boolean is =mydb.insertData(editname.getText().toString(),editsurname.getText().toString(),editmarks.getText().toString());
    if(is==true)
    {
        Toast.makeText(MainActivity.this,"Data Inserted", Toast.LENGTH_LONG).show();

    }
    else
    {
        Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();

    }

    }

    public void show(View v)
    {
        Cursor res= mydb.getAllData();
        if(res.getCount()==0)
        {
            showMessage("Error","No data Found");
            return;
        }
        StringBuffer buffer =new StringBuffer();
        while(res.moveToNext())
        {
            buffer.append("ID:" +res.getString(0)+"\n");
            buffer.append("Name:" +res.getString(1)+"\n");
            buffer.append("Surname:" +res.getString(2)+"\n");
            buffer.append("Marks:" +res.getString(3)+"\n");

        }

        showMessage("Data",buffer.toString());
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

}
