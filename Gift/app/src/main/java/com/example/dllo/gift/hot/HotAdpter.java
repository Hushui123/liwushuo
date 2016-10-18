package com.example.dllo.gift.hot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/22.
 */
public class HotAdpter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;
    private ArrayList<String> title;

    public void setTitle(ArrayList<String> title) {
        this.title = title;
    }

    public ArrayList<Fragment> getFragments() {
        return fragments;
    }

    public HotAdpter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
