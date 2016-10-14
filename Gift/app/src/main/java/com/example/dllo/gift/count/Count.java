package com.example.dllo.gift.count;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.gift.baseaty.BaseAty;
import com.example.dllo.gift.R;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

public class Count extends BaseAty implements View.OnClickListener {


    private ImageView imgBack;
    private ImageView sina;
    private ImageView wechat;
    private ImageView qzone;
    PlatformActionListener paListener = null;

    @Override
    protected int setLayout() {
        return R.layout.activity_count;
    }

    @Override
    protected void initView() {
        imgBack = bindView(R.id.iv_back_count);
        sina = bindView(R.id.iv_load_sina);
        wechat = bindView(R.id.iv_load_wechat);
        qzone = bindView(R.id.iv_load_qzone);
        Log.d("Count", "chulaiba ");
    }

    @Override
    protected void initDate() {
        imgBack.setOnClickListener(this);
        sina.setOnClickListener(this);
        wechat.setOnClickListener(this);
        qzone.setOnClickListener(this);
        ShareSDK.initSDK(this,"sharesdk的appkey");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_count:
                onBackPressed();
                break;
            case R.id.iv_load_sina:

                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                weibo.setPlatformActionListener(paListener);
//authorize与showUser单独调用一个即可
                weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
                weibo.showUser(null);//授权并获取用户信息

                break;
            case R.id.iv_load_wechat:

                break;
            case R.id.iv_load_qzone:

                Platform qq = ShareSDK.getPlatform(QQ.NAME);

                qq.setPlatformActionListener(paListener);
//authorize与showUser单独调用一个即可
                qq.authorize();//单独授权,OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
