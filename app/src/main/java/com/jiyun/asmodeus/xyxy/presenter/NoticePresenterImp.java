package com.jiyun.asmodeus.xyxy.presenter;

import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.contract.NoticeContract;
import com.jiyun.asmodeus.xyxy.model.biz.NoticeService;
import com.jiyun.asmodeus.xyxy.model.entity.NoticeBean;
import com.jiyun.asmodeus.xyxy.model.utils.RetrofitUtils;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NoticePresenterImp implements NoticeContract.NoticePresenter {
    private NoticeService model;
    private NoticeContract.NoticaView view;

    public NoticePresenterImp(NoticeContract.NoticaView view) {
        this.view = view;
        model= RetrofitUtils.getInstance().getNoticeService();
    }

    @Override
    public void laodNoticeDatas() {
        Map<String, Object> params = new HashMap<>();
        Map<String, String> headrs = new HashMap<>();

        String appToken = (String) SharedPreferencesUtils.getParam(App.context,"xyxy_apptoken","String");
        headrs.put("apptoken", appToken);
        model.laodNoticeDatas(headrs)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NoticeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NoticeBean value) {
                        view.laodNoticeDatas(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getMessage();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
