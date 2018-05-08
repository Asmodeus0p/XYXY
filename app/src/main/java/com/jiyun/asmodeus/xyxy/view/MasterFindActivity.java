package com.jiyun.asmodeus.xyxy.view;


import android.support.v7.widget.RecyclerView;

import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.view.base.BaseActivity;

public class MasterFindActivity extends BaseActivity {
    @Override
    protected void onStart() {
        super.onStart();
        App.context = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.context = this;
    }

    //大师
    private static final int LEVEL1 = 2;
    //名师
    private static final int LEVEL2 = 1;
    //达人
    private static final int LEVEL3 = 0;
    //达人
    private static final String SORT_TYPE_DAREN = "2";
    //名师
    private static final String SORT_TYPE_MINGSHI = "3";
    //大师
    private static final String SORT_TYPE_DASHI = "4";
    private TextView masterlist_title_cancle;
    private RelativeLayout masterlist_title_group;
    private TextView master_list_level3_tv;
    private TextView master_list_level3_line;
    private RelativeLayout master_list_level3;
    private TextView master_list_level2_tv;
    private TextView master_list_level2_line;
    private RelativeLayout master_list_level2;
    private TextView master_list_level1_tv;
    private TextView master_list_level1_line;
    private RelativeLayout master_list_level1;
    private RecyclerView masterlist_recyclerview;
    private RelativeLayout masterlist_recyclerview_empty;
    private Button masterlist_recyclerview_fault_btn;
    private RelativeLayout masterlist_recyclerview_fault;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_master_find;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        masterlist_title_cancle = findViewById(R.id.masterlist_title_cancle);
        masterlist_title_group = findViewById(R.id.masterlist_title_group);
        master_list_level3_tv = findViewById(R.id.master_list_level3_tv);
        master_list_level3_line = findViewById(R.id.master_list_level3_line);
        master_list_level3 = findViewById(R.id.master_list_level3);
        master_list_level2_tv = findViewById(R.id.master_list_level2_tv);
        master_list_level2_line = findViewById(R.id.master_list_level2_line);
        master_list_level1_tv = findViewById(R.id.master_list_level1_tv);
        master_list_level1_line = findViewById(R.id.master_list_level1_line);
        master_list_level1 = findViewById(R.id.master_list_level1);
        masterlist_recyclerview = findViewById(R.id.masterlist_recyclerview);
        masterlist_recyclerview_empty = findViewById(R.id.masterlist_recyclerview_empty);
        masterlist_recyclerview_fault_btn = findViewById(R.id.masterlist_recyclerview_fault_btn);
    }


}
