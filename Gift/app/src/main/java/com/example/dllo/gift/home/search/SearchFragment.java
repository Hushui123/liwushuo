package com.example.dllo.gift.home.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.gift.R;
import com.example.dllo.gift.gson.VolleySingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 16/10/8.
 */
public class SearchFragment extends Fragment {


    private ListView lv;
    private String strUrl;
    private static SearchFragment singgo_lazy;
    private SimpleAdapter adapter;
    private static SearchFragment fragment;

    public static SearchFragment newInstance(String wordUrl) {

        Bundle args = new Bundle();
        args.putString("wordUrl", wordUrl);
        if ( fragment == null) {
            fragment = new SearchFragment();
        }

        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wordlist_search,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv = (ListView) view.findViewById(R.id.lv_search_word);

        Bundle agrs =getArguments();
        strUrl = agrs.getString("wordUrl");
        wordGet();
    }

    public void wordGet(){

        VolleySingleton.addRequest(strUrl, WordBean.class, new Response.Listener<WordBean>() {

            @Override
            public void onResponse(WordBean response) {
                List<Map<String, Object>> listems = new ArrayList<Map<String, Object>>();
                for (int i = 0; i < response.getData().getWords().size(); i++) {
                    Map<String, Object> listem = new HashMap<String, Object>();
                    listem.put("word",response.getData().getWords().get(i));
                    listems.add(listem);
                }
                try {
                    adapter = new SimpleAdapter(getContext(),listems, R.layout.simple_item,
                            new String[]{"word"}, new int[]{R.id.tv_search_slimply});
                    lv.setAdapter(adapter);
                }catch (NullPointerException e){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }
}
