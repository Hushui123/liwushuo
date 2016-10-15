package com.example.dllo.windonsaitl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * Created by dllo on 16/10/15.
 */
public class PopWindow extends BaseActivtiy implements View.OnClickListener {

    private TextView tv;
    private PopupWindow popupWindow;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }
    @Override
    protected void initView() {
        tv = binView(R.id.tv_main);

        tv.setOnClickListener(this);
    }
    @Override
    protected void initDate() {
        popupWindow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(this).inflate(R.layout.pop,null);

        popupWindow.setContentView(view);
       // show 默认显示到btn的左下角 第二参数在x轴偏移 第三个是在y轴偏移
        popupWindow.showAsDropDown(tv,0,0);
    }


    @Override
    public void onClick(View v) {

        popupWindow.dismiss();
    }
}
