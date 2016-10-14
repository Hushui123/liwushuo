package com.example.dllo.gift.me.setting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.dllo.gift.R;

public class sexActivity extends AppCompatActivity {

    private RadioButton boy;
    private RadioButton giry;
    private LinearLayout llBoy;
    private LinearLayout llgiry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex);
        boy = (RadioButton) findViewById(R.id.btn_boy);
        giry = (RadioButton) findViewById(R.id.btn_girl);
        llBoy = (LinearLayout) findViewById(R.id.ll_boy);
        llgiry = (LinearLayout) findViewById(R.id.ll_grils);

        llBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giry.setChecked(false);
            }
        });

        llBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boy.setChecked(false);
            }
        });

    }
}
