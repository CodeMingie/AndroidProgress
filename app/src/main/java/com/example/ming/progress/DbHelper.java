package com.example.ming.progress;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ming on 12/4/2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    private final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS Goal(id INTEGER PRIMARY KEY, name STRING, units INTEGER)";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ProgressData.db";

    public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        //db.execSQL("DROP TABLE IF EXITS " + DATABASE_TABLE);
        //onCreate(db);
    }

}
