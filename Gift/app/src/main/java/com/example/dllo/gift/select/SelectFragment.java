package com.example.dllo.gift.select;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.example.dllo.gift.basefragment.BaseFragment;
import com.example.dllo.gift.R;
import com.example.dllo.gift.select.raider.RaidersFragment;
import com.example.dllo.gift.select.single.SingleFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class SelectFragment extends BaseFragment {

    private TabLayout tbSelectFragment;
    private ViewPager vpSelectFragment;
    private LinearLayout llSelectFragment;

    @Override
    protected int setLayout() {
        return R.layout.select_layout;
    }

    @Override
    protected void initdate() {
        tbSelectFragment = bindView(R.id.select_tb);
        vpSelectFragment = bindView(R.id.select_vp);
        llSelectFragment = bindView(R.id.select_ll);
    }

    @Override
    protected void innitView() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RaidersFragment());
        fragments.add(new SingleFragment());
        ArrayList<String> strings = new ArrayList<>();
        strings.add("攻略");
        strings.add("单品");

        SelectAdpter adpter = new SelectAdpter(getChildFragmentManager());
        adpter.setTitle(strings);
        adpter.setFragments(fragments);
        vpSelectFragment.setAdapter(adpter);
        tbSelectFragment.setupWithViewPager(vpSelectFragment);



    }
}
