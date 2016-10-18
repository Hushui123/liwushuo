package com.example.dllo.gift.me.setting;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.dllo.gift.R;
import com.example.dllo.gift.basefragment.BaseFragment;

/**
 * Created by dllo on 16/10/11.
 */
public class ShenfenFragment extends BaseFragment implements View.OnClickListener {

    private ImageView back;
    private ImageView cancle;
    private LinearLayout linearLayout;
    private RelativeLayout mRl;

    @Override
    protected int setLayout() {
        return R.layout.shenfen_layout;
    }

    @Override
    protected void initdate() {
        back = bindView(R.id.img_shenfen_back);
        cancle = bindView(R.id.img_cancel);
        linearLayout = bindView(R.id.ll_lala);
        mRl = bindView(R.id.shenfen_rl);
    }

    @Override
    protected void innitView() {
        cancle.setOnClickListener(this);
        back.setOnClickListener(this);

        TranslateAnimation tt = new TranslateAnimation(Animation.RELATIVE_TO_SELF,-10,
                Animation.RELATIVE_TO_PARENT,0,
                Animation.RELATIVE_TO_SELF,-10,
                Animation.RELATIVE_TO_PARENT,0);
        tt.setDuration(1000);
        linearLayout.startAnimation(tt);
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getActivity().getSupportFragmentManager();

        switch (v.getId()){
            case R.id.img_shenfen_back:

                manager.beginTransaction().replace(R.id.fl_xiayibu,new sexFragment()).commit();

                break;
            case R.id.img_cancel:
              manager.beginTransaction().remove(this).commit();
                break;
            case R.id.shenfen_rl:
                break;
        }
    }


}
