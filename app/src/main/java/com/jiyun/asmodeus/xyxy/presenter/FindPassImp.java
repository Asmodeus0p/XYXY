package com.jiyun.asmodeus.xyxy.presenter;

import com.jiyun.asmodeus.xyxy.contract.FindPassContract;
import com.jiyun.asmodeus.xyxy.model.biz.FindPassService;
import com.jiyun.asmodeus.xyxy.model.entity.FindPassBean;
import com.jiyun.asmodeus.xyxy.model.entity.RegistBean;
import com.jiyun.asmodeus.xyxy.model.utils.RetrofitUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FindPassImp implements FindPassContract.FindPassPresenter {
    private FindPassService model;
    private FindPassContract.FindPassView view;

    public FindPassImp(FindPassContract.FindPassView view) {
        this.view = view;
        model= RetrofitUtils.getInstance().getFindPassService();
    }

    @Override
    public void getFindPass(String userId, String pass) {
        Map<String,String> params=new HashMap<>();
        params.put("mobile",userId);
        params.put("authCode",pass);
        model.authcode(params)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FindPassBean>() {
                    @Override
                    public void accept(FindPassBean findPassBean) throws Exception {
                        view.getFindPassBean(findPassBean);
                    }
                });
    }
}
