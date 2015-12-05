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

    public static final String EXTRA_PARAM_ID = "detail:_id";
    private TextView mTitle;
    private TextView mSessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_fragment);

        int id = this.getIntent().getIntExtra(EXTRA_PARAM_ID, 0);

        mTitle = (TextView) findViewById(R.id.editText);
        mSessions = (TextView) findViewById(R.id.editText2);

        mTitle.setText("Test Title");
        mSessions.setText("7");
    }
}
