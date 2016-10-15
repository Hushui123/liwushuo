package com.example.dllo.gift.ad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.dllo.gift.R;
import com.example.dllo.gift.main.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

import cn.jpush.android.api.JPushInterface;

public class AdActivity extends FragmentActivity {
    private int recLen=3;
    private Timer timer=new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ad);
        timer.schedule(task, 1000, 1000);

    }
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recLen--;
                    if (recLen <0) {
                        timer.cancel();
                        Intent intent =new Intent(AdActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
