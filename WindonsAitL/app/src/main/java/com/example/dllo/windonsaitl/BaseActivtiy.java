package com.example.dllo.windonsaitl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by dllo on 16/10/15.
 */
public abstract class BaseActivtiy extends FragmentActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        initView();
        initDate();
    }

    protected abstract void initDate();

    protected abstract void initView();

    protected abstract int setLayout();

    protected <T extends View> T binView(int id){
        return (T) findViewById(id);
    }
    protected <T extends View> T binView(int id,View v){
        return (T) v.findViewById(id);
    }
}
