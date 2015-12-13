package com.example.ming.progress;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GoalListFragment extends ListFragment implements OnItemClickListener {

    private CustomAdapter adapter;
    private List<GoalItem> rowItems;
    private SaveEditCallBack mCallback;
    private StorageMaster sm;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.list_fragment, null, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        getListView().setOnItemClickListener(this);

        if (rowItems == null) {
            sm = new StorageMaster(this.getActivity());
            rowItems = sm.GetGoals();
            adapter = new CustomAdapter(getActivity(), rowItems);
            setListAdapter(adapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        GoalItem item = (GoalItem) adapter.getItem(position);
        mCallback.ShowDetail(item);
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

    public void SaveGoal(GoalItem item, boolean isNew)
    {
        if (isNew) {
            long itemId = sm.SaveGoalItem(item);
            item.setId(itemId);
        }
        else
            sm.UpdateGoalItem(item);

        UpdateList(item, isNew);

        adapter.notifyDataSetChanged();
    }

    public void UpdateList(GoalItem item, boolean isNew)
    {
        if (isNew)
            rowItems.add(item);
        else {
            for (GoalItem i : rowItems)
            {
                if (i.getId() == item.getId())
                {
                    i.setName(item.getName());
                    i.setUnits(item.getUnits());
                }
            }
        }
    }

    public void DeleteGoal(long id)
    {
        sm.RemoveGoal(id);

        List<Object> objs;
        Iterator<GoalItem> i = rowItems.iterator();
        while (i.hasNext()) {
            GoalItem o = i.next();
            if (o.getId() == id)
            i.remove();
        }

        adapter.notifyDataSetChanged();
    }
}