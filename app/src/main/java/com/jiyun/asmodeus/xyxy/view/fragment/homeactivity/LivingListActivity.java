package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.LivingListBean;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;
import com.jiyun.asmodeus.xyxy.view.adapter.LivingListAtyAdapter;
import com.jiyun.asmodeus.xyxy.view.base.BaseActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LivingListActivity extends BaseActivity implements View.OnClickListener {

    private static final String teacher = "讲堂";
    private static final String master = "私塾";
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
    private List<LivingListBean.DataBean.ListBean> datalist = new ArrayList<>();
    private LivingListAtyAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_living_list;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
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
        livinglist_aty_title_cancle.setOnClickListener(this);
        livinglist_aty_level1.setOnClickListener(this);
        livinglist_aty_level2.setOnClickListener(this);
        livinglist_aty_level1_line.setVisibility(View.VISIBLE);
        LoadRecyData(teacher);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.livinglist_aty_title_cancle:
                finish();
                break;
            case R.id.livinglist_aty_level1:
                LoadRecyData(teacher);
                livinglist_aty_level1_line.setVisibility(View.VISIBLE);
                livinglist_aty_level2_line.setVisibility(View.GONE);
                break;
            case R.id.livinglist_aty_level2:
                LoadRecyData(master);
                livinglist_aty_level1_line.setVisibility(View.GONE);
                livinglist_aty_level2_line.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void LoadRecyData(String type) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        String appToken = (String) SharedPreferencesUtils.getParam(this, "xyxy_apptoken", "String");
        FormBody typeq = new FormBody.Builder().add("type", type).build();
        Request build = new Request.Builder().url("https://www.univstar.com/v1/m/liveCourse/list").post(typeq).addHeader("apptoken", appToken).build();
        client.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        LivingListBean livingListBean = new Gson().fromJson(string, LivingListBean.class);
                        datalist.addAll(livingListBean.getData().getList());
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LivingListActivity.this);
                        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        livinglist_aty_recyclerview.setLayoutManager(linearLayoutManager);
                        adapter = new LivingListAtyAdapter(LivingListActivity.this, R.layout.masterlive_list_aty_item, datalist);
                        livinglist_aty_recyclerview.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        if (livingListBean.getData().getList().size()==0) {
                            livinglist_aty_recyclerview_empty.setVisibility(View.VISIBLE);
                        }else {
                            livinglist_aty_recyclerview_empty.setVisibility(View.GONE);
                        }
                    }
                    //asdfghj
                });

            }
        });

    }
}
