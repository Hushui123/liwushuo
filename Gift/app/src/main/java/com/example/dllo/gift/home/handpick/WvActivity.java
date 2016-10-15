package com.example.dllo.gift.home.handpick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dllo.gift.R;


public class WvActivity extends AppCompatActivity {

    private WebView wv;
    private String url1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Intent intent = getIntent();
        url1 = intent.getStringExtra("webview");
        Log.d("WvActivity", url1);
        initView();
    }

    public void initView() {
        wv = (WebView) findViewById(R.id.wv);
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
