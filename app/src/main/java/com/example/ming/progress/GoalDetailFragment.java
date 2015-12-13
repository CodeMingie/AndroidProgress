package com.example.ming.progress;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GoalDetailFragment extends Fragment implements View.OnClickListener{

    private TextView mTitle;
    private TextView mSessions;
    private long id;
    private String name;
    private int unit;
    private SaveEditCallBack mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        Button btnSave = (Button) view.findViewById(R.id.btSave);
        Button btnDelete = (Button) view.findViewById(R.id.btDelete);
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        mTitle = (EditText) view.findViewById(R.id.editText);
        mSessions = (EditText) view.findViewById(R.id.editText2);

        if (id != 0)
        {
            mTitle.setText(name);
            mSessions.setText(Integer.toString(this.unit));
            this.id = id;
        }

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (SaveEditCallBack) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SaveEditCallBack");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        long id = 0;
        String name = "";
        int unit = -1;

        try {
            Bundle b = getArguments();
            id = b.getLong(Constants.PARAM_ID);
            name = b.getString(Constants.PARAM_NAME);
            Log.d("get Arg2", name + " " + Long.toString(id));
            unit = b.getInt(Constants.PARAM_UNIT);
        }
        catch (Exception e)
        {
            Toast.makeText(this.getActivity().getBaseContext(), e.getMessage(), Toast.LENGTH_LONG);
        }

        this.id = id;
        this.name = name;
        this.unit = unit;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btSave: {
                int unit = 0;

                try {
                    unit = Integer.parseInt(mSessions.getText().toString());
                }
                catch (NumberFormatException e)
                {
                    unit = 0;
                }

                String name = "";

                try {
                    name = mTitle.getText().toString();
                }
                catch (Exception e)
                {
                    name = "";
                }

                if (name == "")
                    mTitle.setError("Name required.");

                if (unit <= 0)
                    mSessions.setError("Positive number required.");

                if (name != "" && unit > 0)
                    mCallback.SaveGoal(new GoalItem(this.id, name, unit));

                break;
            }
            case R.id.btDelete:
            {
                if (this.id > 0)
                    mCallback.DeleteGoal(this.id);
                break;
            }
            default:
                //Do nothing
        }
    }
}