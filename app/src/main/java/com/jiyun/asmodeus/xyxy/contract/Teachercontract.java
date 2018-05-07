package com.jiyun.asmodeus.xyxy.contract;


import com.jiyun.asmodeus.xyxy.model.entity.HomeBean;

public interface Teachercontract {

    interface TeacherPresenter{
        void laodTeacherDatas(Integer loginUserId);
    }
    interface TeacherView{
        void laodTeacherDatas(HomeBean homeData);
    }
}
