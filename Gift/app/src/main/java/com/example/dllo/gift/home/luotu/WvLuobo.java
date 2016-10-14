package com.example.dllo.gift.home.luotu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.example.dllo.gift.R;
import com.example.dllo.gift.count.Count;


public class WvLuobo extends AppCompatActivity implements View.OnClickListener {

    private WebView wv;
    private String url1;
    private RelativeLayout shoucang;
    private RelativeLayout zhuangfa;
    private RelativeLayout pinglun;


    private RadioButton wechat;
    private RadioButton timeline;
    private RadioButton qq;
    private RadioButton weibo;
    private RadioButton qzone;
    private RadioButton hyperlink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_viewlunbo);
        shoucang = (RelativeLayout) findViewById(R.id.rl_shoucang);
        zhuangfa = (RelativeLayout) findViewById(R.id.rl_zhuangfa);
        pinglun = (RelativeLayout) findViewById(R.id.rl_pinglun);
        Intent intent = getIntent();
        url1 = intent.getStringExtra("webview1");

        Log.d("Wv", url1);
        initView();

        shoucang.setOnClickListener(this);
        zhuangfa.setOnClickListener(this);
        pinglun.setOnClickListener(this);
    }

    public void initView() {
        wv = (WebView) findViewById(R.id.wv1);
        wv.getSettings().setJavaScriptEnabled(true);

        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv.getSettings().setAppCacheEnabled(true);
        wv.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        wv.getSettings().setSupportZoom(true);
        wv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        wv.getSettings().setDisplayZoomControls(true);

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

//        wv.loadUrl(url1);
//        "http://hawaii.liwushuo.com/items/1064910"
        wv.loadUrl(url1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_shoucang:
                Intent intent = new Intent(this, Count.class);
                startActivity(intent);
                break;
            case R.id.rl_zhuangfa:
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dialog_layout, null);
                wechat = (RadioButton) view.findViewById(R.id.btn_wechat);
                timeline = (RadioButton) view.findViewById(R.id.btn_timeline);
                qq = (RadioButton) view.findViewById(R.id.btn_qq);
                weibo = (RadioButton) view.findViewById(R.id.btn_weibo);
                qzone = (RadioButton) view.findViewById(R.id.btn_qzone);
                hyperlink = (RadioButton) view.findViewById(R.id.btn_hyperlink);

                wechat.setOnClickListener(this);
                timeline.setOnClickListener(this);
                qq.setOnClickListener(this);
                weibo.setOnClickListener(this);
                qzone.setOnClickListener(this);
                hyperlink.setOnClickListener(this);

                AlertDialog dialog = new AlertDialog.Builder(this, R.style.Dialog_FS).create();
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                window.setWindowAnimations(R.style.mystyle);
                dialog.setView(view);
                dialog.show();
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);
                break;
            case R.id.rl_pinglun:

                break;
        }
    }
    /**
     * 这个方法不好用
     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if((keyCode==KeyEvent.KEYCODE_BACK)&&wv.canGoBack()) {
//            //如果可以回退
//            wv.goBack() ;
//            return true ;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

}
