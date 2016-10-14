package com.example.dllo.gift.basefragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/9/19.
 */
public abstract class BaseFragment extends Fragment{

    protected Context mcontext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mcontext=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(),container,false);
    }
    protected abstract int setLayout();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initdate();
        innitView();
    }
    protected abstract void initdate();
    protected abstract void innitView();

    protected <T extends View> T bindView(int id){
        return (T)getView().findViewById(id);
    }
    protected <T extends View> T bindView(int id,View v){
        return (T)getView().findViewById(id);
    }
}
