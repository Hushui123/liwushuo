package com.example.dllo.gift.home.luotu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.gift.R;
import com.example.dllo.gift.home.handpick.Wv;
import com.example.dllo.gift.home.search.OnListViewClickItem;

public class TaotaoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private ImageView share;
    private ListView listView;
    private RadioButton wechat;
    private RadioButton timeline;
    private RadioButton qq;
    private RadioButton weibo;
    private RadioButton qzone;
    private RadioButton hyperlink;
    private int target_id;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taotao);

        back = (ImageView) findViewById(R.id.img_taotao_back);
        share = (ImageView) findViewById(R.id.img_taotao_share);
        listView = (ListView) findViewById(R.id.lv_taotao_list);
        title = (TextView) findViewById(R.id.tv_title_lunbo);


        back.setOnClickListener(this);
        share.setOnClickListener(this);
        Intent intent = getIntent();
        target_id = (int) intent.getExtras().get("taotao");
        String intentTitle = intent.getStringExtra("taotaotitle");
        title.setText(intentTitle);
        taoGet();

    }

    private void taoGet() {
        String url = "http://api.liwushuo.com/v2/collections/"+target_id+"/posts?gender=1&generation=2&limit=20&offset=0";
        com.example.dllo.gift.gson.VolleySingleton.addRequest(url, OneBean.class, new Response.Listener<OneBean>() {

            @Override
            public void onResponse(final OneBean response) {

                oneAdpter adpter = new oneAdpter(TaotaoActivity.this);
                //adpter.setArrayList(response);
                adpter.setOneBean(response);
                listView.setAdapter(adpter);
                adpter.setOnListViewClickItem(new OnListViewClickItem() {
                    @Override
                    public void click(int position) {
                        Intent it = new Intent(TaotaoActivity.this, Wv.class);
                        String url = response.getData().getPosts().get(position).getUrl();
                        it.putExtra("webview", url);
                        startActivity(it);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_taotao_back:
                onBackPressed();
                break;
            case R.id.img_taotao_share:
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.dialog_layout, null);
                wechat = (RadioButton) view.findViewById(R.id.btn_wechat);
                timeline = (RadioButton) view.findViewById(R.id.btn_timeline);
                qq = (RadioButton) view.findViewById(R.id.btn_qq);
                weibo = (RadioButton) view.findViewById(R.id.btn_weibo);
                qzone = (RadioButton) view.findViewById(R.id.btn_qzone);
                hyperlink = (RadioButton) view.findViewById(R.id.btn_hyperlink);

                wechat.setOnClickListener(this);
                timeline.setOnClickListener(this);
                qq.setOnClickListener(this);
                weibo.setOnClickListener(this);
                qzone.setOnClickListener(this);
                hyperlink.setOnClickListener(this);

                AlertDialog dialog = new AlertDialog.Builder(TaotaoActivity.this, R.style.Dialog_FS).create();
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                window.setWindowAnimations(R.style.mystyle);
                dialog.setView(view);
                dialog.show();
                WindowManager.LayoutParams lp = window.getAttributes();
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
