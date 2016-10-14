package com.example.dllo.gift.select.raider;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.gift.R;
import com.example.dllo.gift.basefragment.BaseFragment;
import com.example.dllo.gift.home.VolleySingleton;
import com.example.dllo.gift.home.search.OnListViewClickItem;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/26.
 */
public class RaidersFragment extends BaseFragment {
    private ExpandableListView expandableListView;
    private List<String> group_list;
    private List<List<String>> item_list;
    private List<List<String>> item_list2;
    private View viewHeader;
    private RecyclerView raiderRv;
    private ArrayList<RaiderHeaderBean> beanArrayList;

    @Override
    protected int setLayout() {
        return R.layout.select_raider_layout;
    }

    @Override
    protected void initdate() {
        expandableListView = bindView(R.id.expendlist);
        View viewHeader = LayoutInflater.from(getContext()).inflate(R.layout.raider_header_layout,null);
        raiderRv = (RecyclerView) viewHeader.findViewById(R.id.raider_rv);
        expandableListView.addHeaderView(viewHeader);

    }

    @Override
    protected void innitView() {
        beanArrayList = new ArrayList<>();
        HeaderGet();
        RaiderGet();
    }

    public void HeaderGet(){
        String urlHeader = "http://api.liwushuo.com/v2/columns?limit=20&offset=0";
        StringRequest stringRequest = new StringRequest(urlHeader, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                RaiderHeaderBean bean = gson.fromJson(response,RaiderHeaderBean.class);
                beanArrayList.add(bean);
                RaiderHeaderAdpter adpter = new RaiderHeaderAdpter(getContext());
                adpter.setRaiderHeaderBean(beanArrayList);
                raiderRv.setAdapter(adpter);

                GridLayoutManager manager = new GridLayoutManager(getContext(),3, LinearLayoutManager.HORIZONTAL,false);
                raiderRv.setLayoutManager(manager);

                adpter.setOnListViewClickItem(new OnListViewClickItem() {
                    @Override
                    public void click(int position) {
                        Toast.makeText(mcontext, "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInStance().addRequest(stringRequest);
    }
    public void RaiderGet() {
        String url = "http://api.liwushuo.com/v2/channel_groups/all";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                RaidersBean bean = gson.fromJson(response, RaidersBean.class);
                group_list = new ArrayList<String>();
                item_list = new ArrayList<List<String>>();
                for (int i = 0; i < bean.getData().getChannel_groups().size(); i++) {
                    group_list.add(bean.getData().getChannel_groups().get(i).getName());
                    item_list.add(group_list);

                }

                List<String> tmp_list = new ArrayList<>();
                item_list2 = new ArrayList<List<String>>();
                for (int j = 0; j < 3; j++) {
                    for (int i = 0; i < 6; i++) {
                        tmp_list.add(bean.getData().getChannel_groups().get(j).getChannels().get(i).getCover_image_url());
                        item_list2.add(tmp_list);

                    }
                }

                Assgiment();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInStance().addRequest(stringRequest);
    }

    public void Assgiment() {


        MyExpandableListViewAdapter adapter = new MyExpandableListViewAdapter(getContext());
        adapter.setGroup_list(group_list);
        adapter.setItem_list(item_list);
        adapter.setItem_list2(item_list2);
        expandableListView.setGroupIndicator(null);
        expandableListView.setAdapter(adapter);

        for (int i = 0; i < group_list.size(); i++) {
            expandableListView.expandGroup(i);
        }

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                Toast.makeText(mcontext, "groupPosition:" + groupPosition, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(mcontext, "childPosition:" + childPosition, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
