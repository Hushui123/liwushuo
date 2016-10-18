package com.example.dllo.gift.select.single;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.bean.SingleBean;

import java.util.List;

/**
 * Created by dllo on 16/9/28.
 */
public class LeftAdpter extends BaseAdapter {
    private List<SingleBean.DataBean.CategoriesBean> categoriesBeanList;
    private Context context;

    public LeftAdpter(Context context) {
        this.context = context;

    }

    public void setSelected(int position){
        for (SingleBean.DataBean.CategoriesBean bean : categoriesBeanList) {
            bean.setChecked(false);
        }
        categoriesBeanList.get(position).setChecked(true);
        notifyDataSetChanged();
    }

    public void setSingleBeanArrayList(List<SingleBean.DataBean.CategoriesBean> categoriesBeanList) {
        this.categoriesBeanList = categoriesBeanList;
    }

    @Override
    public int getCount() {
        return categoriesBeanList.size();
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
        MyViewHodle myViewHodle = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.single_left_item, null);

            myViewHodle = new MyViewHodle(convertView);
            convertView.setTag(myViewHodle);
        } else {
            myViewHodle = (MyViewHodle) convertView.getTag();
        }
        myViewHodle.name.setText(categoriesBeanList.get(position).getName());

        if (categoriesBeanList.get(position).isChecked()) {
            myViewHodle.name.setBackgroundColor(Color.rgb(255, 255, 255));
        } else {
            myViewHodle.name.setBackgroundColor(Color.GRAY);
        }
        return convertView;
    }

    class MyViewHodle {

        private final TextView name;

        public MyViewHodle(View convertView) {
            name = (TextView) convertView.findViewById(R.id.single_tv_name);
        }

    }
}
