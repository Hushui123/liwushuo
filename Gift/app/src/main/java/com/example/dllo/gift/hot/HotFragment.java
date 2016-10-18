package com.example.dllo.gift.hot;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.gift.R;
import com.example.dllo.gift.basefragment.BaseFragment;
import com.example.dllo.gift.bean.HotBean;
import com.example.dllo.gift.home.HomeAdpter;
import com.example.dllo.gift.home.VolleySingleton;
import com.example.dllo.gift.hot.dailyhot.DailyHotFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/9/19.
 */
public class HotFragment extends BaseFragment implements View.OnClickListener {

    private ImageView share;
    private ViewPager vp_hot;
    private TabLayout tb_hot;
    private String url;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> title;

    private RadioButton wechat;
    private RadioButton timeline;
    private RadioButton qq;
    private RadioButton weibo;
    private RadioButton qzone;
    private RadioButton hyperlink;

    @Override
    protected int setLayout() {
        return R.layout.hot_layout;
    }

    @Override
    protected void initdate() {
        share = bindView(R.id.iv_hot_back);
        vp_hot = bindView(R.id.vp_hot);
        tb_hot = bindView(R.id.tb_hot);


    }

    @Override
    protected void innitView() {
        url = "http://api.liwushuo.com/v2/ranks_v2/ranks";

        share.setOnClickListener(this);

        ShareSDK.initSDK(getContext(),"sharesdk的appkey");
        fragments = new ArrayList<>();
        title = new ArrayList<>();
        sendGet();
    }

    private void sendGet() {

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                HotBean bean = gson.fromJson(response, HotBean.class);
                for (int i = 0; i < bean.getData().getRanks().size(); i++) {
                    title.add(bean.getData().getRanks().get(i).getName());
                }

                for (int i = 0; i < title.size(); i++) {
//                    DailyHotFragment f = new DailyHotFragment();
//                    Bundle args = new Bundle();
                    int ii = i + 1;
//                    args.putString("url", ii + "");
//                    f.setArguments(args);
                    fragments.add(DailyHotFragment.newInstance(ii + ""));
                }

                HomeAdpter adpter = new HomeAdpter(getChildFragmentManager());
                adpter.setFragments(fragments);
                adpter.setTitle(title);
                vp_hot.setAdapter(adpter);
                tb_hot.setupWithViewPager(vp_hot);

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
            case R.id.iv_hot_back:
                showShare();

//                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View view = inflater.inflate(R.layout.dialog_layout, null);
//
//
//                wechat = (RadioButton) view.findViewById(R.id.btn_wechat);
//                timeline = (RadioButton) view.findViewById(R.id.btn_timeline);
//                qq = (RadioButton) view.findViewById(R.id.btn_qq);
//                weibo = (RadioButton) view.findViewById(R.id.btn_weibo);
//                qzone = (RadioButton) view.findViewById(R.id.btn_qzone);
//                hyperlink = (RadioButton) view.findViewById(R.id.btn_hyperlink);
//
//                wechat.setOnClickListener(this);
//                timeline.setOnClickListener(this);
//                qq.setOnClickListener(this);
//                weibo.setOnClickListener(this);
//                qzone.setOnClickListener(this);
//                hyperlink.setOnClickListener(this);
//
//                AlertDialog dialog = new AlertDialog.Builder(getContext(), R.style.Dialog_FS).create();
//                Window window = dialog.getWindow();
//                window.setGravity(Gravity.BOTTOM);
//                window.setWindowAnimations(R.style.mystyle);
//                dialog.setView(view);
//                dialog.show();
//                WindowManager.LayoutParams lp = window.getAttributes();
//                lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
//                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//                window.setAttributes(lp);
                break;
            case R.id.btn_wechat:
                break;
            case R.id.btn_timeline:
                break;
            case R.id.btn_qq:
                break;
            case R.id.btn_weibo:
                break;
            case R.id.btn_qzone:
                break;
            case R.id.btn_hyperlink:
                ClipboardManager copy = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText("http://hawaii.liwushuo.com/ranks_v2/ranks/1");
                Toast.makeText(getContext(), "复制成功，可以发给朋友们了。", Toast.LENGTH_LONG).show();
                break;
        }

    }
    private void showShare() {
        ShareSDK.initSDK(getContext());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        Bitmap enableLogo = BitmapFactory.decodeResource(this.getResources() ,
                R.mipmap.ic_share_hyperlink);
        String label = "复制链接";
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager copy = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText("http://hawaii.liwushuo.com/ranks_v2/ranks/1");
                Toast.makeText(getContext(), "复制成功，可以发给朋友们了。", Toast.LENGTH_LONG).show();
            }
        };
        oks.setCustomerLogo(enableLogo , label , listener);

// 启动分享GUI
        oks.show(getContext());
    }
}
