package com.example.ming.progress;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ming on 12/4/2015.
 */
public class mDbHelper  extends SQLiteOpenHelper {

    private final String SQL_CREATE_ENTRIES = "CREATE TABLE Goal(id INTEGER PRIMARY KEY, name STRING, units INTEGER) IF NOT EXISTS Goal";

    public mDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
