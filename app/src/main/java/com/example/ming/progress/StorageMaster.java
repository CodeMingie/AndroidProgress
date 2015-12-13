package com.example.ming.progress;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ming on 11/29/2015.
 * reuse column names
 * getReadableDatabase
 */
public class StorageMaster {

    private DbHelper helper;
    private SQLiteDatabase db;

    public StorageMaster(Context context)
    {
        helper = new DbHelper(context);
        db = helper.getReadableDatabase();
    }

    public void RemoveGoal(long id) {
        String selection = "id" + " LIKE ?";
        String[] selectionArgs = { String.valueOf(id) };
        db.delete(Constants.DB_Goal, selection, selectionArgs );
    }

    public void Save2Db(GoalItem item) {
        if (item.getId() > 0)
            UpdateGoalItem(item);
        else
            SaveGoalItem(item);
    }

    public List<GoalItem> GetGoals() {

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                Constants.DB_ID,
                Constants.DB_NAME,
                Constants.DB_UNIT
        };

        /*String selection = FeedEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(rowId) };
        */
        // How you want the results sorted in the resulting Cursor
        String sortOrder = "id DESC";

        Cursor c = db.query(
                Constants.DB_Goal,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        return getGoals(c);
    }

    public List<Object[]> cursorToTableRows(Cursor cursor) {
        List<Object[]> result = new ArrayList<Object[]>(cursor.getCount());

        cursor.move(0);
        cursor.moveToNext();
        while (cursor.isAfterLast() == false) {
            Object[] tableRow = new Object[cursor.getColumnCount()];
            for(int i=0; i<cursor.getColumnNames().length; i++) {
                int columnIndex = cursor.getColumnIndex(cursor.getColumnName(i));
                Object columnValue = null;
                String columnName = cursor.getColumnName(i);
                if (columnName.equals(Constants.DB_ID))
                    columnValue = cursor.getLong(columnIndex);
                else if (columnName.equals(Constants.DB_NAME))
                    columnValue = cursor.getString(columnIndex);
                else if (columnName.equals(Constants.DB_UNIT))
                    columnValue = cursor.getInt(columnIndex);

                tableRow[i] = columnValue;
            }
            result.add(tableRow);
            cursor.moveToNext();
        }

        cursor.close();

        return result;
    }

    public List<GoalItem> getGoals(Cursor cursor) {
        List<GoalItem> goals = new ArrayList<GoalItem>();
        List<Object[]> objects = cursorToTableRows(cursor);
        for(Object[] row : objects) {
            int i=0;
            long id = (long) row[0];
            String name = (String) row[1];
            int unit = (int) row[2];
            GoalItem goal = new GoalItem(id, name, unit);
            goals.add(goal);
        }
        return goals;
    }

    public long SaveGoalItem(GoalItem item)
    {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Constants.DB_NAME, item.getName());
        values.put(Constants.DB_UNIT , item.getUnits());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insertOrThrow(
                Constants.DB_Goal,
                null,
                values);
        return newRowId;
    }

    public int UpdateGoalItem(GoalItem item)
    {
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(Constants.DB_NAME, item.getName());
        values.put(Constants.DB_UNIT, item.getUnits());

        // Which row to update, based on the ID
        String selection = "id" + " LIKE ?";
        String[] selectionArgs = { String.valueOf(item.getId()) };

        int count = db.update(
            Constants.DB_Goal,
            values,
            selection,
            selectionArgs);

        return count;
    }
}
