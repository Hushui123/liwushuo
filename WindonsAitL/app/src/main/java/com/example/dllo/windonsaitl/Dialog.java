package com.example.dllo.windonsaitl;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by dllo on 16/10/15.
 */
public class Dialog extends BaseActivtiy{

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDate() {

        /**********************第一种情况****************************/
        String[] itemStrings = {"邀请好友", "登陆后邀请获得50积分", "直接邀请", "暂不邀请"};
        AlertDialog.Builder builder = new AlertDialog.Builder(Dialog.this);
        builder.setIcon(android.R.drawable.ic_lock_lock).
                setItems(itemStrings,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                            }
                        }).create().show();



        /**********************第二种情况****************************/
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pingfen_layout, null);
        AlertDialog.Builder builder2 = new AlertDialog.Builder(Dialog.this);
        builder2.setView(view);
        builder2.create().show();

        TextView land = (TextView) view.findViewById(R.id.tv_land_setting);
        TextView pingfen = (TextView) view.findViewById(R.id.tv_pingfen_setting);
        TextView concel = (TextView) view.findViewById(R.id.tv_concel_setting);

        land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        concel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        /**********************第三种情况****************************/


    }


}
