package com.example.dllo.gift.home.handpick;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.gift.R;
import com.example.dllo.gift.basefragment.BaseFragment;
import com.example.dllo.gift.gson.VolleySingleton;
import com.example.dllo.gift.home.luotu.OneActivity;
import com.example.dllo.gift.home.luotu.TaotaoActivity;
import com.example.dllo.gift.home.luotu.WvLuoboActivity;
import com.example.dllo.gift.home.search.OnListViewClickItem;
import com.example.dllo.gift.refreshlishview.MeiTuanListView;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/20.
 */
public class HomeSelectFragment extends BaseFragment implements MeiTuanListView.OnMeiTuanRefreshListener {

    private MeiTuanListView mListView;
    private final static int REFRESH_COMPLETE = 0;

    // 刷新定义
    private InterHandler mInterHandler = new InterHandler(HomeSelectFragment.this);
    private PickAdpter adpter;
    private ArrayList<String> arrayList;

    // 轮播图定义
    Gallery main_gallery;
    LinearLayout main_lin;
    List<Object> li;
    Gallery_adapter gallery_adapter;
    int current_circle = 0;
    Runnable timeadv;
    int count;
    Handler handler = new Handler();
    private View view1;
    private String url;
    private LinearLayout mGallery;
    private String url1;
    private String urlList;
    private ArrayList<Integer> target_id;
    private ArrayList<String> title;

    private static class InterHandler extends Handler {
        private WeakReference<HomeSelectFragment> mFragment;

        public InterHandler(HomeSelectFragment activity) {
            mFragment = new WeakReference<HomeSelectFragment>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HomeSelectFragment activity = mFragment.get();
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
        return R.layout.select_item;
    }

    @Override
    protected void initdate() {
        view1 = LayoutInflater.from(getContext()).inflate(R.layout.shufflingfigure_item, null);
        mListView = bindView(R.id.listview);
        mListView.addHeaderView(view1);
        initview();

    }

    private void initview() {
        main_gallery = (Gallery) view1.findViewById(R.id.main_gallery);
        main_lin = (LinearLayout) view1.findViewById(R.id.main_lin);
        mGallery = (LinearLayout) view1.findViewById(R.id.id_gallery);
    }


    @Override
    protected void innitView() {
        arrayList = new ArrayList<>();

        mListView.setOnMeiTuanRefreshListener(this);

        url = "http://api.liwushuo.com/v2/banners";
        url1 = "http://api.liwushuo.com/v2/secondary_banners?gender=2&generation=1";
        urlList = "http://api.liwushuo.com/v2/channels/104/items_v2?limit=20&ad=2&gender=2&offset=0&generation=2%20HTTP/1.1";
        LunBo();
        SpecialGet();
        ListGet();
    }

