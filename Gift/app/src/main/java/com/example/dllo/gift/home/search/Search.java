package com.example.dllo.gift.home.search;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.gift.R;
import com.example.dllo.gift.database.MySqlHelper;
import com.example.dllo.gift.gson.VolleySingleton;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Search extends AppCompatActivity implements View.OnClickListener {


    private Context context;
    private TextView back;
    private RecyclerView hotRv;
    private String urlHot;
    private ListView lv;
    private ImageView deteAll;
    private RecordAdpter adpter;
    private EditText et;
    private SQLiteDatabase database;
    private RecordAdpter adpter1;
    private ArrayList<String> strings;
    private String wordUrl;
    private RelativeLayout hdie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        back = (TextView) findViewById(R.id.cancel_search);
        hotRv = (RecyclerView) findViewById(R.id.rl_search_biaoqian);
        lv = (ListView) findViewById(R.id.lv_search_jilu);
        deteAll = (ImageView) findViewById(R.id.btn_search_deteall);
        et = (EditText) findViewById(R.id.et_word_search);
        hdie = (RelativeLayout) findViewById(R.id.ll_serach);

        MySqlHelper helper = new MySqlHelper(this, "hotword.db", null, 1);
        database = helper.getWritableDatabase();
        et.addTextChangedListener(textWatcher);

        //http://api.liwushuo.com/v2/search/word_completed?keyword=%E6%9D%AF%E5%AD%90
        deteAll.setOnClickListener(this);
        back.setOnClickListener(this);
        urlHot = "http://api.liwushuo.com/v2/search/hot_words";
        hotWordSet();
        dataBase();
        record();

    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            Log.d("TAG", "afterTextChanged--------------->");
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            transaction.replace(R.id.search_fl, SearchFragment.newInstance(wordUrl));

            transaction.commit();
            hdie.setVisibility(View.VISIBLE);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
            Log.d("TAG", "beforeTextChanged--------------->");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String str = null;
            try {
                if (et.getText().toString() == null) {
                    Log.d("Search", "didi");
                }else {
                    str = URLEncoder.encode(et.getText().toString(), "utf-8");

                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            wordUrl = "http://api.liwushuo.com/v2/search/word_completed?keyword=" + str;
        }
    };

    private void showDialog() {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(Search.this);
        builder.setTitle("消息").setIcon(android.R.drawable.stat_notify_error);
        builder.setMessage("你输出的整型数字有误，请改正");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        dialog = builder.create();
        dialog.show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_search:
                if (et.getText().length() != 0) {
                    dataBase();
                    adpter1.notifyDataSetChanged();
                }
                onBackPressed();
                break;
            case R.id.btn_search_deteall:
                database.delete("word", null, null);
                strings.clear();
                adpter1.notifyDataSetChanged();

                hdie.setVisibility(View.INVISIBLE);

                break;
        }

    }

    public void hotWordSet() {
        final FlowAdpter adpter = new FlowAdpter(Search.this);
        VolleySingleton.addRequest(urlHot, HotBean.class, new Response.Listener<HotBean>() {

            @Override
            public void onResponse(final HotBean response) {
                adpter.setHotBeen(response);
                hotRv.setAdapter(adpter);

                GridLayoutManager manager = new GridLayoutManager(Search.this, 3, LinearLayoutManager.HORIZONTAL, false);
                hotRv.setLayoutManager(manager);

                adpter.setOnRecyclerItemClick(new OnRecyclerItemClick() {
                    @Override
                    public void click(int postion, FlowAdpter.MyViewHodler holder) {
                        et.setText(response.getData().getHot_words().get(postion));
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        this.context = context;
    }

    public static void forceStopRecyclerViewScroll(RecyclerView mRecyclerView) {
        mRecyclerView.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_CANCEL, 0, 0, 0));
    }

    public void record() {
        strings = new ArrayList<>();
        Cursor cursor = database.query("word", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String words = cursor.getString(cursor.getColumnIndex("words"));

                strings.add(words);
            }
            if(!strings.isEmpty()){
                for(int i=0;i<strings.size();i++){
                    for(int j=strings.size()-1;j>i;j--){
                        if(strings.get(i).equals(strings.get(j))){
                            strings.remove(j);
                        }
                    }
                }
            }
            adpter1 = new RecordAdpter(this);
            adpter1.setStringArrayList(strings);
            lv.setAdapter(adpter1);
        }
        adpter1.setOnListViewClickItem(new OnListViewClickItem() {
            @Override
            public void click(int position) {
                database.delete("word", "words =?", new String[]{strings.get(position)});
                strings.remove(position);
                adpter1.notifyDataSetChanged();
            }
        });
    }

    public void dataBase() {

        if (et.getText().length() != 0) {
            ContentValues values = new ContentValues();
            values.put("words", et.getText().toString());
            database.insert("word", null, values);
        }


    }
}
