package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity
{   DatabaseHelper db;
    EditText name;
    EditText age;
    EditText hb;
    EditText sugar;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {   super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        db = new DatabaseHelper(this);
        name = (EditText) findViewById(R.id.et_name);
        age = (EditText) findViewById(R.id.et_age);
        hb = (EditText) findViewById(R.id.et_hb);
        sugar = (EditText) findViewById(R.id.et_sugar);
        tv= (TextView)findViewById(R.id.tv_report);
    }

    public void insert (View view)
    {  boolean status = db.insert(name.getText().toString(),
            Integer.parseInt(age.getText().toString()),
            Integer.parseInt(hb.getText().toString()),
            Integer.parseInt(sugar.getText().toString()));

    }
    public void report(View view)
    { Cursor c = db.select();
        if (c.getCount() ==0)
            return;
        StringBuffer buf = new StringBuffer();
        while (c.moveToNext())
        {   buf.append ("Name: "+c.getString(1));
            buf.append (" Age: "+c.getString(2));
            buf.append (" Hemoglobin: "+c.getString(3));
            buf.append (" Sugar: "+c.getString(4));
            buf.append("\n");
        }
        tv.setText(buf);
    }
  
}

