package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.view.base.BaseActivity;

public class MasterGuanzhuListActivity extends BaseActivity implements View.OnClickListener {

    private TextView masterworklist_title_cancle;
    private TextView masterworklist_title_tv;
    private RecyclerView masterworklist_recyclerview;
    private RelativeLayout masterworklist_recyclerview_empty;
    private Button masterworklist_recyclerview_fault_btn;
    private RelativeLayout masterworklist_recyclerview_fault;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_master_guanzhu_list;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int teacherid = intent.getIntExtra("teacherid", 0);
        String name = intent.getStringExtra("name");
    }

    @Override
    protected void initView() {

        masterworklist_title_cancle = (TextView) findViewById(R.id.masterworklist_title_cancle);
        masterworklist_title_cancle.setOnClickListener(this);
        masterworklist_title_tv = (TextView) findViewById(R.id.masterworklist_title_tv);
        masterworklist_title_tv.setOnClickListener(this);
        masterworklist_recyclerview = (RecyclerView) findViewById(R.id.masterworklist_recyclerview);
        masterworklist_recyclerview.setOnClickListener(this);
        masterworklist_recyclerview_empty = (RelativeLayout) findViewById(R.id.masterworklist_recyclerview_empty);
        masterworklist_recyclerview_empty.setOnClickListener(this);
        masterworklist_recyclerview_fault_btn = (Button) findViewById(R.id.masterworklist_recyclerview_fault_btn);
        masterworklist_recyclerview_fault_btn.setOnClickListener(this);
        masterworklist_recyclerview_fault = (RelativeLayout) findViewById(R.id.masterworklist_recyclerview_fault);
        masterworklist_recyclerview_fault.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.masterworklist_recyclerview_fault_btn:

                break;
        }
    }
}
