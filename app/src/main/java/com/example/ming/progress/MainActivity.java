package com.example.ming.progress;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.Console;

//TODO: very end...consider, soccer balls that user taps
//Menu add or delete or edit. pg 256
//SQL lite database
//Edit not passing values to detail. Test saving to database? Delete.

public class MainActivity extends ActionBarActivity implements SaveEditCallBack {

    private GoalListFragment goalList;
    private GoalDetailFragment newFragment = new GoalDetailFragment();
    private static final int MENU_ADD = 1;
    private static final int MENU_BACK = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        goalList = new GoalListFragment();
        transaction.replace(R.id.contentFragment, goalList);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_layout, menu);
        menu.add(0, MENU_ADD, Menu.NONE, "New").setIcon(android.R.drawable.ic_menu_add).setShowAsAction(1);
        menu.add(0, MENU_BACK, Menu.NONE, "New").setIcon(android.R.drawable.ic_menu_revert).setShowAsAction(1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case MENU_ADD:
                ShowDetail(new GoalItem(0, "", 0));
                break;
            case MENU_BACK:
                ShowList();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ShowDetail(GoalItem item)
    {
        GoalDetailFragment newFragment = new GoalDetailFragment();
        Bundle args = new Bundle();
        args.putLong(Constants.PARAM_ID, item.getId());
        args.putString(Constants.PARAM_NAME, item.getName());
        args.putInt(Constants.PARAM_UNIT, item.getUnits());
        newFragment.setArguments(args);
        Log.d("Arg", item.getName());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contentFragment, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void ShowList()
    {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contentFragment, goalList);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void SaveGoal(GoalItem item) {

        if (item.getId() > 0)
            goalList.SaveGoal(item, false);
        else
            goalList.SaveGoal(item, true);

        ShowList();
    }

    public void DeleteGoal(long id)
    {
        Log.d("delete", "id " + Long.toString(id));
        goalList.DeleteGoal(id);

        ShowList();
    }
}
