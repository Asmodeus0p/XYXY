package com.jiyun.asmodeus.xyxy.presenter;

import com.jiyun.asmodeus.xyxy.contract.PhoneCodeContract;
import com.jiyun.asmodeus.xyxy.model.biz.PhoneCodeService;
import com.jiyun.asmodeus.xyxy.model.entity.RegistBean;
import com.jiyun.asmodeus.xyxy.model.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PhoneCodePresenterImp implements PhoneCodeContract.phonePresenter {
    private PhoneCodeService model;
    private PhoneCodeContract.phoneView view;

    public PhoneCodePresenterImp(PhoneCodeContract.phoneView view) {
        this.view = view;
        model= RetrofitUtils.getInstance().getPhoneServiec();
    }

    @Override
    public void loadPhoneCode(String moble) {
        model.getPhoneCode(moble)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegistBean>() {
                    @Override
                    public void accept(RegistBean registBean) throws Exception {
                        view.loadPhoneDatas(registBean);
                    }
                });

    }

    @Override
    public void findPassPhoneCode(String moble) {
        model.findPassPhoneCode(moble)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegistBean>() {
                    @Override
                    public void accept(RegistBean registBean) throws Exception {
                        view.loadPhoneDatas(registBean);
                    }
                });
    }
}
