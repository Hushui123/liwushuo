package com.example.dllo.gift.select.single;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.gift.R;
import com.example.dllo.gift.basefragment.BaseFragment;
import com.example.dllo.gift.home.VolleySingleton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/26.
 */
public class SingleFragment extends BaseFragment {

    private LeftAdpter adpter;
    private ListView listViewLeft;
    private ExpandableListView listViewRight;
    private String url;
    private ArrayList<List<String>> item_list2;
    private ArrayList<List<String>> item_list;
    private ArrayList<String> group_list;
    private SingleBean singleBean;
    private boolean isScroll = true;
    private ArrayList<Boolean> flagArray =new ArrayList<>();
    private RightAdpter adapter;

    @Override
    protected int setLayout() {
        return R.layout.select_single_layout;
    }

    @Override
    protected void initdate() {
        listViewLeft = bindView(R.id.left_lv_single);
        listViewRight = bindView(R.id.right_lv_single);
    }

    @Override
    protected void innitView() {
        url = "http://api.liwushuo.com/v2/item_categories/tree";
        singleLeft();

        listViewLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isScroll = false;

                adpter.setSelected(position);
                listViewRight.setSelectedGroup(position);

            }
        });

        listViewRight.setOnScrollListener(new AbsListView.OnScrollListener() {


            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (listViewRight.getLastVisiblePosition() == (listViewRight.getCount() - 1)) {
                            listViewLeft.setSelection(ListView.FOCUS_DOWN);
                        }

                        // 判断滚动到顶部
                        if (listViewRight.getFirstVisiblePosition() == 0) {
                            listViewLeft.setSelection(0);
                        }

                        break;
                }
            }


            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                if (is) {
                    //listViewLeft.setSelection(firstVisibleItem);
//
//                }

            }
        });
    }

    private void singleLeft() {
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                singleBean = gson.fromJson(response, SingleBean.class);
                List<SingleBean.DataBean.CategoriesBean> categories = singleBean.getData().getCategories();
                for (int i = 0; i < categories.size(); i++) {
                    categories.get(i).setChecked(false);
                }
                categories.get(0).setChecked(true);
                adpter = new LeftAdpter(getContext());
                adpter.setSingleBeanArrayList(categories);
                listViewLeft.setAdapter(adpter);
                Assgiment(categories);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInStance().addRequest(stringRequest);
    }

    public void Assgiment(List<SingleBean.DataBean.CategoriesBean> categories) {

        adapter = new RightAdpter(mcontext);

        adapter.setSingleBeen(categories);
        listViewRight.setGroupIndicator(null);
        listViewRight.setAdapter(adapter);

        for (int i = 0; i < categories.size(); i++) {
            listViewRight.expandGroup(i);
        }

        listViewRight.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }
}



