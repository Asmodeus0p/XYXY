package com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.contract.CenterContract;
import com.jiyun.asmodeus.xyxy.model.entity.CenterBean;
import com.jiyun.asmodeus.xyxy.presenter.CenerPresenterImp;
import com.jiyun.asmodeus.xyxy.view.adapter.VoucherCenterAdapter;
import com.jiyun.asmodeus.xyxy.view.fragment.MyselfFragment;

import java.util.ArrayList;
import java.util.List;

public class VoucherCenterActivity extends AppCompatActivity implements View.OnClickListener, CenterContract.VenterView {
    private VoucherCenterAdapter adapter;
    private TextView recharge_center_aty_bottom;
    private TextView recharge_center_aty_cancle;
    private TextView recharge_center_aty_zhangdan;
    private TextView zhanghao_tv;
    private TextView recharge_center_aty_yue_tv;
    private RecyclerView recharge_center_aty_recyclerview;
    private RelativeLayout recharge_center_aty_group;
    List<CenterBean.DataBean> data;
    private CenerPresenterImp presenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher_center);
        initView();
        init();
    }

    @SuppressLint("WrongConstant")
    private void init() {


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String mobile = bundle.getString("mobile");
        zhanghao_tv.setText(mobile);
    }

    private void initView() {
        recharge_center_aty_bottom = findViewById(R.id.recharge_center_aty_bottom);
        recharge_center_aty_cancle = findViewById(R.id.recharge_center_aty_cancle);
        recharge_center_aty_zhangdan = findViewById(R.id.recharge_center_aty_zhangdan);
        zhanghao_tv = findViewById(R.id.recharge_center_aty_zhanghao_tv);
        recharge_center_aty_yue_tv = findViewById(R.id.recharge_center_aty_yue_tv);
        recharge_center_aty_recyclerview = findViewById(R.id.recharge_center_aty_recyclerview);
        recharge_center_aty_group = findViewById(R.id.recharge_center_aty_group);
        recharge_center_aty_cancle.setOnClickListener(this);


        data = new ArrayList<>();


        presenterImp = new CenerPresenterImp(this);
        presenterImp.getCenter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recharge_center_aty_recyclerview.setLayoutManager(manager);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recharge_center_aty_cancle:
                finish();
                break;
        }
    }

    @Override
    public void getCenterPassBean(CenterBean centerBean) {
        Log.e("==", centerBean.getData().size() + "");
        data.addAll(centerBean.getData());
        adapter = new VoucherCenterAdapter(data);
        recharge_center_aty_recyclerview.setAdapter(adapter);
    }
}
