package com.example.dllo.gift.home.handpick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.dllo.gift.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/21.
 */
public class Gallery_adapter extends BaseAdapter {
    Context context;

    List<Object> list;

    public Gallery_adapter(Context context) {
        this.context = context;
        list = new ArrayList<Object>();
    }

    public void setList(List<Object> list) {
        this.list = list;
        this.notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null || convertView.getTag() == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.gallery_item, parent,false);

            viewHolder = new ViewHolder();

            viewHolder.gallery_iv = (ImageView) convertView.findViewById(R.id.gallery_iv);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }



       // viewHolder.gallery_iv.setImageResource(Integer.parseInt(list.get(position).toString()));

        Picasso.with(context).load(list.get(position).toString()).into(viewHolder.gallery_iv);

        return convertView;
    }

    class ViewHolder{
        ImageView gallery_iv;

    }
}
