package com.jiyun.asmodeus.xyxy.view;

import android.graphics.Color;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.view.base.BaseActivity;
import com.jiyun.asmodeus.xyxy.view.base.BaseFragment;
import com.jiyun.asmodeus.xyxy.view.fragment.MingShiFragment;
import com.jiyun.asmodeus.xyxy.view.fragment.MyselfFragment;
import com.jiyun.asmodeus.xyxy.view.fragment.NoticeFragment;
import com.jiyun.asmodeus.xyxy.view.fragment.ValuableFragment;
import com.jiyun.asmodeus.xyxy.view.fragment.WorkFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout maina;
    private RadioButton mingshi;
    private RadioButton work;
    private RadioButton valuable;
    private RadioButton notice;
    private RadioButton myself;
    private RadioGroup radiogroup;
    private ImageView set;
    private ImageView message;
    private ImageView titleimage;
    private RelativeLayout tab;
    private FrameLayout fragmentcontainer;
    private LinearLayout lin;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    public RadioButton getMingshi() {
        return mingshi;
    }

    public RadioButton getWork() {
        return work;
    }

    public RadioButton getValuable() {
        return valuable;
    }

    public RadioButton getNotice() {
        return notice;
    }

    @Override
    protected void initView() {
        lin=findViewById(R.id.lin);
        maina = findViewById(R.id.maina);
        fragmentcontainer = findViewById(R.id.fragmentcontainer);
        titleimage = findViewById(R.id.titleimage);
        radiogroup = findViewById(R.id.radiogroup);
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
                tab.setVisibility(View.VISIBLE);
                SetParams();

                break;
            case R.id.work:
                this.setContentView(WorkFragment.class);
                set.setVisibility(View.GONE);
                titleimage.setVisibility(View.VISIBLE);
                message.setVisibility(View.VISIBLE);
                tab.setVisibility(View.VISIBLE);
                SetParams();
                break;
            case R.id.valuable:
                setContentView(ValuableFragment.class);
                set.setImageResource(R.mipmap.publish_valuable);
                set.setVisibility(View.VISIBLE);
                titleimage.setVisibility(View.VISIBLE);
                message.setVisibility(View.VISIBLE);
                tab.setVisibility(View.VISIBLE);
                SetParams();
                break;
            case R.id.notice:
                setContentView(NoticeFragment.class);
                set.setVisibility(View.GONE);
                titleimage.setVisibility(View.VISIBLE);
                message.setVisibility(View.VISIBLE);
                tab.setVisibility(View.VISIBLE);
                SetParams();
                break;
            case R.id.myself:
                setContentView(MyselfFragment.class);
                message.setVisibility(View.GONE);
                titleimage.setVisibility(View.GONE);
                tab.setVisibility(View.GONE);
                maina.setWeightSum(11.0f);
                fragmentcontainer.setLayoutParams(new LinearLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, 0, 10.0f));
                lin.setLayoutParams( new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 0, 1.0f));
                break;
            case R.id.set:

                break;
            case R.id.message:

                break;
        }
    }

    private void SetParams() {
        tab.setBackgroundColor(Color.parseColor("#FFFFFF"));
        maina.setWeightSum(11.0f);
        tab.setLayoutParams(new LinearLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, 0, 1.0f));
        fragmentcontainer.setLayoutParams(new LinearLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, 0, 9.0f));
        lin.setLayoutParams( new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 0, 1.0f));
    }


}
