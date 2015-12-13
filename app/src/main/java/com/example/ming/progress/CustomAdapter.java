package com.example.ming.progress;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class CustomAdapter extends BaseAdapter {

    Context context;
    List<GoalItem> rowItem;

    CustomAdapter(Context context, List<GoalItem> rowItem) {
        this.context = context;
        this.rowItem = rowItem;
    }

    @Override
    public int getCount() {

        return rowItem.size();
    }

    public void addItem(GoalItem item)
    {
        rowItem.add(item);
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {

        return rowItem.get(position);
    }

    @Override
    public long getItemId(int position) {

        return rowItem.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item, null);
        }
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        TextView txtUnit = (TextView) convertView.findViewById(R.id.unit);

        GoalItem row_pos = rowItem.get(position);
        // setting the image resource and title
        txtName.setText("Name: " + row_pos.getName());
        txtUnit.setText("Unit: " + Integer.toString(row_pos.getUnits()));

        return convertView;

    }

}
