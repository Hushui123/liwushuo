package com.example.dllo.gift.home.luotu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.gift.R;
import com.example.dllo.gift.gson.VolleySingleton;
import com.example.dllo.gift.home.handpick.Wv;
import com.example.dllo.gift.home.search.OnListViewClickItem;

public class One extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private ImageView share;
    private ListView listView;
    private int target_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        back = (ImageView) findViewById(R.id.img_lunbo_back);
        share = (ImageView) findViewById(R.id.img_lunbo_share);
        listView = (ListView) findViewById(R.id.lv_lunbo_list);
        TextView textView = (TextView) findViewById(R.id.onetitle);
        back.setOnClickListener(this);
        share.setOnClickListener(this);
        Intent intent = getIntent();
        target_id = (int) intent.getExtras().get("target_id");
        String title = intent.getStringExtra("onetitle");
        textView.setText(title);
        oneGet();
    }

    private void oneGet() {
        String url = "http://api.liwushuo.com/v2/collections/"+target_id+"/posts?gender=1&generation=2&limit=20&offset=0";

        VolleySingleton.addRequest(url, OneBean.class, new Response.Listener<OneBean>() {

            @Override
            public void onResponse(final OneBean response) {

                oneAdpter adpter = new oneAdpter(One.this);
                //adpter.setArrayList(response);
                adpter.setOneBean(response);
                listView.setAdapter(adpter);
                adpter.setOnListViewClickItem(new OnListViewClickItem() {
                    @Override
                    public void click(int position) {
                        Intent it = new Intent(One.this, Wv.class);
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
        switch (v.getId()) {
            case R.id.img_lunbo_back:
                onBackPressed();
                break;
            case R.id.img_lunbo_share:


                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
