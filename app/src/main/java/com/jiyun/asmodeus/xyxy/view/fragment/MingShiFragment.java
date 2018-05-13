package com.jiyun.asmodeus.xyxy.view.fragment;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.contract.Teachercontract;
import com.jiyun.asmodeus.xyxy.model.entity.HomeBean;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.presenter.TeacherPresenterImp;
import com.jiyun.asmodeus.xyxy.view.MainActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.homeactivity.LivingListActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.homeactivity.MasterDetailActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.homeactivity.MasterFindActivity;
import com.jiyun.asmodeus.xyxy.view.adapter.HomeMasterLiveGridViewAdapter;
import com.jiyun.asmodeus.xyxy.view.adapter.HomeMasterLiveListViewAdapter;
import com.jiyun.asmodeus.xyxy.view.adapter.HomeMasterWorkListViewAdapter;
import com.jiyun.asmodeus.xyxy.view.adapter.HomeRecommendMasterAdapter;
import com.jiyun.asmodeus.xyxy.view.base.BaseFragment;
import com.jiyun.asmodeus.xyxy.view.fragment.homeactivity.MasterLiveDetailActivity;
import com.jiyun.asmodeus.xyxy.view.fragment.homeactivity.WorkDataActivity;
import com.jiyun.asmodeus.xyxy.view.ui.MyGridView;
import com.jiyun.asmodeus.xyxy.view.ui.MyListView;
import com.jiyun.asmodeus.xyxy.view.ui.MyScrollView;
import com.recker.flybanner.FlyBanner;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MingShiFragment extends BaseFragment implements Teachercontract.TeacherView, View.OnClickListener {
    private CompositeDisposable compositeDisposable;

    private List<HomeBean.DataBean.SystemAdsBean> systemAds = new ArrayList<>();

    private List<HomeBean.DataBean.LivesBean> livesbean = new ArrayList<>();

    private HomeMasterLiveListViewAdapter liveAdapter;

    private List<HomeBean.DataBean.UsersBean> usersbean = new ArrayList<>();

    private HomeRecommendMasterAdapter usersAdapter;

    private List<HomeBean.DataBean.LiveCoursesBean> liveCoursesBeen = new ArrayList<>();

    private HomeMasterLiveGridViewAdapter liveCoursesAdapter;

    private List<HomeBean.DataBean.HomewoksBean> homewoksBeen = new ArrayList<>();

    private HomeMasterWorkListViewAdapter homewoksAdapter;
    private TeacherPresenterImp presenterImp;
    private FlyBanner home_master_adv_viewpager;
    private LinearLayout home_master_find_group;
    private LinearLayout home_master_look_group;
    private LinearLayout home_master_work_group;
    private LinearLayout home_master_chat_group;
    private LinearLayout home_master_learn_group;
    private MyListView home_master_live_listview;
    private TextView homne_master_recommend_more;
    private RecyclerView homne_master_recommend_recyclerview;
    private TextView home_master_live_more;
    private MyGridView home_master_live_gridview;
    private TextView home_master_fragment_workmore;
    private MyListView home_master_work_listview;
    private RelativeLayout home_master_fragment_chatvaluable;
    private MyScrollView home_master_fragment_scrollview;
    private SmartRefreshLayout home_master_fragment_swipe;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ming_shi;
    }

    @Override
    protected void init() {
        presenterImp = new TeacherPresenterImp(this);
        presenterImp.laodTeacherDatas(0);
        home_master_adv_viewpager = getActivity().findViewById(R.id.home_master_adv_viewpager);
        home_master_find_group = getActivity().findViewById(R.id.home_master_find_group);
        home_master_look_group = getActivity().findViewById(R.id.home_master_look_group);
        home_master_work_group = getActivity().findViewById(R.id.home_master_work_group);
        home_master_chat_group = getActivity().findViewById(R.id.home_master_chat_group);
        home_master_learn_group = getActivity().findViewById(R.id.home_master_learn_group);
        home_master_live_listview = getActivity().findViewById(R.id.home_master_live_listview);
        homne_master_recommend_more = getActivity().findViewById(R.id.homne_master_recommend_more);
        homne_master_recommend_recyclerview = getActivity().findViewById(R.id.homne_master_recommend_recyclerview);
        home_master_live_more = getActivity().findViewById(R.id.home_master_live_more);
        home_master_fragment_workmore = getActivity().findViewById(R.id.home_master_fragment_workmore);
        home_master_live_gridview = getActivity().findViewById(R.id.home_master_live_gridview);
        home_master_work_listview = getActivity().findViewById(R.id.home_master_work_listview);
        home_master_fragment_chatvaluable = getActivity().findViewById(R.id.home_master_fragment_chatvaluable);
        home_master_fragment_scrollview = getActivity().findViewById(R.id.home_master_fragment_scrollview);
        home_master_fragment_swipe = getActivity().findViewById(R.id.home_master_fragment_swipe);
        home_master_find_group.setOnClickListener(this);
        home_master_look_group.setOnClickListener(this);
        home_master_work_group.setOnClickListener(this);
        home_master_chat_group.setOnClickListener(this);

        home_master_learn_group.setOnClickListener(this);
    }

    @Override
    protected void loadDatas() {

    }

    @Override
    public void laodTeacherDatas(HomeBean homeBean) {
        systemAds.addAll(homeBean.getData().getSystemAds());
        ArrayList<String> urls = new ArrayList<>();
        for (HomeBean.DataBean.SystemAdsBean systemAd : systemAds) {
            urls.add(systemAd.getMobileImgUrl());
        }
        home_master_adv_viewpager.setImagesUrl(urls);
        usersAdapter = new HomeRecommendMasterAdapter(usersbean);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        homne_master_recommend_recyclerview.setLayoutManager(linearLayoutManager);
        usersbean.addAll(homeBean.getData().getUsers());
        usersAdapter.notifyDataSetChanged();
        homne_master_recommend_recyclerview.setAdapter(usersAdapter);
        usersAdapter.setmOnItemClickListener(new HomeRecommendMasterAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (usersbean.isEmpty()) {
                    return;
                }
                int id = usersbean.get(position).getId();
                Intent intent = new Intent(getContext(), MasterDetailActivity.class);
                intent.putExtra(Constant.Teacher_id, id);
                startActivity(intent);

            }
        });

