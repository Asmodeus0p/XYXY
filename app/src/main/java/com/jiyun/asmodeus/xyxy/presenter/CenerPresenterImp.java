package com.jiyun.asmodeus.xyxy.presenter;

import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.contract.CenterContract;
import com.jiyun.asmodeus.xyxy.model.biz.CenterService;
import com.jiyun.asmodeus.xyxy.model.entity.CenterBean;
import com.jiyun.asmodeus.xyxy.model.entity.FindPassBean;
import com.jiyun.asmodeus.xyxy.model.utils.RetrofitUtils;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CenerPresenterImp implements CenterContract.CenterPresenter {
    private CenterService model;
    private CenterContract.VenterView view;
    String appToken = (String) SharedPreferencesUtils.getParam(App.context, "xyxy_apptoken", "String");
    public CenerPresenterImp(CenterContract.VenterView view) {
        this.view = view;
        model= RetrofitUtils.getInstance().getcenterService();
    }

    @Override
    public void getCenter() {
        Map<String,String> headers=new HashMap<>();
        headers.put("appToken",appToken);
        model.getRechargeCenter(headers)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CenterBean>() {
                    @Override
                    public void accept(CenterBean centerBean) throws Exception {
                        if (centerBean==null||centerBean.getData()==null){
                            return;
                        }
                        view.getCenterPassBean(centerBean);
                    }
                });
    }
}
