package com.example.dllo.gift.home.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.gift.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/30.
 */
public class RecordAdpter extends BaseAdapter {
    private ArrayList<String> stringArrayList;
    private Context context;

    public void setOnListViewClickItem(OnListViewClickItem onListViewClickItem) {
        this.onListViewClickItem = onListViewClickItem;
    }

    OnListViewClickItem onListViewClickItem;
    public RecordAdpter(Context context) {
        this.context = context;
    }

    public void setStringArrayList(ArrayList<String> stringArrayList) {
        this.stringArrayList = stringArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return stringArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyListViewDodler myListViewDodler = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.record_item,null);
            myListViewDodler = new MyListViewDodler(convertView);

            convertView.setTag(myListViewDodler);
        } else {
            myListViewDodler = (MyListViewDodler) convertView.getTag();
        }
        myListViewDodler.record.setText(stringArrayList.get(position));

        myListViewDodler.dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListViewClickItem.click(position);
            }
        });
        return convertView;
    }
    class MyListViewDodler{

        private final TextView record;
        private final ImageView dele;

        public MyListViewDodler(View convertView) {
            record = (TextView) convertView.findViewById(R.id.tv_search_record);
            dele = (ImageView) convertView.findViewById(R.id.img_single_dele);
        }
    }
}
