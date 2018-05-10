package com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.view.adapter.DingdanViewPagerAdapter;
import com.jiyun.asmodeus.xyxy.view.fragment.DingdanFragment;

import java.util.ArrayList;
import java.util.List;

public class StudentDingdanActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView back;
    private TabLayout dingdanTab;
    private DingdanFragment dingdanFragment;
    private List<String> mTitles;
    private List<Fragment> mList;
    private DingdanViewPagerAdapter adapter;
    private ViewPager dingdanViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dingdan);
        initView();
    }

    private void initView() {
        mTitles = new ArrayList<>();
        mList = new ArrayList<>();
        back = (TextView) findViewById(R.id.back);
        dingdanTab = (TabLayout) findViewById(R.id.dingdanTab);
        dingdanViewPager = (ViewPager) findViewById(R.id.dingdanViewPager);
        back.setOnClickListener(this);
        mTitles.add("全部");
        mTitles.add("代付款");
        mTitles.add("待使用");
        mTitles.add("退货");
        for (int i = 0; i < mTitles.size(); i++) {
            dingdanFragment = new DingdanFragment();
            mList.add(dingdanFragment);
        }
        adapter = new DingdanViewPagerAdapter(getSupportFragmentManager(), mList, mTitles);
        dingdanViewPager.setAdapter(adapter);
        dingdanTab.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#0ddcff"));
        dingdanTab.setupWithViewPager(dingdanViewPager);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        LinearLayout viewById = getFragmentManager().findFragmentById(R.id.myselfFragment)
//                .getView().findViewById(R.id.home_myselft_fragment_student_fukuan);
//        viewById.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dingdanViewPager.setCurrentItem(0);
//            }
//        });
//    }
}
