package com.example.dllo.gift.me.setting;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.gift.R;
import com.example.dllo.gift.count.Count;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout gandan;
    private RelativeLayout gengxin;
    private RelativeLayout guangyu;
    private RelativeLayout huancun;
    private RelativeLayout pingFen;
    private RelativeLayout search1;
    private RelativeLayout sercivce;
    private RelativeLayout shenFen;
    private RelativeLayout tuiSong;
    private RelativeLayout xihuan;
    private ImageView back;
    private RelativeLayout yao;
    private FrameLayout flSex;
    private Intent intent;
    private TextView clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        flSex = (FrameLayout) findViewById(R.id.fl_sex);

        back = (ImageView) findViewById(R.id.back_setting);

        gandan = (RelativeLayout) findViewById(R.id.rl_gandan_setting);
        gengxin = (RelativeLayout) findViewById(R.id.rl_gengxin_setting);
        guangyu = (RelativeLayout) findViewById(R.id.rl_guangyu_setting);
        huancun = (RelativeLayout) findViewById(R.id.rl_huancun_setting);
        pingFen = (RelativeLayout) findViewById(R.id.rl_pingfen_setting);
        clear = (TextView) findViewById(R.id.tv_clear_setting);
        search1 = (RelativeLayout) findViewById(R.id.rl_service_setting);
        sercivce = (RelativeLayout) findViewById(R.id.rl_service_setting);
        shenFen = (RelativeLayout) findViewById(R.id.rl_shenfen_setting);
        tuiSong = (RelativeLayout) findViewById(R.id.rl_tuisong_setting);
        xihuan = (RelativeLayout) findViewById(R.id.rl_xihuan_setting);
        yao = (RelativeLayout) findViewById(R.id.rl_yaoqing_setting);

        back.setOnClickListener(this);

        gandan.setOnClickListener(this);
        gengxin.setOnClickListener(this);
        guangyu.setOnClickListener(this);
        huancun.setOnClickListener(this);
        pingFen.setOnClickListener(this);

        search1.setOnClickListener(this);
        sercivce.setOnClickListener(this);
        shenFen.setOnClickListener(this);
        tuiSong.setOnClickListener(this);
        xihuan.setOnClickListener(this);
        yao.setOnClickListener(this);

        try {
            clear.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_setting:
              onBackPressed();
                break;
            case R.id.rl_shenfen_setting:
                FragmentManager manager = getSupportFragmentManager();
                sexFragment fragment = new sexFragment();
                manager.beginTransaction().add(R.id.fl_sex,fragment).commit();
                break;
            case R.id.rl_yaoqing_setting:
                String[] itemStrings = {"邀请好友", "登陆后邀请获得50积分", "直接邀请", "暂不邀请"};
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setIcon(android.R.drawable.ic_lock_lock).
                        setItems(itemStrings,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub
                                    }
                                }).create().show();
                break;
            case R.id.rl_pingfen_setting:
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.pingfen_layout, null);
                AlertDialog.Builder builder2 = new AlertDialog.Builder(SettingActivity.this);
                builder2.setView(view);
                builder2.create().show();

                TextView land = (TextView) view.findViewById(R.id.tv_land_setting);
                TextView pingfen = (TextView) view.findViewById(R.id.tv_pingfen_setting);
                TextView concel = (TextView) view.findViewById(R.id.tv_concel_setting);

                land.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentLand = new Intent(SettingActivity.this, Count.class);
                        startActivity(intentLand);
                    }
                });
                concel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });

                break;
            case R.id.rl_yijian_setting:

                Toast.makeText(this, "服务暂未开通", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_service_setting:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(SettingActivity.this);
                builder1.setMessage("拨打礼物说客服电话");
                builder1.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent hiddenI = new Intent(Intent.ACTION_CALL);
                        // 设置data数据
                        // 访问拨号界面

                        Uri dataU = Uri.parse("tel:" + "4009992053");
                        hiddenI.setData(dataU);
                        if (ActivityCompat.checkSelfPermission(SettingActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        startActivity(hiddenI);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                    }
                });
                builder1.show();
                break;
            case R.id.rl_xihuan_setting:


                break;
            case R.id.rl_tuisong_setting:
                break;
            case R.id.rl_gengxin_setting:
                break;
            case R.id.rl_huancun_setting:
                try {

                    DataCleanManager.clearAllCache(SettingActivity.this);
                    try {
                        clear.setText(DataCleanManager.getTotalCacheSize(SettingActivity.this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


            } catch (Exception e) {
                e.printStackTrace();
            }

                break;
            case R.id.rl_gandan_setting:
                break;
            case R.id.rl_guangyu_setting:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
