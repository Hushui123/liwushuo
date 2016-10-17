package com.example.dllo.gift.me;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.dllo.gift.R;
import com.example.dllo.gift.basefragment.BaseFragment;
import com.example.dllo.gift.count.CountActivity;
import com.example.dllo.gift.me.setting.SettingActivity;

/**
 * Created by dllo on 16/9/19.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {

    private ImageView land;
    private ImageView shezhi;
    private ImageView code;
    private RadioButton order;
    private RadioButton car;
    private RadioButton voucher;
    private RadioButton guangZhu;
    private RadioButton kefu;

    @Override
    protected int setLayout() {
        return R.layout.me_layout;
    }

    @Override
    protected void initdate() {
        land = bindView(R.id.img_land_me);
        shezhi = bindView(R.id.ic_launcher);
        code = bindView(R.id.img_code_me);

        order = bindView(R.id.btn_order_me);
        car = bindView(R.id.btn_shopcar_me);
        voucher = bindView(R.id.btn_Voucher_me);
        guangZhu = bindView(R.id.btn_guangzhu_me);
        kefu = bindView(R.id.btn_kefu_me);
    }

    @Override
    protected void innitView() {

        land.setOnClickListener(this);
        shezhi.setOnClickListener(this);
        code.setOnClickListener(this);

        order.setOnClickListener(this);
        car.setOnClickListener(this);
        voucher.setOnClickListener(this);
        guangZhu.setOnClickListener(this);
        kefu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_land_me:
                Intent intentLand = new Intent(getContext(), CountActivity.class);
                startActivity(intentLand);
                break;
            case R.id.ic_launcher:
                Intent intentSetting = new Intent(getContext(), SettingActivity.class);
                startActivity(intentSetting);
                break;
            case R.id.img_code_me:

                break;
            case R.id.btn_order_me:
                Intent intentOrder = new Intent(getContext(), CountActivity.class);
                startActivity(intentOrder);
                break;
            case R.id.btn_Voucher_me:
                Intent intentVoucher = new Intent(getContext(), CountActivity.class);
                startActivity(intentVoucher);
                break;
            case R.id.btn_guangzhu_me:
                Intent intentGuanzhu = new Intent(getContext(), CountActivity.class);
                startActivity(intentGuanzhu);
                break;
            case R.id.btn_kefu_me:
                Intent intentKefu = new Intent(getContext(), CountActivity.class);
                startActivity(intentKefu);
                break;
            case R.id.btn_shopcar_me:
                Intent intentCar = new Intent(getContext(), CountActivity.class);
                startActivity(intentCar);
                break;
        }
    }
}
