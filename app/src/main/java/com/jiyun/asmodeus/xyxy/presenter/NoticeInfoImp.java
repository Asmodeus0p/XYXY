package com.jiyun.asmodeus.xyxy.presenter;

import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.contract.NoticeInfoContract;
import com.jiyun.asmodeus.xyxy.model.biz.NoticeInfoService;
import com.jiyun.asmodeus.xyxy.model.entity.NoticeInfoBean;
import com.jiyun.asmodeus.xyxy.model.entity.RegistBean;
import com.jiyun.asmodeus.xyxy.model.utils.RetrofitUtils;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NoticeInfoImp implements NoticeInfoContract.NoticeInfoPresenter{

    private NoticeInfoService model;
    private NoticeInfoContract.NoticaInfoView view;

    public NoticeInfoImp(NoticeInfoContract.NoticaInfoView view) {
        this.view = view;
        model= RetrofitUtils.getInstance().getNoticeInfoService();
    }

    @Override
    public void laodNoticeDatas(int courseId) {
         Map<String,Integer> params=new HashMap<>();
         Map<String,String> hears=new HashMap<>();

        String appToken = (String) SharedPreferencesUtils.getParam(App.context,"xyxy_apptoken","String");
        hears.put("appToken",appToken);
        params.put("courseId",courseId);
        model.getNoticeDetail(params,hears)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NoticeInfoBean>() {
                    @Override
                    public void accept(NoticeInfoBean noticeInfoBean) throws Exception {
                        if(noticeInfoBean==null){

                            return ;
                        }
                        view.laodNoticeDatas(noticeInfoBean);
                    }
                });

    }

}
