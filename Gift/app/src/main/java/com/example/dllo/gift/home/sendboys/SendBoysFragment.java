package com.example.dllo.gift.home.sendboys;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.gift.R;
import com.example.dllo.gift.basefragment.BaseFragment;
import com.example.dllo.gift.gson.VolleySingleton;
import com.example.dllo.gift.home.handpick.PickAdpter;
import com.example.dllo.gift.bean.PickListBean;
import com.example.dllo.gift.home.search.OnListViewClickItem;
import com.example.dllo.gift.refreshlishview.MeiTuanListView;

import java.lang.ref.WeakReference;

/**
 * Created by dllo on 16/9/20.
 */
public class SendBoysFragment extends BaseFragment implements MeiTuanListView.OnMeiTuanRefreshListener {
    private PickAdpter adpter;
    private MeiTuanListView mListView;
    private String urlList;
    private InterHandler mInterHandler = new InterHandler(SendBoysFragment.this);
    private final static int REFRESH_COMPLETE = 0;
    private String mNum;


    public static SendBoysFragment newInstance(String bean) {

        Bundle args = new Bundle();
        args.putString("url", bean);
        SendBoysFragment fragment = new SendBoysFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private static class InterHandler extends Handler {
        private WeakReference<SendBoysFragment> mActivity;

        public InterHandler(SendBoysFragment activity) {
            mActivity = new WeakReference<SendBoysFragment>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SendBoysFragment activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case REFRESH_COMPLETE:
                        activity.mListView.setOnRefreshComplete();
                        activity.adpter.notifyDataSetChanged();
                        activity.mListView.setSelection(0);
                        break;
                }
            } else {
                super.handleMessage(msg);
            }
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.sendboys_item;
    }

    @Override
    protected void initdate() {
        mListView = bindView(R.id.listview);
    }

    @Override
    protected void innitView() {
        mListView.setOnMeiTuanRefreshListener(this);

        //http://api.liwushuo.com/v2/channels/拼接id/items?ad=2&gender=1&generation=4&limit=20&set=0
        Bundle args = getArguments();
        mNum = args.getString("url");
        urlList = "http://api.liwushuo.com/v2/channels/" + mNum + "/items_v2?limit=20&ad=2&gender=2&offset=0&generation=2%20HTTP/1.1";
        ListGet();
    }

    public void ListGet() {

        VolleySingleton.addRequest(urlList, PickListBean.class, new Response.Listener<PickListBean>() {

            @Override
            public void onResponse(final PickListBean response) {
                adpter = new PickAdpter(getContext());
                adpter.setArrayList(response);
                mListView.setAdapter(adpter);

                adpter.setOnListViewClickItem(new OnListViewClickItem() {
                    @Override
                    public void click(int position) {
                        String url = response.getData().getItems().get(position).getContent_url();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {

            @Override
            public void run() {
//                try {
//                    Thread.sleep(3000);
//                    // arrayList.add(0, "new data");
                mInterHandler.sendEmptyMessage(REFRESH_COMPLETE);
                urlList = "http://api.liwushuo.com/v2/channels/" + mNum + "/items_v2?limit=20&ad=2&gender=2&offset=0&generation=2%20HTTP/1.1";
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
            }
        }).start();
    }
}
