package com.example.dllo.gift.home.handpick;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.home.search.OnListViewClickItem;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/21.
 */
public class PickAdpter extends BaseAdapter {
    Context context;
    OnListViewClickItem onListViewClickItem;
    PickListBean arrayList;
    public void setOnListViewClickItem(OnListViewClickItem onListViewClickItem) {
        this.onListViewClickItem = onListViewClickItem;
    }

    public PickAdpter(Context context) {
        this.context = context;
    }

    public void setArrayList(PickListBean arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.getData().getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.getData().getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHdler viewHdler = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.pick_item, null);
            viewHdler = new ViewHdler(convertView);
            convertView.setTag(viewHdler);
        } else {
            viewHdler = (ViewHdler) convertView.getTag();
        }

        ImageView iv_cover_image_url = (ImageView) convertView.findViewById(R.id.iv_cover_image_url);
        ImageView iv_avatar_url = (ImageView) convertView.findViewById(R.id.iv_avatar_url);

        viewHdler.tv_title_long.setText(arrayList.getData().getItems().get(position).getTitle());
        if (arrayList.getData().getItems().get(position).getColumn() == null) {
            viewHdler.tv_title_short.setText("");
            viewHdler.tv_category.setText("");
        } else {
            viewHdler.tv_title_short.setText(arrayList.getData().getItems().get(position).getColumn().getTitle());
            viewHdler.tv_category.setText(arrayList.getData().getItems().get(position).getColumn().getCategory());
        }

        viewHdler.tv_nickname.setText(arrayList.getData().getItems().get(position).getAuthor().getNickname());
        Picasso.with(context).load(arrayList.getData().getItems().get(position).getAuthor().getAvatar_url()).into(iv_avatar_url);
        Picasso.with(context).load(arrayList.getData().getItems().get(position).getCover_image_url()).into(iv_cover_image_url);
        viewHdler.tv_likes_count.setText(arrayList.getData().getItems().get(position).getLikes_count() + "");

        iv_cover_image_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListViewClickItem.click(position);
            }
        });

        return convertView;
    }

    class ViewHdler  {


        private final TextView tv_category;
        private final TextView tv_title_short;
        private final TextView tv_nickname;
        private final TextView tv_title_long;
        private final TextView tv_likes_count;
        private final ImageView img_heart;
        private final LinearLayout ll;


        public ViewHdler(View convertView) {
            tv_category = (TextView) convertView.findViewById(R.id.tv_category);
            tv_title_short = (TextView) convertView.findViewById(R.id.tv_title_short);
            tv_nickname = (TextView) convertView.findViewById(R.id.tv_nickname);
            tv_title_long = (TextView) convertView.findViewById(R.id.tv_title_long);
            tv_likes_count = (TextView) convertView.findViewById(R.id.tv_likes_count1);
            img_heart = (ImageView) convertView.findViewById(R.id.img_heart);
            ll = (LinearLayout) convertView.findViewById(R.id.ll_pick_item);
        }


    }
}
