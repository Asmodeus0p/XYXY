package com.jiyun.asmodeus.xyxy.view.fragment.homeactivity;



import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.entity.MasterListAtyBean;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;
import com.jiyun.asmodeus.xyxy.view.adapter.MasterListAtyAdapter;
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

public class MasterFindActivity extends BaseActivity implements View.OnClickListener {

    private List<MasterListAtyBean.DataBean.ListBean> masterwoksBeen ;


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


    private int page = 1;


    private MasterListAtyAdapter adapter;

    private GridLayoutManager gridLayoutManager;

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
        masterlist_title_cancle.setOnClickListener(this);
        masterlist_title_group = findViewById(R.id.masterlist_title_group);
        master_list_level3_tv = findViewById(R.id.master_list_level3_tv);
        master_list_level3_line = findViewById(R.id.master_list_level3_line);
        master_list_level3 = findViewById(R.id.master_list_level3);
        master_list_level2 = findViewById(R.id.master_list_level2);
        master_list_level2_tv = findViewById(R.id.master_list_level2_tv);
        master_list_level2_line = findViewById(R.id.master_list_level2_line);
        master_list_level1_tv = findViewById(R.id.master_list_level1_tv);
        master_list_level1_line = findViewById(R.id.master_list_level1_line);
        master_list_level1 = findViewById(R.id.master_list_level1);
        masterlist_recyclerview = findViewById(R.id.masterlist_recyclerview);
        masterlist_recyclerview_empty = findViewById(R.id.masterlist_recyclerview_empty);
        masterlist_recyclerview_fault_btn = findViewById(R.id.masterlist_recyclerview_fault_btn);
        master_list_level3.setOnClickListener(this);
        master_list_level2.setOnClickListener(this);
        master_list_level1.setOnClickListener(this);
        GetData(SORT_TYPE_DASHI);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.masterlist_title_cancle:
                    finish();
                break;
            case R.id.master_list_level3:
                GetData(SORT_TYPE_DASHI);
                master_list_level3_line.setVisibility(View.VISIBLE);
                master_list_level1_line.setVisibility(View.INVISIBLE);
                master_list_level2_line.setVisibility(View.INVISIBLE);
                break;
            case R.id.master_list_level2:
                GetData(SORT_TYPE_MINGSHI);
                master_list_level3_line.setVisibility(View.INVISIBLE);
                master_list_level1_line.setVisibility(View.INVISIBLE);
                master_list_level2_line.setVisibility(View.VISIBLE);
                break;
            case R.id.master_list_level1:
                GetData(SORT_TYPE_DAREN);
                master_list_level3_line.setVisibility(View.INVISIBLE);
                master_list_level1_line.setVisibility(View.VISIBLE);
                master_list_level2_line.setVisibility(View.INVISIBLE);
                break;
        }
    }


    private void GetData(String userType) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        String appToken = (String) SharedPreferencesUtils.getParam(this, "xyxy_apptoken", "String");
        FormBody body = new FormBody.Builder().add("userType", userType).build();
        Request build = new Request.Builder().url("https://www.univstar.com/v1/m/user/teacher/list").post(body).addHeader("apptoken", appToken).build();
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
                        masterwoksBeen = new ArrayList<>();
                        MasterListAtyBean masterListAtyBean = new Gson().fromJson(string, MasterListAtyBean.class);
                        masterwoksBeen.addAll(masterListAtyBean.getData().getList());
                         adapter = new MasterListAtyAdapter(MasterFindActivity.this,R.layout.masterlistaty_listview_item, masterwoksBeen);
                        gridLayoutManager =new GridLayoutManager(MasterFindActivity.this,2,GridLayoutManager.VERTICAL,false);;
                        masterlist_recyclerview.setLayoutManager(gridLayoutManager);
                        masterlist_recyclerview.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                if(masterwoksBeen.isEmpty()){
                                    return;
                                }
                                Intent intent = new Intent(MasterFindActivity.this, MasterDetailActivity.class);
                                intent.putExtra("id",masterwoksBeen.get(position).getId()) ;
                                startActivity(intent);
                            }
                        });
                    }
                });

            }
        });
    }


}
