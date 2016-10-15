package com.example.dllo.windonsaitl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // timer.schedule(task,1000,1000);


        /**********************二级页面****************************/
        ExpandableListView lv = (ExpandableListView) findViewById(R.id.ev);
        ArrayList<String> a = new ArrayList<>();
        a.add("zheshi san");
        a.add("sheshi");
        ArrayList<String> b = new ArrayList<>();
        b.add("bbb");

        ArrayList<String> arrayListGroup = new ArrayList<>();

        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        arrayLists.add(a);
        arrayLists.add(b);
        for (int i = 0; i < 2; i++) {
            arrayListGroup.add("View convertView" + i);
        }

        MyExpandableListViewAdapter adapter = new MyExpandableListViewAdapter(this);
        adapter.setArrayListGroup(arrayListGroup);
        adapter.setArrayListsChid(arrayLists);
        lv.setAdapter(adapter);
        for (int i = 0; i < 2; i++) {
            lv.expandGroup(i);
        }
        lv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // TODO Auto-generated method stub
                return true;
            }
        });
    }
    /**         定时器         **/
    private int recLen = 3;
    private Timer timer = new Timer();
    private TimerTask task =new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recLen --;
                    if (recLen < 0) {
                        timer.cancel();
                    }
                }
            });
        }
    };
}
