package com.jiyun.asmodeus.xyxy.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.jiyun.asmodeus.xyxy.contract.LoginSucessContract;
import com.jiyun.asmodeus.xyxy.model.biz.IPhoneLoginService;
import com.jiyun.asmodeus.xyxy.model.entity.LoginRegsterSucessBean;
import com.jiyun.asmodeus.xyxy.model.utils.RetrofitUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class LoginPresenterImp implements LoginSucessContract.LoginPresenter {
    private IPhoneLoginService model;
    private LoginSucessContract.LoginView view;

    public LoginPresenterImp(LoginSucessContract.LoginView view) {
        this.view = view;
        model = RetrofitUtils.getInstance().getIPhoneLoginService();
    }

    @Override
    public void laodLoginDatas(String userId, String pass) {

        Map<String, String> params = new HashMap<>();
        params.put("mobile", userId);
        params.put("password", pass);
        model.getPhoneLogin(params)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        Log.e("===", "responseBody:" + string);
                        LoginRegsterSucessBean loginRegsterSucessBean = new Gson().fromJson(string, LoginRegsterSucessBean.class);
                        view.laodLoginDatas(loginRegsterSucessBean);
                    }
                });
    }
}
