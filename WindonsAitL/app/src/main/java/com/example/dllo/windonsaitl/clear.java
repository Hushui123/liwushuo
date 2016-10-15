package com.example.dllo.windonsaitl;

import android.widget.TextView;

/**
 * Created by dllo on 16/10/15.
 */
public class clear extends BaseActivtiy{


    private TextView textView;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        textView = binView(R.id.tv_main);
    }

    @Override
    protected void initDate() {

        DataCleanManager.clearAllCache(clear.this);
        try {
            textView.setText(DataCleanManager.getTotalCacheSize(clear.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
