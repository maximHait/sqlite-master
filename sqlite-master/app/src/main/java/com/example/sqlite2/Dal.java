package com.example.sqlite2;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class Dal extends SQLiteAssetHelper {
    public Dal(Context context) {
        super(context, "my_db.db", null, 1);
    }

    public void addContact(String name, String email, String phone, String street, String city)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO contacts (name, email, phone, street, city) values(?,?,?,?,?)";
        Log.i("1", "WWWWWWWWWWWWWWWWWWWWWWWW");
        SQLiteStatement statement = db.compileStatement(sql_INSERT);
        Log.i("name", name);
        Log.i("email", email);
        Log.i("phone", phone);
        Log.i("street", street);
        Log.i("city", city);
        statement.bindString(1, name);
        statement.bindString(2, email);
        statement.bindString(3, phone);
        statement.bindString(4, street);
        statement.bindString(5, city);
        Log.i("1", "WWWWWWWWWWWWWWWWWWWWWWWW");
        Log.i("statement", statement.toString());
        Log.i("1", "WWWWWWWWWWWWWWWWWWWWWWWW");
        statement.execute();
    }

    public void addContact_withimg(String name, String email, String phone, String street, String city, byte[] img)
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql_INSERT = "INSERT INTO contacts (name, email, phone, street, city, img) values(?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql_INSERT);

        statement.bindString(1, name);
        statement.bindString(2, email);
        statement.bindString(3, phone);
        statement.bindString(4, street);
        statement.bindString(5, city);
        statement.bindBlob(6, img);
        statement.execute();
    }

    public ArrayList<Contact> getAllContacts()
    {
        ArrayList<Contact> ary = new ArrayList<>();
        String st = "select * from contacts";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        while(cursor.moveToNext())
        {
            Contact c = new Contact(cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("email")),
                    cursor.getString(cursor.getColumnIndex("phone")),
                    cursor.getString(cursor.getColumnIndex("street")),
                    cursor.getString(cursor.getColumnIndex("city")));
            c.setContactImage(cursor.getBlob(cursor.getColumnIndex("img")));

            ary.add(c);
        }
        return ary;
    }
}
