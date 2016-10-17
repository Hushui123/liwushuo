package com.example.dllo.gift.hot.dailyhot;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.gift.R;
import com.example.dllo.gift.basefragment.BaseFragment;
import com.example.dllo.gift.home.VolleySingleton;
import com.example.dllo.gift.home.handpick.WvActivity;
import com.example.dllo.gift.home.search.OnListViewClickItem;
import com.example.dllo.gift.refreshlishview.MeiTuanListView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by dllo on 16/9/22.
 */
public class DailyHotFragment extends BaseFragment implements MeiTuanListView.OnMeiTuanRefreshListener {

    private MeiTuanListView listview_hot;
    private String url;

    private final static int REFRESH_COMPLETE = 0;
    private DailyAdpter adpter;

    // 刷新定义
    private InterHandler mInterHandler = new InterHandler(DailyHotFragment.this);
    private ImageView img_hot;


    public static DailyHotFragment newInstance(String bean) {

        Bundle args = new Bundle();
        args.putString("url",bean);
        DailyHotFragment fragment = new DailyHotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static class InterHandler extends Handler {
        private WeakReference<DailyHotFragment> mActivity;

        public InterHandler(DailyHotFragment activity) {
            mActivity = new WeakReference<DailyHotFragment>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            DailyHotFragment activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case REFRESH_COMPLETE:
                        activity.listview_hot.setOnRefreshComplete();
                        activity.adpter.notifyDataSetChanged();
                        activity.listview_hot.setSelection(0);
                        break;
                }
            } else {
                super.handleMessage(msg);
            }
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.daily_hot;
    }

    @Override
    protected void initdate() {


        listview_hot = bindView(R.id.listview_hot);

        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.night_item, null);
        img_hot = (ImageView) view1.findViewById(R.id.img_hot);
        listview_hot.addHeaderView(view1);

    }

    @Override
    protected void innitView() {

        Bundle args = getArguments();
        String urll = args.getString("url");
        url = "http://api.liwushuo.com/v2/ranks_v2/ranks/" + urll + "?limit=20&offset=0HTTP/1.1";

        listview_hot.setOnMeiTuanRefreshListener(this);
        DailGet();
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {

            @Override
            public void run() {
//                try {
//                    //Thread.sleep(3000);
//                    // arrayList.add(0, "new data");
                   mInterHandler.sendEmptyMessage(REFRESH_COMPLETE);
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
            }
        }).start();
    }

    public void DailGet() {
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                final DailyHotBean dailyHotBean = gson.fromJson(response, DailyHotBean.class);
                Picasso.with(getContext()).load(dailyHotBean.getData().getCover_image()).into(img_hot);
                ArrayList<DailyHotBean> list = new ArrayList<>();
                list.add(dailyHotBean);
                adpter = new DailyAdpter(getContext());
                adpter.setArrayList(list);
                listview_hot.setAdapter(adpter);

                adpter.setOnListViewClickItem(new OnListViewClickItem() {
                    int finalPosition = 0;
                    @Override
                    public void click(int position) {
                        if (position == 0) {
                             finalPosition = position;

                        }else {
                             finalPosition = position - 1;

                        }
                        Intent it = new Intent(getContext(), WvActivity.class);
                        String url = dailyHotBean.getData().getItems().get(finalPosition).getUrl();

                        String url1 = url.replace("hawaii", "www");
                        Log.d("DailyHotFragment", url1);
                        it.putExtra("webview", url1);
                        startActivity(it);
                    }
                });
                adpter.setOnLsitViewClickItemLeft(new OnLsitViewClickItemLeft() {
                    @Override
                    public void click(int finalPosition) {
                        Intent it = new Intent(getContext(), WvActivity.class);
                        String url = dailyHotBean.getData().getItems().get(finalPosition).getUrl();

                        String url1 = url.replace("hawaii", "www");

                        it.putExtra("webview", url1);
                        startActivity(it);
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


}
