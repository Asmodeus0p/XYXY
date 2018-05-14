package com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.contract.AiliPayContract;
import com.jiyun.asmodeus.xyxy.contract.CenterContract;
import com.jiyun.asmodeus.xyxy.model.biz.AliPayService;
import com.jiyun.asmodeus.xyxy.model.entity.CenterBean;
import com.jiyun.asmodeus.xyxy.model.entity.NoticeDetailOrderModel;
import com.jiyun.asmodeus.xyxy.model.entity.OrderNoBean;
import com.jiyun.asmodeus.xyxy.model.http.HttpHelp;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.presenter.AiliPayPrensenterImp;
import com.jiyun.asmodeus.xyxy.presenter.CenerPresenterImp;
import com.jiyun.asmodeus.xyxy.view.adapter.VoucherCenterAdapter;
import com.jiyun.asmodeus.xyxy.view.fragment.MyselfFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class VoucherCenterActivity extends AppCompatActivity implements View.OnClickListener, CenterContract.VenterView ,AiliPayContract.getOrderNoView{
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
    private View view;
    private PopupWindow popupWindow;
    private LinearLayout alipay;
    private LinearLayout weixinpay;
    private String order;
    private AiliPayPrensenterImp payPrensenterImp;
    private Button cencle;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what==0) {
             String s= (String) msg.obj;
                Toast.makeText(VoucherCenterActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        };
    };
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
        payPrensenterImp=new AiliPayPrensenterImp(this);
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

        adapter = new VoucherCenterAdapter(data);
        recharge_center_aty_recyclerview.setAdapter(adapter);
        //item监听 在里面调用AiPay代码

        adapter.setClick(new VoucherCenterAdapter.OnClick() {
            @Override
            public void setOnClickListener(View v, int postion) {
                    initPopuwindow();
            }
        });




    }

    public  void initPopuwindow(){
        //弹起支付面板

        view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.app_pay_item, null);

        alipay= view.findViewById(R.id.pop_window_pay_alipay_group);

        weixinpay=view.findViewById(R.id.pop_window_pay_weixin_group);


        cencle=view.findViewById(R.id.alert_wok_valuable_bottom_cancle);
        popupWindow = new PopupWindow(view, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ADADAD")));
        popupWindow.showAtLocation(findViewById(R.id.recharge_center_aty_recyclerview), Gravity.BOTTOM,0,0);

        alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payPrensenterImp.sendOrderNo(881,1,10);


            }
        });
       cencle.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               popupWindow.dismiss();
           }
       });

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
        adapter.notifyDataSetChanged();

    }

    @Override
    public void getOrderNoBean(NoticeDetailOrderModel noticeDetailOrderModel) {
        order = noticeDetailOrderModel.getData().getOrderNo();
        payPrensenterImp.getOrderNo(order);
    }

    @Override
    public void getOrderNoB(OrderNoBean orderNoBean) {
        final String orderInfo =  orderNoBean.getData();
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(VoucherCenterActivity.this);
                String result = String.valueOf(alipay.payV2(orderInfo,true));

                Message msg = new Message();
                msg.what = 0;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }





}
