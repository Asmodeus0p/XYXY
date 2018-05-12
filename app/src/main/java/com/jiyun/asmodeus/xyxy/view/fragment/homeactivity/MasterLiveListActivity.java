package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.LivingListBean;
import com.jiyun.asmodeus.xyxy.model.entity.MasterDetailBean;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;
import com.jiyun.asmodeus.xyxy.model.utils.SplitStringColorUtils;
import com.jiyun.asmodeus.xyxy.view.adapter.LivingListAtyAdapter;
import com.jiyun.asmodeus.xyxy.view.base.BaseActivity;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MasterLiveListActivity extends BaseActivity implements View.OnClickListener {

    private TextView masterworklist_title_cancle;
    private TextView masterworklist_title_tv;
    private RecyclerView masterworklist_recyclerview;
    private RelativeLayout masterworklist_recyclerview_empty;
    private Button masterworklist_recyclerview_fault_btn;
    private RelativeLayout masterworklist_recyclerview_fault;
    private String name;
    private int teacherid;
    private LivingListAtyAdapter adapter;
    private List<LivingListBean.DataBean.ListBean> datalist;
    private int page = 1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_master_live_list;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        teacherid = intent.getIntExtra("teacherid", 0);
        name = intent.getStringExtra("name");
    }

    @Override
    protected void initView() {

        masterworklist_title_cancle = (TextView) findViewById(R.id.masterworklist_title_cancle);
        masterworklist_title_cancle.setOnClickListener(this);
        masterworklist_title_tv = (TextView) findViewById(R.id.masterworklist_title_tv);
        masterworklist_title_tv.setOnClickListener(this);
        masterworklist_recyclerview = (RecyclerView) findViewById(R.id.masterworklist_recyclerview);

        masterworklist_recyclerview_empty = (RelativeLayout) findViewById(R.id.masterworklist_recyclerview_empty);
        masterworklist_recyclerview_empty.setOnClickListener(this);
        masterworklist_recyclerview_fault_btn = (Button) findViewById(R.id.masterworklist_recyclerview_fault_btn);
        masterworklist_recyclerview_fault_btn.setOnClickListener(this);
        masterworklist_recyclerview_fault = (RelativeLayout) findViewById(R.id.masterworklist_recyclerview_fault);
        masterworklist_recyclerview_fault.setOnClickListener(this);
        masterworklist_title_tv.setText(String.format("%s的直播",name));
        getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.masterworklist_recyclerview_fault_btn:

                break;
        }
    }

    public void getData() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        String appToken = (String) SharedPreferencesUtils.getParam(this, "xyxy_apptoken", "String");
        FormBody formBody = new FormBody.Builder().add("teacherid", teacherid + "").build();
        Log.e("112233",teacherid+"");
        Request request = new Request.Builder().url("https://www.univstar.com/v1/m/user/teacher/live").post(formBody).addHeader("apptoken", appToken).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LivingListBean detailBean = new Gson().fromJson(string, LivingListBean.class);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MasterLiveListActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        masterworklist_recyclerview.setLayoutManager(linearLayoutManager);
                        datalist.addAll(detailBean.getData().getList());
                        adapter=new LivingListAtyAdapter(MasterLiveListActivity.this, R.layout.masterlive_list_aty_item, datalist);
                        adapter.notifyDataSetChanged();
                        masterworklist_recyclerview.setAdapter(adapter);

                    }
                });
            }
        });
    }
}
