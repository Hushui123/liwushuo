package com.example.dllo.windonsaitl;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/10/15.
 */
public abstract class BaseFragment extends Fragment {
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        innitView();
    }

    protected abstract void innitView();

    protected abstract void initView();

    protected abstract int setLayout();

    protected <T extends View> T bindView(int id){
        return (T) getView().findViewById(id);
    }
    protected <T extends View> T bindView(int id,View view){

        return (T) getView().findViewById(id);
    }
}
