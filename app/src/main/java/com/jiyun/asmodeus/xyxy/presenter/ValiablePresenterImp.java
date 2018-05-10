package com.jiyun.asmodeus.xyxy.presenter;

import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.contract.ValiableContract;
import com.jiyun.asmodeus.xyxy.contract.WorkContract;
import com.jiyun.asmodeus.xyxy.model.biz.HomeworkService;
import com.jiyun.asmodeus.xyxy.model.biz.ValuableService;
import com.jiyun.asmodeus.xyxy.model.entity.HomeValuanleBean;
import com.jiyun.asmodeus.xyxy.model.entity.WorkBean;
import com.jiyun.asmodeus.xyxy.model.utils.RetrofitUtils;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ValiablePresenterImp implements ValiableContract.ValiablePresenter {
    private ValuableService model;
    private ValiableContract.ValiableView view;
    String appToken = (String) SharedPreferencesUtils.getParam(App.context, "xyxy_apptoken", "String");

    public ValiablePresenterImp(ValiableContract.ValiableView  view) {
        this.view = view;
        model = RetrofitUtils.getInstance().getValuableService();
    }

    @Override
    public void GetWorkZhiNeng() {
        Map<String, String> params = new HashMap<>();
        Map<String, String> headrs = new HashMap<>();

        params.put("sortord", 0 + "");
        headrs.put("apptoken", appToken);
        model.loadWorkPage(params, headrs)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeValuanleBean>() {
                    @Override
                    public void accept(HomeValuanleBean workBean) throws Exception {

                        if (workBean.getCode() == 0) {
                            view.laodWorkZhiNeng(workBean);
                        }
                    }
                });
    }

    @Override
    public void GetWorkZanShu() {
        Map<String, String> params1 = new HashMap<>();
        Map<String, String> headrs1 = new HashMap<>();

        params1.put("sortord", 1+"");
        headrs1.put("apptoken", appToken);
        model.loadWorkPage(params1, headrs1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeValuanleBean>() {
                    @Override
                    public void accept(HomeValuanleBean workBean) throws Exception {
                        if (workBean.getCode() == 0) {
                            view.laodWorkZanShu(workBean);
                        }
                    }
                });
    }

    @Override
    public void GetWorkZuiXin() {
        Map<String, String> params2 = new HashMap<>();
        Map<String, String> headrs2 = new HashMap<>();

        params2.put("sortord", 2+"");
        headrs2.put("apptoken", appToken);
        model.loadWorkPage(params2, headrs2)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomeValuanleBean>() {
                    @Override
                    public void accept(HomeValuanleBean workBean) throws Exception {
                        if (workBean.getCode() == 0) {
                            view.laodWorkZuiXin(workBean);
                        }
                    }
                });
    }


}
