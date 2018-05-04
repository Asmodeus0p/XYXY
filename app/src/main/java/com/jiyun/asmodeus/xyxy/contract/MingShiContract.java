package com.jiyun.asmodeus.xyxy.contract;

import com.jiyun.asmodeus.xyxy.model.entity.HomeBean;

import java.util.List;

public class MingShiContract {
    interface View {
        void showRollPager(List<HomeBean.DataBean.SystemAdsBean> systemAds);

        void showTeacherRecycler(List<HomeBean.DataBean.UsersBean> users);

        void showClassRecycler(List<HomeBean.DataBean.LiveCoursesBean> liveCourses);

        void showWorkRecycler(List<HomeBean.DataBean.HomewoksBean> homewoks);

        void showError(String msg);
    }

    interface Presenter {
        void loadHomePageDate(Integer userId);
    }
}
