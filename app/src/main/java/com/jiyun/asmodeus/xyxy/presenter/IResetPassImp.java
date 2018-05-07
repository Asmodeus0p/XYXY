package com.jiyun.asmodeus.xyxy.presenter;

import com.jiyun.asmodeus.xyxy.contract.IResetPassContract;
import com.jiyun.asmodeus.xyxy.model.biz.IResetPassService;
import com.jiyun.asmodeus.xyxy.model.entity.FindPassBean;
import com.jiyun.asmodeus.xyxy.model.utils.RetrofitUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class IResetPassImp implements IResetPassContract.ResetPassPresenter{

    private IResetPassService model;
    private IResetPassContract.ResetPassView view;

    public IResetPassImp(IResetPassContract.ResetPassView view) {
        this.view = view;
        model= RetrofitUtils.getInstance().getIResetPassService();
    }

    @Override
    public void getResetPass(String mobile, String passwowrd) {
        Map<String,String> params=new HashMap<>();
        params.put("mobile",mobile);
        params.put("password",passwowrd);
        model.resetPass(params)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FindPassBean>() {
                    @Override
                    public void accept(FindPassBean findPassBean) throws Exception {
                        view.IresetPass(findPassBean);
                    }
                });

    }
}
