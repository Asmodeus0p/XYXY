package com.jiyun.asmodeus.xyxy.presenter;

import android.util.Log;

import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.contract.Teachercontract;
import com.jiyun.asmodeus.xyxy.contract.WorkContract;
import com.jiyun.asmodeus.xyxy.model.biz.HomeworkService;
import com.jiyun.asmodeus.xyxy.model.biz.TeacherService;
import com.jiyun.asmodeus.xyxy.model.entity.HomeBean;
import com.jiyun.asmodeus.xyxy.model.entity.WorkBean;
import com.jiyun.asmodeus.xyxy.model.utils.RetrofitUtils;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class WorkPresenterImp implements WorkContract.WorkPresenter {
    private HomeworkService model;
    private WorkContract.WorkView view;

    public WorkPresenterImp(WorkContract.WorkView view) {
        this.view = view;
        model = RetrofitUtils.getInstance().getWorkService();
    }

    @Override
    public void laodWorkDatas() {
        Map<String, String> params = new HashMap<>();
        Map<String, String> headrs = new HashMap<>();
        String appToken = (String) SharedPreferencesUtils.getParam(App.context, "xyxy_apptoken", "String");
        params.put("sortord", "0");
        headrs.put("apptoken", appToken);
        model.loadWorkPage(params, headrs)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WorkBean>() {
                    @Override
                    public void accept(WorkBean workBean) throws Exception {
                        if (workBean.getCode() == 0) {
                            Log.e("11111",workBean.getMessage());
                            view.laodWorkZhiNeng(workBean);
                        }
                    }
                });

        Map<String, String> params1 = new HashMap<>();
        Map<String, String> headrs1 = new HashMap<>();

        params.put("sortord", "1");
        headrs.put("apptoken", appToken);
        model.loadWorkPage(params1, headrs1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WorkBean>() {
                    @Override
                    public void accept(WorkBean workBean) throws Exception {
                        if (workBean.getCode() == 0) {
                            Log.e("2222",workBean.getMessage());
                            view.laodWorkTouTing(workBean);
                        }
                    }
                });
        Map<String, String> params2 = new HashMap<>();
        Map<String, String> headrs2 = new HashMap<>();

        params.put("sortord", "2");
        headrs.put("apptoken", appToken);
        model.loadWorkPage(params2, headrs2)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WorkBean>() {
                    @Override
                    public void accept(WorkBean workBean) throws Exception {
                        if (workBean.getCode() == 0) {
                            Log.e("3333",workBean.getMessage());
                            view.laodWorkDianPing(workBean);
                        }
                    }
                });
    }
}
