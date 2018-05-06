package com.jiyun.asmodeus.xyxy.presenter;

import com.jiyun.asmodeus.xyxy.contract.RegistContract;
import com.jiyun.asmodeus.xyxy.model.biz.RegistService;
import com.jiyun.asmodeus.xyxy.model.entity.RegistBean;
import com.jiyun.asmodeus.xyxy.model.utils.RetrofitUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class RegistPresneterImp implements RegistContract.registPresenter{

private RegistService model;
private RegistContract.registView view;

    public RegistPresneterImp(RegistContract.registView view) {
        this.view = view;
        model=RetrofitUtils.getInstance().getRegistServiec();
    }

    @Override
    public void GotoRegist(String moble, String phoneCode) {
        Map<String,String> params=new HashMap<>();
        params.put("moble",moble);
        params.put("phonecode",phoneCode);
        model.GotoRegister(params)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegistBean>() {
                    @Override
                    public void accept(RegistBean registBean) throws Exception {
                        view.loadUserDatas(registBean);
                    }
                });


    }

    @Override
    public void loadPhoneCode(String moble) {
        model.getPhoneCode(moble)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        view.showPhoneDatas(string);
                    }
                });
    }
}
