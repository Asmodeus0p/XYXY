package com.jiyun.asmodeus.xyxy.presenter;

import android.util.Log;

import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.contract.Teachercontract;

import com.jiyun.asmodeus.xyxy.model.biz.TeacherService;
import com.jiyun.asmodeus.xyxy.model.entity.HomeBean;

import com.jiyun.asmodeus.xyxy.model.utils.RetrofitUtils;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;


import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TeacherPresenterImp implements Teachercontract.TeacherPresenter{
    private TeacherService model;
    private Teachercontract.TeacherView view;
    public TeacherPresenterImp(Teachercontract.TeacherView view) {
        this.view=view;
        model= RetrofitUtils.getInstance().getTeacher();
    }

    @Override
    public void laodTeacherDatas(Integer UserId) {
        Map<String,String> params=new HashMap<>();
        Map<String,String> headrs=new HashMap<>();
        String appToken = (String) SharedPreferencesUtils.getParam(App.context,"xyxy_apptoken","String");
        params.put("loginUserId",UserId+"");
        headrs.put("apptoken", appToken);
        model.loadHomePage(params,headrs)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeBean>() {
                    @Override
                    public void accept(HomeBean homeBean) throws Exception {
                        if (homeBean.getCode()==0){
                            Log.e("qSADFG", homeBean.getMessage());
                            view.laodTeacherDatas(homeBean);
                        }
                    }
                });

    }
}
