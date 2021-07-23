package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{   DatabaseHelper (Context context)
{
    super(context, "Health.db",null,1); }

    @Override
    public void onCreate (SQLiteDatabase sqLiteDatabase)
    { sqLiteDatabase.execSQL("CREATE TABLE LAB ( PATID integer PRIMARY KEY AUTOINCREMENT, PANAME TEXT, AGE INTEGER, HM INTEGER, SUGAR INTEGER );");
    }

    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1)
    {   sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LAB ");
        onCreate(sqLiteDatabase);
    }
    public boolean insert (String name, int age, int hb, int sug)
    {   SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("PANAME", name);
        cv.put("AGE", age);
        cv.put("HM",hb);
        cv.put("SUGAR",sug);
        long status = db.insert("LAB",null,cv);
        if (status == -1)
            return false;
        else
            return true;
    }

    public Cursor select()
    {   SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM LAB", null);
        return cr;
    }
}