//        //直播课程
        liveCoursesBeen.addAll(homeBean.getData().getLiveCourses());
        liveCoursesAdapter = new HomeMasterLiveGridViewAdapter(getContext(), liveCoursesBeen);
        home_master_live_gridview.setAdapter(liveCoursesAdapter);
        liveCoursesAdapter.notifyDataSetChanged();
        home_master_live_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), MasterLiveDetailActivity.class);
                int id1 = liveCoursesBeen.get(position).getId();
                intent.putExtra(Constant.Teacher_id, id1);
                startActivity(intent);
            }
        });
        //推荐作业


        homewoksBeen.addAll(homeBean.getData().getHomewoks());
        homewoksAdapter = new HomeMasterWorkListViewAdapter(getContext(), homewoksBeen);
        home_master_work_listview.setAdapter(homewoksAdapter);
        home_master_work_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (homewoksBeen.isEmpty()) {
                    return;
                }
                WorkDataActivity.start((Activity) getActivity(),homewoksBeen.get(position).getId());

            }
        });
        homewoksAdapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_master_find_group:
                startActivity(new Intent(getContext(), MasterFindActivity.class));

                break;
            case R.id.home_master_look_group:
                startActivity(new Intent(getActivity(), LivingListActivity.class));
                break;
            case R.id.home_master_work_group:
                ((MainActivity) getActivity()).getWork().setChecked(true);
                ((MainActivity) getActivity()).setContentView(WorkFragment.class);
                break;
            case R.id.home_master_chat_group:
                ((MainActivity) getActivity()).getValuable().setChecked(true);
                ((MainActivity) getActivity()).setContentView(ValuableFragment.class);
                break;
            case R.id.home_master_learn_group:
                ((MainActivity) getActivity()).getNotice().setChecked(true);
                ((MainActivity) getActivity()).setContentView(NoticeFragment.class);
                break;
        }
    }
}
