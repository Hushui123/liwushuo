package com.example.dllo.gift.select.single;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.gift.R;
import com.example.dllo.gift.bean.SingleBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/9/28.
 */
public class RightAdpter extends BaseExpandableListAdapter {
    private List<List<String>> item_list;
    private List<String> group_list;
    private List<List<String>> item_list2;
    private Context context;
    private ItemHolder itemHolder;
    private List<SingleBean.DataBean.CategoriesBean> categoriesBeanList;

    public void setSingleBeen(List<SingleBean.DataBean.CategoriesBean> categoriesBeanList) {
        this.categoriesBeanList = categoriesBeanList;
    }

    public RightAdpter(Context context) {
        this.context = context;
    }

//    public void setGroup_list(List<String> group_list) {
//        this.group_list = group_list;
//    }
//
//    public void setItem_list(List<List<String>> item_list) {
//        this.item_list = item_list;
//    }
//
//    public void setItem_list2(List<List<String>> item_list2) {
//        this.item_list2 = item_list2;
//    }

    @Override
    public int getGroupCount() {
        return categoriesBeanList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {


        return categoriesBeanList.get(groupPosition).getSubcategories().size() / 3 + 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categoriesBeanList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return categoriesBeanList.get(groupPosition).getSubcategories().get(childPosition);
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
        GroupHolder groupHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.single_right_head_item, null);
            groupHolder = new GroupHolder();
            groupHolder.txt = (TextView) convertView.findViewById(R.id.single_tv_namer);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        groupHolder.txt.setText(categoriesBeanList.get(groupPosition).getName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ItemHolder itemHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.single_right_item, null);
            itemHolder = new ItemHolder();

            itemHolder.nameOne = (TextView) convertView.findViewById(R.id.single_tv_right);
            itemHolder.nameTwo = (TextView) convertView.findViewById(R.id.single_tv_right_two);
            itemHolder.nameThree = (TextView) convertView.findViewById(R.id.single_tv_right_three);

            itemHolder.imgOne = (ImageView) convertView.findViewById(R.id.single_img_right);
            itemHolder.imgTwo = (ImageView) convertView.findViewById(R.id.single_img_right_two);
            itemHolder.imgThree = (ImageView) convertView.findViewById(R.id.single_img_right_three);
            convertView.setTag(itemHolder);
        } else {
            itemHolder = (ItemHolder) convertView.getTag();
        }


        childPosition = childPosition * 3;
        SingleBean.DataBean.CategoriesBean bean = categoriesBeanList.get(groupPosition);

        if (childPosition < bean.getSubcategories().size()) {
            itemHolder.nameOne.setText(bean.getSubcategories()
                    .get(childPosition).getName());
            Picasso.with(context).load(bean.getSubcategories().get(childPosition).getIcon_url()).into(itemHolder.imgOne);
        }
        if (childPosition < bean.getSubcategories().size() - 2) {

            itemHolder.nameTwo.setText(bean.getSubcategories()
                    .get(childPosition + 1).getName());
            Picasso.with(context).load(bean.getSubcategories().get(childPosition + 1).getIcon_url()).into(itemHolder.imgTwo);

        }
        if (childPosition < bean.getSubcategories().size() - 3) {
            itemHolder.nameThree.setText(bean.getSubcategories()
                    .get(childPosition + 2).getName());

            Picasso.with(context).load(bean.getSubcategories().get(childPosition + 2).getIcon_url()).into(itemHolder.imgThree);
        }


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        public TextView txt;
    }

    class ItemHolder {
        public TextView nameOne;
        public ImageView imgOne;
        public TextView nameTwo;
        public ImageView imgTwo;
        public TextView nameThree;
        public ImageView imgThree;
    }
}
