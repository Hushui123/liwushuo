package com.example.dllo.gift.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.example.dllo.gift.baseaty.BaseAty;
import com.example.dllo.gift.home.HomeFragment;
import com.example.dllo.gift.hot.HotFragment;
import com.example.dllo.gift.me.MeFragment;
import com.example.dllo.gift.R;
import com.example.dllo.gift.select.SelectFragment;

public class MainActivity extends BaseAty implements View.OnClickListener {


    private FrameLayout fl_main;
    private RadioButton ibtn_home;
    private RadioButton ibtn_hot;
    private RadioButton ibtn_select;
    private RadioButton ibtn_me;

    @Override
    protected int setLayout() {

        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        fl_main = bindView(R.id.fl_main);
        ibtn_home = bindView(R.id.ibtn_home);
        ibtn_hot = bindView(R.id.ibtn_hot);
        ibtn_select = bindView(R.id.ibtn_select);
        ibtn_me = bindView(R.id.ibtn_me);

        ibtn_home.setOnClickListener(this);
        ibtn_hot.setOnClickListener(this);
        ibtn_select.setOnClickListener(this);
        ibtn_me.setOnClickListener(this);
    }

    @Override
    protected void initDate() {


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.fl_main, new HomeFragment());
        transaction.commit();

    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (v.getId()) {
            case R.id.ibtn_home:
                transaction.replace(R.id.fl_main, new HomeFragment());
                break;
            case R.id.ibtn_hot:
                transaction.replace(R.id.fl_main, new HotFragment());
                break;
            case R.id.ibtn_select:
                transaction.replace(R.id.fl_main, new SelectFragment());
                break;
            case R.id.ibtn_me:
                transaction.replace(R.id.fl_main, new MeFragment());
                break;
        }
        transaction.commit();
    }
}
