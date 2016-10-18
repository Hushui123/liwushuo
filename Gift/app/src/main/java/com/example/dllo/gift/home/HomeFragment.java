package com.example.dllo.gift.home;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.gift.R;
import com.example.dllo.gift.basefragment.BaseFragment;
import com.example.dllo.gift.bean.MyHomeBean;
import com.example.dllo.gift.count.CountActivity;
import com.example.dllo.gift.home.handpick.HomeSelectFragment;
import com.example.dllo.gift.home.search.SearchActivity;
import com.example.dllo.gift.home.sendboys.SendBoysFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private PopupWindow popupWindow;
    private ImageView img_mean;
    private TabLayout tb_home;
    private ViewPager vp_home;
    private String url;
    private ArrayList<String> title;
    private ArrayList<Fragment> fragments;
    private ImageView imgDaoHang;
    private MyHomeBean bean;
    private TextView search;

    @Override
    protected int setLayout() {
        return R.layout.home_layout;
    }

    @Override
    protected void initdate() {
        img_mean = bindView(R.id.img_mean);
        img_mean.setOnClickListener(this);
        imgDaoHang = bindView(R.id.img_home_pop);
        tb_home = bindView(R.id.tb_home);
        vp_home = bindView(R.id.vp_home);
        search = bindView(R.id.tv_title_home_search);
        search.setOnClickListener(this);
    }

    @Override
    protected void innitView() {
        url = "http://api.liwushuo.com/v2/channels/preset?gender=2&generation=1";
        fragments = new ArrayList<>();

        title = new ArrayList<>();

        sendGet();

        imgDaoHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow == null || !popupWindow.isShowing()) {
                    initPop();
                }
            }
        });


    }

    private void initPop() {
        // 显示
        //第一步  初始化PopupWindow

        popupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //给PopupWindow设置一个View
        View view = LayoutInflater.from(getContext()).inflate(R.layout.pop, null);
        ImageView imgbtnPop = (ImageView) view.findViewById(R.id.imgbtn_pop);
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll_pop);
        imgbtnPop.setOnClickListener(this);
        GridView gridView = (GridView) view.findViewById(R.id.girdView_pop);
        ArrayList<MyHomeBean> myHomeBeen = new ArrayList<>();
        myHomeBeen.add(bean);
        PopAdpter popAdpter = new PopAdpter(getContext());
        popAdpter.setMyHomeBeanArrayList(myHomeBeen);
        gridView.setAdapter(popAdpter);
        ll.setOnClickListener(this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vp_home.setCurrentItem(position);
                popupWindow.dismiss();
            }
        });

        popupWindow.setContentView(view);

        //jiangpop   show 默认显示到btn的左下角 第二参数在x轴偏移 第三个是在y轴偏移
        popupWindow.showAsDropDown(imgDaoHang, -15, -50);
    }

    private void sendGet() {

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                bean = gson.fromJson(response, MyHomeBean.class);
                for (int i = 0; i < bean.getData().getChannels().size(); i++) {
                    title.add(bean.getData().getChannels().get(i).getName());
                }

                for (int i = 0; i < title.size(); i++) {
                    if (i == 0) {
                        fragments.add(new HomeSelectFragment());
                    } else {
                        fragments.add(SendBoysFragment.newInstance(String.valueOf(bean.getData().getChannels().get(i).getId())));
                    }
                }

                HomeAdpter adpter = new HomeAdpter(getChildFragmentManager());
                adpter.setFragments(fragments);
                adpter.setTitle(title);
                vp_home.setAdapter(adpter);
                tb_home.setupWithViewPager(vp_home);
                tb_home.setTabMode(TabLayout.MODE_SCROLLABLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleton.getInStance().addRequest(stringRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_mean:

                Intent intent = new Intent(getContext(), CountActivity.class);
                startActivity(intent);
                break;
            case R.id.imgbtn_pop:
                popupWindow.dismiss();
                break;
            case R.id.tv_title_home_search:

                Intent intentSearch = new Intent(getContext(), SearchActivity.class);
                startActivity(intentSearch);
                break;
            case R.id.ll_pop:
                popupWindow.dismiss();
                break;
        }


    }
}
