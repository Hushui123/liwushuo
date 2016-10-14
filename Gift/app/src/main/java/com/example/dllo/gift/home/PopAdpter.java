package com.example.dllo.gift.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.gift.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/29.
 */
public class PopAdpter extends BaseAdapter {
    Context context;
    ArrayList<MyHomeBean> myHomeBeanArrayList;

    public void setMyHomeBeanArrayList(ArrayList<MyHomeBean> myHomeBeanArrayList) {
        this.myHomeBeanArrayList = myHomeBeanArrayList;
    }

    public PopAdpter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return myHomeBeanArrayList.get(0).getData().getChannels().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHdolerOne myViewHdolerOne =null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.pop_item,null);
            myViewHdolerOne = new MyViewHdolerOne(convertView);
            convertView.setTag(myViewHdolerOne);
        } else {
            myViewHdolerOne = (MyViewHdolerOne) convertView.getTag();
        }
        myViewHdolerOne.textView.setText(myHomeBeanArrayList.get(0).getData().getChannels().get(position).getName());

        return convertView;
    }

    class MyViewHdolerOne {

        private final TextView textView;

        public MyViewHdolerOne(View convertView) {
            textView = (TextView) convertView.findViewById(R.id.tv_pop);
        }

    }
}
