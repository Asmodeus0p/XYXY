package com.jiyun.asmodeus.xyxy.presenter;

import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.contract.AiliPayContract;
import com.jiyun.asmodeus.xyxy.model.biz.AliPayService;
import com.jiyun.asmodeus.xyxy.model.entity.NoticeDetailOrderModel;
import com.jiyun.asmodeus.xyxy.model.entity.OrderNoBean;
import com.jiyun.asmodeus.xyxy.model.utils.RetrofitUtils;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AiliPayPrensenterImp implements AiliPayContract.getOrderNoPresenter {

    private AliPayService model;
    private AiliPayContract.getOrderNoView view;

    public AiliPayPrensenterImp(AiliPayContract.getOrderNoView view) {
        this.view = view;
        model= RetrofitUtils.getInstance().getAiliPayOrderNo();
    }

    @Override
    public void sendOrderNo(int logindId,double price,int amount) {
        Map <String,String> headres=new HashMap<>();
        String appToken = (String) SharedPreferencesUtils.getParam(App.context,"xyxy_apptoken","String");
        headres.put("appToken",appToken);
        model.getOrder(logindId,price,amount,headres)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NoticeDetailOrderModel>() {
                    @Override
                    public void accept(NoticeDetailOrderModel noticeDetailOrderModel) throws Exception {
                        view.getOrderNoBean(noticeDetailOrderModel);
                    }
                });
    }

    @Override
    public void getOrderNo(String orderNo) {
        Map <String,String> headres=new HashMap<>();
        Map <String,String> params=new HashMap<>();

        String appToken = (String) SharedPreferencesUtils.getParam(App.context,"xyxy_apptoken","String");
        headres.put("appToken",appToken);
        params.put("orderNo",orderNo);
        model.getOrderNo(headres,params)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<OrderNoBean>() {
                    @Override
                    public void accept(OrderNoBean orderNoBean) throws Exception {
                        view.getOrderNoB(orderNoBean);
                    }
                });

    }
}
