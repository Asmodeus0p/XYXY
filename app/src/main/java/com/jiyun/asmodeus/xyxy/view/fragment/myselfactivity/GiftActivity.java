package com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.view.adapter.GiftViewPagerAdapter;
import com.jiyun.asmodeus.xyxy.view.fragment.DingdanFragment;
import com.jiyun.asmodeus.xyxy.view.fragment.GiftFragment;

import java.util.ArrayList;
import java.util.List;

public class GiftActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView gift_back;
    private TabLayout gifiTab;
    private ViewPager giftViewPager;
    private List<String> mTitlts;
    private List<Fragment> mList;
    private GiftFragment giftFragment;
    private GiftViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);
        initView();
    }

    private void initView() {
        mTitlts=new ArrayList<>();
        mList=new ArrayList<>();
        gift_back = (TextView) findViewById(R.id.gift_back);
        gifiTab = (TabLayout) findViewById(R.id.gifiTab);
        giftViewPager = (ViewPager) findViewById(R.id.giftViewPager);
        gift_back.setOnClickListener(this);
        mTitlts.add("收到礼物");
        mTitlts.add("现金收入");
        for (int i = 0; i < mTitlts.size(); i++) {
             giftFragment = new GiftFragment();
             mList.add(giftFragment);
        }
        adapter=new GiftViewPagerAdapter(getSupportFragmentManager(),mTitlts,mList);
        giftViewPager.setAdapter(adapter);
        gifiTab.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#0ddcff"));
        gifiTab.setupWithViewPager(giftViewPager);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gift_back:
                finish();
                break;
        }
    }
}
