package com.jiyun.asmodeus.xyxy.view;

import android.graphics.Color;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.view.base.BaseActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.MingShiFragment;
import com.jiyun.asmodeus.xyxy.view.fragment.MyselfFragment;
import com.jiyun.asmodeus.xyxy.view.fragment.NoticeFragment;
import com.jiyun.asmodeus.xyxy.view.fragment.ValuableFragment;
import com.jiyun.asmodeus.xyxy.view.fragment.WorkFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private RadioButton mingshi;
    private RadioButton work;
    private RadioButton valuable;
    private RadioButton notice;
    private RadioButton myself;
    private ImageView set;
    private ImageView message;
    private ImageView titleimage;
    private RelativeLayout tab;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        titleimage = findViewById(R.id.titleimage);
        tab = findViewById(R.id.tab);
        set = findViewById(R.id.set);
        message = findViewById(R.id.message);
        mingshi = (RadioButton) findViewById(R.id.mingshi);
        work = (RadioButton) findViewById(R.id.work);
        valuable = (RadioButton) findViewById(R.id.valuable);
        notice = (RadioButton) findViewById(R.id.notice);
        myself = (RadioButton) findViewById(R.id.myself);
        mingshi.setOnClickListener(this);
        work.setOnClickListener(this);
        valuable.setOnClickListener(this);
        notice.setOnClickListener(this);
        myself.setOnClickListener(this);
        set.setOnClickListener(this);
        message.setOnClickListener(this);
        setContentView(MingShiFragment.class);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mingshi:
                setContentView(MingShiFragment.class);
                set.setVisibility(View.GONE);
                titleimage.setVisibility(View.VISIBLE);
                message.setVisibility(View.VISIBLE);
                tab.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case R.id.work:
                setContentView(WorkFragment.class);
                set.setVisibility(View.GONE);
                titleimage.setVisibility(View.VISIBLE);
                message.setVisibility(View.GONE);
                tab.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case R.id.valuable:
                setContentView(ValuableFragment.class);
                set.setImageResource(R.mipmap.publish_valuable);
                set.setVisibility(View.VISIBLE);
                titleimage.setVisibility(View.VISIBLE);
                message.setVisibility(View.VISIBLE);
                tab.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case R.id.notice:
                setContentView(NoticeFragment.class);
                set.setVisibility(View.GONE);
                titleimage.setVisibility(View.VISIBLE);
                message.setVisibility(View.VISIBLE);
                tab.setBackgroundColor(Color.parseColor("#FFFFFF"));
                break;
            case R.id.myself:
                setContentView(MyselfFragment.class);

                message.setVisibility(View.VISIBLE);
                titleimage.setVisibility(View.GONE);
                tab.setBackgroundResource(R.mipmap.myself_head_bg1);
                break;
            case R.id.set:

                break;
            case R.id.message:

                break;
        }
    }
}
