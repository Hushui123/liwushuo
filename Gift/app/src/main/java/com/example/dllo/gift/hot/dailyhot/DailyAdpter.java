package com.example.dllo.gift.hot.dailyhot;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.bean.DailyHotBean;
import com.example.dllo.gift.home.search.OnListViewClickItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/23.
 */
public class DailyAdpter extends BaseAdapter {
    private Context context;
    private ArrayList<DailyHotBean> arrayList;
    private OnListViewClickItem onListViewClickItem;
    private OnLsitViewClickItemLeft onLsitViewClickItemLeft;

    public void setOnLsitViewClickItemLeft(OnLsitViewClickItemLeft onLsitViewClickItemLeft) {
        this.onLsitViewClickItemLeft = onLsitViewClickItemLeft;
    }

    public void setOnListViewClickItem(OnListViewClickItem onListViewClickItem) {
        this.onListViewClickItem = onListViewClickItem;
    }

    public void setArrayList(ArrayList<DailyHotBean> arrayList) {
        this.arrayList = arrayList;
    }

    public DailyAdpter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        Log.d("DailyAdpter", "arrayList.get(0).getData().getItems().size():" + arrayList.get(0).getData().getItems().size());
        return arrayList.get(0).getData().getItems().size() / 2 - 1;
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


        ViewHodler viewHodler = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.dailyheader, null);
            viewHodler = new ViewHodler(convertView);
            convertView.setTag(viewHodler);
        } else {
            viewHodler = (ViewHodler) convertView.getTag();
        }
//        Log.d("DailyAdpter", "position:" + position);
        ImageView ItemImage = (ImageView) convertView.findViewById(R.id.ItemImage);
        ImageView itemImage1 = (ImageView) convertView.findViewById(R.id.ItemImage1);


        position = position + position;


        final int finalPosition = position;
        itemImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListViewClickItem.click(finalPosition);
                //   Toast.makeText(context, "finalPosition:" + finalPosition, Toast.LENGTH_SHORT).show();
            }
        });

        ItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLsitViewClickItemLeft.click(finalPosition);
//                Toast.makeText(context, "zhesjo", Toast.LENGTH_SHORT).show();
            }
        });
        Picasso.with(context).load(arrayList.get(0).getData()
                .getItems().get(position).getCover_image_url().toString()).into(ItemImage);
        Picasso.with(context).load(arrayList.get(0).getData()
                .getItems().get(position + 1).getCover_image_url()).into(itemImage1);

        viewHodler.itemText.setText(arrayList.get(0).getData().getItems().get(position).getShort_description());
        viewHodler.Daily_name.setText(arrayList.get(0).getData().getItems().get(position).getName());
        viewHodler.price.setText(arrayList.get(0).getData().getItems().get(position).getPrice());

        viewHodler.itemText1.setText(arrayList.get(0).getData().getItems().get(position + 1).getShort_description());
        viewHodler.Daily_name1.setText(arrayList.get(0).getData().getItems().get(position + 1).getName());
        viewHodler.Daily_price1.setText(arrayList.get(0).getData().getItems().get(position + 1).getPrice());


        return convertView;

    }


    class ViewHodler {


        private final TextView itemText;
        private final TextView Daily_name;
        private final TextView price;
        private final TextView itemText1;
        private final TextView Daily_name1;
        private final TextView Daily_price1;

        public ViewHodler(View convertView) {

            itemText = (TextView) convertView.findViewById(R.id.ItemText);
            Daily_name = (TextView) convertView.findViewById(R.id.Daily_name);
            price = (TextView) convertView.findViewById(R.id.Daily_price);

            itemText1 = (TextView) convertView.findViewById(R.id.ItemText1);
            Daily_name1 = (TextView) convertView.findViewById(R.id.Daily_name1);
            Daily_price1 = (TextView) convertView.findViewById(R.id.Daily_price1);
        }
    }
}
