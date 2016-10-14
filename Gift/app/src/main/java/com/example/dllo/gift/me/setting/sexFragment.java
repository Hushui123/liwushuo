package com.example.dllo.gift.me.setting;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.example.dllo.gift.R;
import com.example.dllo.gift.basefragment.BaseFragment;

/**
 * Created by dllo on 16/10/10.
 */
public class sexFragment extends BaseFragment implements View.OnClickListener {

    private RadioButton boy;
    private RadioButton giry;
    private LinearLayout llBoy;
    private LinearLayout llgiry;
    private ImageView back;
    private RelativeLayout rv;
    private Button next;
    private FrameLayout frameLayout;
    private LinearLayout linearLayout;

    @Override
    protected int setLayout() {
        return R.layout.activity_sex;
    }

    @Override
    protected void initdate() {
        boy = bindView(R.id.btn_boy);
        giry = bindView(R.id.btn_girl);
        llBoy = bindView(R.id.ll_boy);
        llgiry = bindView(R.id.ll_grils);
        back = bindView(R.id.img_sex_btn);
        rv = bindView(R.id.rl_norepson);
        next = bindView(R.id.btn_xiayibu);
        frameLayout = bindView(R.id.fl_shengfen);
        linearLayout = bindView(R.id.ll_sex);
    }

    @Override
    protected void innitView() {
        back.setOnClickListener(this);
        rv.setOnClickListener(this);
        next.setOnClickListener(this);

//        SharedPreferences sp = getActivity().getSharedPreferences("kaoshi", Context.MODE_PRIVATE);
//        SharedPreferences.Editor spEt = sp.edit();
//        spEt.putBoolean("isfo", false);
//        spEt.commit();
        llBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giry.setChecked(false);
                boy.setChecked(true);
            }
        });

        llgiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boy.setChecked(false);
                giry.setChecked(true);
            }
        });

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
        switch (v.getId()) {
            case R.id.rl_norepson:
                break;
            case R.id.img_sex_btn:
               manager.beginTransaction().remove(this).commit();
                break;
            case R.id.btn_xiayibu:
                manager.beginTransaction().replace(R.id.fl_sex, new ShenfenFragment()).commit();
                break;
        }
    }


}
