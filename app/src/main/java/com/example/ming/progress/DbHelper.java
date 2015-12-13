package com.example.ming.progress;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ming on 12/4/2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "ProgressData.db";
    private static final String TABLE_GOAL = "Goal";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + TABLE_GOAL + "(id INTEGER PRIMARY KEY, name STRING, unit INTEGER)";

    public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOAL);
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GOAL);
        onCreate(db);
    }

}