    public void SpecialGet() throws NullPointerException {
        VolleySingleton.addRequest(url1, SpecialBean.class, new Response.Listener<SpecialBean>() {

            @Override
            public void onResponse(SpecialBean response) {

                try {
                    for (int i = 0; i < response.getData().getSecondary_banners().size(); i++) {

                        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_index_gallery_item,
                                mGallery, false);
                        ImageView img = (ImageView) view
                                .findViewById(R.id.id_index_gallery_item_image);

                        img.setAdjustViewBounds(true);

                        Picasso.with(getContext()).load(response.getData()
                                .getSecondary_banners().get(i).getImage_url()).into(img);

                        mGallery.addView(view);
                    }
                } catch (NullPointerException e) {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    public void PickSendGet() {
        li = new ArrayList<Object>();

        target_id = new ArrayList<>();
        title = new ArrayList<>();
        VolleySingleton.addRequest(url, PickBean.class, new Response.Listener<PickBean>() {

            @Override
            public void onResponse(PickBean response) {
                for (int i = 0; i < response.getData().getBanners().size(); i++) {
                    li.add(response.getData().getBanners().get(i).getImage_url());
                    target_id.add(response.getData().getBanners().get(i).getTarget_id());
                    if (response.getData().getBanners().get(i).getTarget() != null) {
                        title.add(response.getData().getBanners().get(i).getTarget().getTitle());
                    } else {
                        title.add(null);
                    }
                }
                gallery_adapter = new Gallery_adapter(getContext());

                main_gallery.setAdapter(gallery_adapter);
                gallery_adapter.setList(li);
                setCircle();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


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
                        Intent it = new Intent(getContext(), WvActivity.class);
                        String url = response.getData().getItems().get(position).getUrl();
                        it.putExtra("webview", url);
                        startActivity(it);
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


    }

    public void LunBo() {
        PickSendGet();
        setCircle();

        main_gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 1:
                        Intent it = new Intent(getContext(), WvLuoboActivity.class);
                        String url = "http://hawaii.liwushuo.com/posts/" + target_id.get(1) + "?campaign";

                        String url1 = url.replace("hawaii", "www");
                        it.putExtra("webview1", url1);
                        startActivity(it);
                        break;
                    case 0:
                        Intent it1 = new Intent(getContext(), WvLuoboActivity.class);
                        String url0 = "http://hawaii.liwushuo.com/posts/" + target_id.get(0) + "?campaign";

                        String url11 = url0.replace("hawaii", "www");
                        it1.putExtra("webview1", url11);
                        startActivity(it1);
                        break;
                    case 2:
                        Intent it3 = new Intent(getContext(), WvLuoboActivity.class);
                        String url3 = "http://hawaii.liwushuo.com/posts/" + target_id.get(2) + "?campaign";

                        String url13 = url3.replace("hawaii", "www");
                        it3.putExtra("webview1", url13);
                        startActivity(it3);
                        break;
                    case 3:

                        Intent oneIntent = new Intent(getContext(), OneActivity.class);
                        oneIntent.putExtra("target_id", target_id.get(3));
                        oneIntent.putExtra("onetitle", title.get(3));
                        startActivity(oneIntent);

                        break;
                    case 4:
                        Intent TaoIntent = new Intent(getContext(), TaotaoActivity.class);
                        TaoIntent.putExtra("taotao", target_id.get(4));
                        TaoIntent.putExtra("taotaotitle", title.get(4));
                        startActivity(TaoIntent);
                        break;
                }


            }
        });
        //设置滚动图片的时候，对应小圆点的图片切换
        main_gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                View v = main_lin.getChildAt(position);
                View cuview = main_lin.getChildAt(current_circle);

                if (v != null && cuview != null) {
                    ImageView pointView = (ImageView) v;
                    ImageView curpointView = (ImageView) cuview;
                    curpointView
                            .setBackgroundResource(R.mipmap.icon_heart_unselected);
                    pointView
                            .setBackgroundResource(R.mipmap.icon_heart_selected);
                    current_circle = position;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //设置轮播定时器
        timeadv = new Runnable() {

            @Override
            public void run() {
                //获取当前的图片是哪一张图片，图片的序号，
                count = main_gallery.getSelectedItemPosition();
                //当前滚动的图片序号大于多有的图片的数量，就跳转到第一张图片，否则就跳转到下一张图片
                if (count + 1 >= li.size()) {
                    count = 0;
                } else {
                    count = count + 1;
                }
                main_gallery.setSelection(count);
                handler.postDelayed(this, 1000);

            }
        };

        //开启定时器，1000毫秒切换一次图片
        handler.postDelayed(timeadv, 2000);
    }

    //设置滚动图片的小圆点
    private void setCircle() throws NullPointerException {
        for (int i = 0; i < li.size(); i++) {
            try {
                ImageView iv = new ImageView(getContext());
                //循环创建小圆点，判断第一个小圆点为白色的，其他的都是透明的
                if (i == 0) {
                    iv.setBackgroundResource(R.mipmap.icon_heart_selected);
                } else {
                    iv.setBackgroundResource(R.mipmap.icon_heart_unselected);
                }
                main_lin.addView(iv);

                //设置小圆点的margin值
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                lp.setMargins(5, 10, 5, 10);
                iv.setLayoutParams(lp);
            } catch (NullPointerException e) {

            }

        }
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {

            @Override
            public void run() {
//                try {
//                    Thread.sleep(3000);
//                   // arrayList.add(0, "new data");
                url = "http://api.liwushuo.com/v2/banners";
                url1 = "http://api.liwushuo.com/v2/secondary_banners?gender=2&generation=1";
                urlList = "http://api.liwushuo.com/v2/channels/104/items_v2?limit=20&ad=2&gender=2&offset=0&generation=2%20HTTP/1.1";
                mInterHandler.sendEmptyMessage(REFRESH_COMPLETE);
//                } catch (InterruptedException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        //关闭activity时，关闭定时器
        if (handler != null) {
            //判断定时器时候为null，！null就销毁
            if (timeadv != null) {
                handler.removeCallbacks(timeadv);
            }
        }
        super.onDestroy();
    }
}
