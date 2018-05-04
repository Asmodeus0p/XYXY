package com.jiyun.asmodeus.xyxy.view.fragment;


import android.support.v4.app.Fragment;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.contract.Teachercontract;
import com.jiyun.asmodeus.xyxy.model.entity.HomeBean;
import com.jiyun.asmodeus.xyxy.presenter.TeacherPresenterImp;
import com.jiyun.asmodeus.xyxy.view.base.BaseFragment;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MingShiFragment extends BaseFragment implements Teachercontract.TeacherView {
    private List<HomeBean.DataBean.SystemAdsBean> systemAds;
    private List<String> imgs;
    private FlyBanner flyBanner;
    private TeacherPresenterImp presenterImp;
    public MingShiFragment() {
        // Required empty public constructor
    }




    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ming_shi;
    }

    @Override
    protected void init() {
        presenterImp=new TeacherPresenterImp(this);
        presenterImp.laodTeacherDatas(0);
        systemAds=new ArrayList<>();
        imgs=new ArrayList<>();


    }

    @Override
    protected void loadDatas() {

    }

    @Override
    public void laodTeacherDatas(HomeBean homeBean) {
        HomeBean.DataBean data = homeBean.getData();

        List<HomeBean.DataBean.UsersBean> users = data.getUsers();

        List<HomeBean.DataBean.HomewoksBean> homewoks = homeBean.getData().getHomewoks();

        List<HomeBean.DataBean.LiveCoursesBean> liveCourses = homeBean.getData().getLiveCourses();

        List<HomeBean.DataBean.LivesBean> lives = homeBean.getData().getLives();

        List<HomeBean.DataBean.SystemAdsBean> systemAds = homeBean.getData().getSystemAds();
        for (HomeBean.DataBean.SystemAdsBean adsBean : systemAds) {
            imgs.add(adsBean.getMobileImgUrl());
        }
        flyBanner=getView().findViewById(R.id.banner_1);
        flyBanner.setImagesUrl(imgs);

    }
}
