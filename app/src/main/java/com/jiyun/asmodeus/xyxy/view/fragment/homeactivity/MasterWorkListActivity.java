package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;
import com.jiyun.asmodeus.xyxy.view.base.BaseActivity;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MasterWorkListActivity extends BaseActivity implements View.OnClickListener {

    private TextView masterlive_list_aty_cancle;
    private TextView masterlivelist_teachername_title;
    private RecyclerView masterlivelist_recyclerview;
    private RelativeLayout masterlivelist_recyclerview_empty;
    private Button masterlivelist_recyclerview_fault_btn;
    private RelativeLayout masterlivelist_recyclerview_fault;

    private String name;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_master_work_list;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();

        name = intent.getStringExtra("name");
    }

    @Override
    protected void initView() {
        masterlive_list_aty_cancle = (TextView) findViewById(R.id.masterlive_list_aty_cancle);
        masterlivelist_teachername_title = (TextView) findViewById(R.id.masterlivelist_teachername_title);
        masterlivelist_recyclerview = (RecyclerView) findViewById(R.id.masterlivelist_recyclerview);
        masterlivelist_recyclerview_empty = (RelativeLayout) findViewById(R.id.masterlivelist_recyclerview_empty);
        masterlivelist_recyclerview_fault_btn = (Button) findViewById(R.id.masterlivelist_recyclerview_fault_btn);
        masterlivelist_recyclerview_fault = (RelativeLayout) findViewById(R.id.masterlivelist_recyclerview_fault);
        masterlive_list_aty_cancle.setOnClickListener(this);
        masterlivelist_recyclerview_fault_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.masterlivelist_recyclerview_fault_btn:

                break;
            case R.id.masterlive_list_aty_cancle:
                finish();
                break;
        }
    }
    public void getData(){
        OkHttpClient client = new OkHttpClient.Builder().build();
        int teacherid = getIntent().getIntExtra("id", 0);

        String appToken = (String) SharedPreferencesUtils.getParam(this, "xyxy_apptoken", "String");

        FormBody userId = new FormBody.Builder().add("userId", teacherid + "").build();
        Request build = new Request.Builder().url("https://www.univstar.com/v1/m/user/homepage").post(userId).addHeader("apptoken", appToken).build();
    }

}
