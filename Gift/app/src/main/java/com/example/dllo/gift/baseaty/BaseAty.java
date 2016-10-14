package com.example.dllo.gift.baseaty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by dllo on 16/9/19.
 */
public abstract class BaseAty extends FragmentActivity {
//    @Override
//    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);

//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(setLayout());
        Log.d("BaseAty", "lalda");
        initView();
        initDate();
    }

    protected abstract int setLayout();

    protected abstract void initView();

    protected abstract void initDate();

    protected <T extends View>T bindView(int id){
        return (T)findViewById(id);
    }
    protected <T extends View> T bindView(int id,View v){
        return (T)v.findViewById(id);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
