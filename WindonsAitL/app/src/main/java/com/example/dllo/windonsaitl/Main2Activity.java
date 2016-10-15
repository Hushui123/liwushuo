package com.example.dllo.windonsaitl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        LinearLayout linearLayout = null;
        TranslateAnimation tt = new TranslateAnimation(Animation.RELATIVE_TO_SELF,-10,
                Animation.RELATIVE_TO_PARENT,0,
                Animation.RELATIVE_TO_SELF,-10,
                Animation.RELATIVE_TO_PARENT,0);
        tt.setDuration(1000);
        linearLayout.startAnimation(tt);
    }
}
