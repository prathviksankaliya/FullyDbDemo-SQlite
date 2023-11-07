package com.shadowtech.fullydbdemo.Data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.PrecomputedText;

import com.shadowtech.fullydbdemo.Model.Contact;
import com.shadowtech.fullydbdemo.Params.params;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {
    public MyDbHandler(Context context) {
        super(context, params.DB_NAME, null, params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        "CREATE TABLE Contacts_table(id INTEGER PRIMARY KEY,name TEXT, phone TEXT, time DATETIME DEFAULT CURRENT_TIMESTAMP)";
        String create = "CREATE TABLE " + params.TABLE_NAME + "("
                + params.KEY_ID + " INTEGER PRIMARY KEY,"
                + params.KEY_NAME + " TEXT,"
                + params.KEY_PHONE + " TEXT,"
                    + params.KEY_TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP" + ")";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(params.KEY_NAME , contact.getName());
        contentValues.put(params.KEY_PHONE , contact.getPhone());
        contentValues.put(params.KEY_TIME , contact.getTime());
        db.insert(params.TABLE_NAME , null , contentValues);
        db.close();
    }
    public List<Contact> getAllContacts()
    {
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String select = "SELECT * FROM "+ params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select , null);

        if(cursor.moveToFirst())
        {
            do {
                Contact contact = new Contact(Integer.parseInt(cursor.getString(0)) ,
                        cursor.getString(1) , cursor.getString(2) , cursor.getString(3));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        return contactList;
    }
    public int updateContact(Contact contact)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(params.KEY_NAME , contact.getName());
        values.put(params.KEY_PHONE , contact.getPhone());
        values.put(params.KEY_TIME , contact.getTime());

        return db.update(params.TABLE_NAME , values , params.KEY_ID + "=?" , new String[]{String.valueOf(contact.getId())});
    }
    public void deleteContact(Contact contact)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(params.TABLE_NAME , params.KEY_NAME + "=?" ,new String[]{String.valueOf(contact.getName())});
        db.close();
    }

}
