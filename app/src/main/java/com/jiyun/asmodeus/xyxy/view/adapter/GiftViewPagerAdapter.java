package com.jiyun.asmodeus.xyxy.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class GiftViewPagerAdapter extends FragmentPagerAdapter {
    private List<String> mTitles;
    private List<Fragment> mList;

    public GiftViewPagerAdapter(FragmentManager fm,List<String> mTitles,List<Fragment> mList) {
        super(fm);
        this.mTitles=mTitles;
        this.mList=mList;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
