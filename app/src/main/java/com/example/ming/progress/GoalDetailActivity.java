package com.example.ming.progress;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class GoalDetailActivity extends android.app.Activity {


    private TextView mTitle;
    private TextView mSessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_fragment);

        int id = this.getIntent().getIntExtra(Constants.PARAM_ID, 0);
        String name = this.getIntent().getStringExtra(Constants.PARAM_NAME);
        int unit = this.getIntent().getIntExtra(Constants.PARAM_UNIT, 0);

        mTitle = (TextView) findViewById(R.id.editText);
        mSessions = (TextView) findViewById(R.id.editText2);

        mTitle.setText(name);
        mSessions.setText(unit);
    }
}
