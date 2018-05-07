package com.jiyun.asmodeus.xyxy.view.fragment;


import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.contract.WorkContract;
import com.jiyun.asmodeus.xyxy.model.entity.HomeBean;
import com.jiyun.asmodeus.xyxy.model.entity.WorkBean;

import com.jiyun.asmodeus.xyxy.presenter.WorkPresenterImp;
import com.jiyun.asmodeus.xyxy.view.adapter.HomeWorkListViewAdapter;
import com.jiyun.asmodeus.xyxy.view.base.BaseFragment;
import com.jiyun.asmodeus.xyxy.view.ui.LoadMoreListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkFragment extends BaseFragment implements WorkContract.WorkView, View.OnClickListener {
    private CompositeDisposable compositeDisposable;

    private HomeWorkListViewAdapter adapter;
    private LinearLayout home_work_fragment_publishwok_group;
    private LinearLayout home_work_fragment_publishaskwok_group;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsing;
    private TextView home_work_fragment_capacity_tv;
    private TextView home_work_fragment_capacity_line;
    private RelativeLayout home_work_fragment_capacity_group;
    private TextView home_work_fragment_listen_tv;
    private TextView home_work_fragment_listen_line;
    private RelativeLayout home_work_fragment_listen_group;
    private TextView home_work_fragment_comment_tv;
    private TextView home_work_fragment_comment_line;
    private RelativeLayout home_work_fragment_comment_group;
    private AppBarLayout home_work_fragment_appbar;
    private LoadMoreListView home_work_fragment_listview;
    private FrameLayout home_work_fragment_listview_load_more;
    private RelativeLayout home_work_fragment_listview_empty;
    private Button home_work_fragment_listview_fault_btn;
    private RelativeLayout home_work_fragment_listview_fault;
    private NestedScrollView home_work_fragment_nestscroll;
    private SmartRefreshLayout home_work_fragment_swipe;
    private List<WorkBean.DataBean.ListBean> homewoksBeen= new ArrayList<>();;
    private List<WorkBean.DataBean.ListBean> homewoksBeen1= new ArrayList<>();;
    private List<WorkBean.DataBean.ListBean> homewoksBeen2= new ArrayList<>();;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_work;
    }

    @Override
    protected void init() {
        WorkPresenterImp presenterImp = new WorkPresenterImp(this);
        presenterImp.GetWorkZhiNeng();
        presenterImp.GetWorkTouTing();
        presenterImp.GetWorkDiamPing();
        compositeDisposable = new CompositeDisposable();
        adapter = new HomeWorkListViewAdapter(App.context, homewoksBeen);
        home_work_fragment_publishwok_group = getActivity().findViewById(R.id.home_work_fragment_publishwok_group);
        home_work_fragment_publishaskwok_group = getActivity().findViewById(R.id.home_work_fragment_publishaskwok_group);
        toolbar = getActivity().findViewById(R.id.toolbar);
        collapsing = getActivity().findViewById(R.id.collapsing);
        home_work_fragment_capacity_tv = getActivity().findViewById(R.id.home_work_fragment_capacity_tv);
        home_work_fragment_capacity_line = getActivity().findViewById(R.id.home_work_fragment_capacity_line);
        home_work_fragment_capacity_group = getActivity().findViewById(R.id.home_work_fragment_capacity_group);
        home_work_fragment_listen_tv = getActivity().findViewById(R.id.home_work_fragment_listen_tv);
        home_work_fragment_listen_line = getActivity().findViewById(R.id.home_work_fragment_listen_line);
        home_work_fragment_listen_group = getActivity().findViewById(R.id.home_work_fragment_listen_group);
        home_work_fragment_comment_tv = getActivity().findViewById(R.id.home_work_fragment_comment_tv);
        home_work_fragment_comment_line = getActivity().findViewById(R.id.home_work_fragment_comment_line);
        home_work_fragment_comment_group = getActivity().findViewById(R.id.home_work_fragment_comment_group);
        home_work_fragment_appbar = getActivity().findViewById(R.id.home_work_fragment_appbar);
        home_work_fragment_listview = getActivity().findViewById(R.id.home_work_fragment_listview);
        home_work_fragment_listview_load_more = getActivity().findViewById(R.id.home_work_fragment_listview_load_more);
        home_work_fragment_listview_empty = getActivity().findViewById(R.id.home_work_fragment_listview_empty);
        home_work_fragment_listview_fault = getActivity().findViewById(R.id.home_work_fragment_listview_fault);
        home_work_fragment_listview_fault_btn = getActivity().findViewById(R.id.home_work_fragment_listview_fault_btn);
        home_work_fragment_nestscroll = getActivity().findViewById(R.id.home_work_fragment_nestscroll);
        home_work_fragment_swipe = getActivity().findViewById(R.id.home_work_fragment_swipe);
        home_work_fragment_capacity_group.setOnClickListener(this);
        home_work_fragment_listen_group.setOnClickListener(this);
        home_work_fragment_comment_group.setOnClickListener(this);


    }

    @Override
    protected void loadDatas() {

    }

    @Override
    public void laodWorkZhiNeng(WorkBean workBean) {
        homewoksBeen.addAll(workBean.getData().getList());
        adapter = new HomeWorkListViewAdapter(App.context,homewoksBeen);
        home_work_fragment_listview.setAdapter(adapter);
    }

    @Override
    public void laodWorkTouTing(WorkBean workBean) {
        homewoksBeen1.addAll(workBean.getData().getList());
    }

    @Override
    public void laodWorkDianPing(WorkBean workBean) {
        homewoksBeen2.addAll(workBean.getData().getList());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_work_fragment_capacity_group:
                home_work_fragment_capacity_line.setVisibility(View.VISIBLE);
                home_work_fragment_listen_line.setVisibility(View.INVISIBLE);
                home_work_fragment_comment_line.setVisibility(View.INVISIBLE);
                adapter = new HomeWorkListViewAdapter(App.context,homewoksBeen);
                home_work_fragment_listview.setAdapter(adapter);
                break;
            case R.id.home_work_fragment_listen_group:
                home_work_fragment_capacity_line.setVisibility(View.INVISIBLE);
                home_work_fragment_listen_line.setVisibility(View.VISIBLE);
                home_work_fragment_comment_line.setVisibility(View.INVISIBLE);
                adapter = new HomeWorkListViewAdapter(App.context,homewoksBeen1);
                home_work_fragment_listview.setAdapter(adapter);
                break;
            case R.id.home_work_fragment_comment_group:
                home_work_fragment_capacity_line.setVisibility(View.INVISIBLE);
                home_work_fragment_listen_line.setVisibility(View.INVISIBLE);
                home_work_fragment_comment_line.setVisibility(View.VISIBLE);
                adapter = new HomeWorkListViewAdapter(App.context,homewoksBeen2);
                home_work_fragment_listview.setAdapter(adapter);
                break;
        }
    }
}
