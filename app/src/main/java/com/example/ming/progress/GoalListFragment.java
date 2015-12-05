package com.example.ming.progress;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GoalListFragment extends ListFragment implements OnItemClickListener {

    CustomAdapter adapter;
    private List<GoalItem> rowItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.list_fragment, null, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        rowItems = StorageMaster.GetGoals();
        adapter = new CustomAdapter(getActivity(), rowItems);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        GoalItem item = (GoalItem) adapter.getItem(position);
        //Toast.makeText(getActivity(), position, Toast.LENGTH_SHORT)
        //        .show();

        Intent i = new Intent(getActivity(), GoalDetailActivity.class);
        i.putExtra(GoalDetailActivity.EXTRA_PARAM_ID, item.getId());
        startActivity(i);
    }

}