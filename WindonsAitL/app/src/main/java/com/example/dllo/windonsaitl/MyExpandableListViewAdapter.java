package com.example.dllo.windonsaitl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/15.
 */
public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;

    private ArrayList<String> arrayListGroup;
    private ArrayList<ArrayList<String>> arrayListsChid;

    public void setArrayListsChid(ArrayList<ArrayList<String>> arrayListsChid) {
        this.arrayListsChid = arrayListsChid;
    }
    public MyExpandableListViewAdapter(Context context) {
        this.context = context;
    }
    public void setArrayListGroup(ArrayList<String> arrayListGroup) {
        this.arrayListGroup = arrayListGroup;
    }

    @Override
    public int getGroupCount() {
        return arrayListGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return arrayListsChid.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return arrayListGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arrayListsChid.get(groupPosition).get(childPosition);
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
        GroupHodle groupHodle = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group,null);
            groupHodle = new GroupHodle(convertView);
            convertView.setTag(groupHodle);
        }
        else {
            groupHodle = (GroupHodle) convertView.getTag();
        }
        groupHodle.textView.setText(arrayListGroup.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHodler childHodler = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.childitem,null);
            childHodler = new ChildHodler(convertView);
            convertView.setTag(childHodler);
        }else {
            childHodler = (ChildHodler) convertView.getTag();
        }
        childHodler.textViewChild.setText(arrayListsChid.get(groupPosition).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHodle {
        private TextView textView ;

        public GroupHodle(View convertView) {
            textView = (TextView) convertView.findViewById(R.id.tv_grouo);
        }
    }
    class ChildHodler{

        private final TextView textViewChild;

        public ChildHodler(View convertView) {
            textViewChild = (TextView) convertView.findViewById(R.id.tv_child);
        }
    }
}
