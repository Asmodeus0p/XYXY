package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;

public class LivingListActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView livinglist_aty_title_cancle;
    private RelativeLayout livinglist_aty_title_group;
    private TextView livinglist_aty_level1_tv;
    private TextView livinglist_aty_level1_line;
    private RelativeLayout livinglist_aty_level1;
    private TextView livinglist_aty_level2_tv;
    private TextView livinglist_aty_level2_line;
    private RelativeLayout livinglist_aty_level2;
    private RecyclerView livinglist_aty_recyclerview;
    private RelativeLayout livinglist_aty_recyclerview_empty;
    private Button livinglist_aty_recyclerview_fault_btn;
    private RelativeLayout livinglist_aty_recyclerview_fault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_list);
        initView();

    }

    private void initView() {
        livinglist_aty_title_cancle = (TextView) findViewById(R.id.livinglist_aty_title_cancle);
        livinglist_aty_title_group = (RelativeLayout) findViewById(R.id.livinglist_aty_title_group);
        livinglist_aty_level1_tv = (TextView) findViewById(R.id.livinglist_aty_level1_tv);
        livinglist_aty_level1_line = (TextView) findViewById(R.id.livinglist_aty_level1_line);
        livinglist_aty_level1 = (RelativeLayout) findViewById(R.id.livinglist_aty_level1);
        livinglist_aty_level2_tv = (TextView) findViewById(R.id.livinglist_aty_level2_tv);
        livinglist_aty_level2_line = (TextView) findViewById(R.id.livinglist_aty_level2_line);
        livinglist_aty_level2 = (RelativeLayout) findViewById(R.id.livinglist_aty_level2);
        livinglist_aty_recyclerview = (RecyclerView) findViewById(R.id.livinglist_aty_recyclerview);
        livinglist_aty_recyclerview_empty = (RelativeLayout) findViewById(R.id.livinglist_aty_recyclerview_empty);
        livinglist_aty_recyclerview_fault_btn = (Button) findViewById(R.id.livinglist_aty_recyclerview_fault_btn);
        livinglist_aty_recyclerview_fault = (RelativeLayout) findViewById(R.id.livinglist_aty_recyclerview_fault);

        livinglist_aty_recyclerview_fault_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.livinglist_aty_recyclerview_fault_btn:

                break;
        }
    }
}
