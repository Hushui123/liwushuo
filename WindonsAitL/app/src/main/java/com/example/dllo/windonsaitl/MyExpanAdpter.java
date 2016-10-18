package com.example.dllo.windonsaitl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/17.
 */
public class MyExpanAdpter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<String> mArrayListGroup;
    private ArrayList<ArrayList<String>> mArrayListsChild;

    public void setArrayListGroup(ArrayList<String> arrayListGroup) {
        mArrayListGroup = arrayListGroup;
    }

    public MyExpanAdpter(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return mArrayListGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mArrayListsChild.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mArrayListGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mArrayListsChild.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
