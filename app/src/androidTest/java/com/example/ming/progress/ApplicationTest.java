package com.example.ming.progress;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.ApplicationTestCase;

import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        createApplication();
    }

    public void testMyApp() throws Exception {

        StorageMaster m = new StorageMaster(getContext());
        GoalItem item = new GoalItem(0, "test1", 2);
        long returnId = m.SaveGoalItem(item);
        assertTrue(Long.toString(returnId), returnId > 0);
        //DbHelper helper = new DbHelper(getContext());
        //SQLiteDatabase db = helper.getWritableDatabase();

        item.setId(returnId);
        int count = m.UpdateGoalItem(item);
        assertTrue(count == 1);
        List<GoalItem> items = m.GetGoals();
    }
}