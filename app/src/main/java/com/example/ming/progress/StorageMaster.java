package com.example.ming.progress;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ming on 11/29/2015.
 * reuse column names
 * getReadableDatabase
 */
public class StorageMaster {

    private SQLiteDatabase db = mDbHelper.getReadableDatabase();
    private List<GoalItem> items;

    private void LoadGoals() {
        List<GoalItem> goals = new ArrayList<>();
        GoalItem item = new GoalItem("test", 3, 1);
        goals.add(item);

        item = new GoalItem("test", 3, 2);
        goals.add(item);
        items = goals;
    }

    public List<GoalItem> GetGoals() {
        if (items == null)
            LoadGoals();

        return items;
    }

    public void AddGoal(GoalItem item) {
        items.add(item);
    }

    public void RemoveGoal(int id) {
        int index = -1;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id)
                index = i;
        }

        if (index != -1)
            items.remove(index);
    }


    public void Save2Db() {
        for (GoalItem item : items)
            Save2Db(item);
    }

    public void Save2Db(GoalItem item) {

    }

    public void LoadFromDb() {

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                "id",
                "name",
                "unit"
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedEntry.COLUMN_NAME_UPDATED + " DESC";

        Cursor c = db.query(
                "Goal",  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
    }

    public void SaveGoalItem(GoalItem item)
    {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put( , title);
        values.put( , content);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                "Goal",
                FeedEntry.COLUMN_NAME_NULLABLE,
                values);
    }

    public void UpdateGoalItem(GoalItem item)
    {
        // New value for one column
        ContentValues values = new ContentValues();
        values.put("name", item.getName());
        values.put("units", item.getUnits());

        // Which row to update, based on the ID
        String selection = "id" + " LIKE ?";
        String[] selectionArgs = { String.valueOf(item.getId()) };

        int count = db.update(
            "Goal",
            values,
            selection,
            selectionArgs);
    }
}
