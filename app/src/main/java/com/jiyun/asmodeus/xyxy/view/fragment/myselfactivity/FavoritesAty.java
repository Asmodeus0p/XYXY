package com.jiyun.asmodeus.xyxy.view.fragment.myselfactivity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.R;

import com.jiyun.asmodeus.xyxy.view.adapter.FavoritesAdapter;
import com.jiyun.asmodeus.xyxy.view.fragment.FavoritesFragment;
import com.jiyun.asmodeus.xyxy.view.ui.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FavoritesAty extends AppCompatActivity {


    FavoritesFragment favoritesFragment;
    List<Fragment> mList;
    List<String> mTitle;
    FavoritesAdapter adapter;
    @BindView(R.id.my_collect_aty_title_cancle)
    TextView myCollectAtyTitleCancle;
    @BindView(R.id.favorites_tab)
    TabLayout favoritesTab;
    @BindView(R.id.favorites_ViewPager)
    CustomViewPager favoritesViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_aty);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mList = new ArrayList<>();
        mTitle = new ArrayList<>();
        mTitle.add("直播课程");
        mTitle.add("体验课程");
        mTitle.add("偷听作业");
        mTitle.add("帖子");
        for (int i = 0; i < mTitle.size(); i++) {
            // favotiteTiyaFragment=new FavotiteTiyaFragment();
            favoritesFragment = new FavoritesFragment();
            mList.add(favoritesFragment);
        }

        adapter = new FavoritesAdapter(getSupportFragmentManager(), mTitle, mList);
        favoritesTab.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#0ddcff"));
        favoritesViewPager.setAdapter(adapter);
        favoritesTab.setupWithViewPager(favoritesViewPager);

    }


    @OnClick(R.id.my_collect_aty_title_cancle)
    public void onViewClicked() {
        finish();
    }
}
