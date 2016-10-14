package com.example.dllo.gift.select.raider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/26.
 */
public class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Context context;

    public void setGroup_list(List<String> group_list) {
        this.group_list = group_list;
    }

    private List<String> group_list;

    public void setItem_list(List<List<String>> item_list) {
        this.item_list = item_list;
    }

    private List<List<String>> item_list;

    public void setItem_list2(List<List<String>> item_list2) {
        this.item_list2 = item_list2;
    }

    private List<List<String>> item_list2;

    public MyExpandableListViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return group_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return item_list.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group_list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return item_list.get(groupPosition).get(childPosition);
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
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.expendlist_group, null);

            groupHolder = new GroupHolder();
            groupHolder.txt = (TextView) convertView.findViewById(R.id.txt);
            // groupHolder.img = (ImageView) convertView
            // .findViewById(R.id.img);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        groupHolder.txt.setText(group_list.get(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ItemHolder itemHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.expendlist_item, null);

            itemHolder = new ItemHolder();
            itemHolder.txt = (TextView) convertView.findViewById(R.id.txt);
            itemHolder.img = (ImageView) convertView.findViewById(R.id.img);
            itemHolder.imgRider = (ImageView) convertView.findViewById(R.id.img_raider);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) convertView.getTag();
        }
        childPosition = childPosition + childPosition;
        if (groupPosition == 0) {
            Picasso.with(context).load(item_list2.get(groupPosition).get(childPosition)).into(itemHolder.img);
            Picasso.with(context).load(item_list2.get(groupPosition).get(childPosition + 1)).into(itemHolder.imgRider);
        } else if(groupPosition == 1){
            childPosition = childPosition + 6;
            Picasso.with(context).load(item_list2.get(groupPosition).get(childPosition)).into(itemHolder.img);
            Picasso.with(context).load(item_list2.get(groupPosition).get(childPosition + 1)).into(itemHolder.imgRider);
        } else {
            childPosition = childPosition + 12;
            Picasso.with(context).load(item_list2.get(groupPosition).get(childPosition)).into(itemHolder.img);
            Picasso.with(context).load(item_list2.get(groupPosition).get(childPosition + 1)).into(itemHolder.imgRider);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    class GroupHolder {
        public TextView txt;
        public ImageView img;
    }

    class ItemHolder {
        public ImageView img;
        public TextView txt;
        public ImageView imgRider;
    }
}
