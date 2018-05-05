package com.jiyun.asmodeus.xyxy.view.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.contract.Teachercontract;
import com.jiyun.asmodeus.xyxy.model.entity.HomeBean;
import com.jiyun.asmodeus.xyxy.presenter.TeacherPresenterImp;
import com.jiyun.asmodeus.xyxy.view.adapter.HomeMasterLiveGridViewAdapter;
import com.jiyun.asmodeus.xyxy.view.adapter.HomeMasterLiveListViewAdapter;
import com.jiyun.asmodeus.xyxy.view.adapter.HomeMasterWorkListViewAdapter;
import com.jiyun.asmodeus.xyxy.view.adapter.HomeRecommendMasterAdapter;
import com.jiyun.asmodeus.xyxy.view.base.BaseFragment;
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
public class MingShiFragment extends BaseFragment implements Teachercontract.TeacherView {
    private CompositeDisposable compositeDisposable;

    private List<HomeBean.DataBean.SystemAdsBean> systemAds;

    private List<HomeBean.DataBean.LivesBean> livesbean;

    private HomeMasterLiveListViewAdapter liveAdapter;

    private List<HomeBean.DataBean.UsersBean> usersbean;

    private HomeRecommendMasterAdapter usersAdapter;

    private List<HomeBean.DataBean.LiveCoursesBean> liveCoursesBeen;

    private HomeMasterLiveGridViewAdapter liveCoursesAdapter;

    private List<HomeBean.DataBean.HomewoksBean> homewoksBeen;

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

    public MingShiFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ming_shi;
    }

    @Override
    protected void init() {
        presenterImp = new TeacherPresenterImp(this);
        presenterImp.laodTeacherDatas(0);
        systemAds = new ArrayList<>();



    }

    @Override
    protected void loadDatas() {

    }

    @Override
    public void laodTeacherDatas(HomeBean homeBean) {
        if (homeBean == null || homeBean.getData() == null) {
            return;
        }
        //广告栏
        if (systemAds.size() <= 0) {
            if (homeBean.getData() != null && homeBean.getData().getSystemAds() != null) {
                systemAds.addAll(homeBean.getData().getSystemAds());
            }
            loadAds();
        }
        //直播列表
        if (homeBean.getData().getLives() != null && homeBean.getData().getLives().size() > 0) {
            livesbean.clear();
            livesbean.addAll(homeBean.getData().getLives());
            liveAdapter.notifyDataSetChanged();
        }
        //推荐名师
        if (homeBean.getData().getUsers() != null && homeBean.getData().getUsers().size() > 0) {
            usersbean.clear();
            usersbean.addAll(homeBean.getData().getUsers());
            usersAdapter.notifyDataSetChanged();
        }
        //直播课程
        if (homeBean.getData().getLiveCourses() != null && homeBean.getData().getLiveCourses().size() > 0) {
            liveCoursesBeen.clear();
            liveCoursesBeen.addAll(homeBean.getData().getLiveCourses());
            liveCoursesAdapter.notifyDataSetChanged();
        }
        //推荐作业
        if (homeBean.getData().getHomewoks() != null && homeBean.getData().getHomewoks().size() > 0) {
            homewoksBeen.clear();
            homewoksBeen.addAll(homeBean.getData().getHomewoks());
            homewoksAdapter.notifyDataSetChanged();
        }
    }
    private void loadAds(){
        if(systemAds==null) {
            return;
        }
//        advViewpager.setAdapter(MingShiFragment.this);
//        advViewpager.setData(systemAds,new ArrayList<String>());

    }
}
