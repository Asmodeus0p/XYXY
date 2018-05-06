package com.jiyun.asmodeus.xyxy.model.utils;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.model.biz.AppTokenService;
import com.jiyun.asmodeus.xyxy.model.biz.NoticeService;
import com.jiyun.asmodeus.xyxy.model.biz.PhoneCodeService;
import com.jiyun.asmodeus.xyxy.model.biz.RegistService;
import com.jiyun.asmodeus.xyxy.model.biz.TeacherService;
import com.jiyun.asmodeus.xyxy.model.entity.TokenBean;

import java.sql.Date;
import java.text.SimpleDateFormat;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 需要努力的人 on 2018/4/4.
 */

public class RetrofitUtils {

    private static RetrofitUtils retrofitUtils;

    private final Retrofit retrofit;
    private RetrofitUtils() {
       retrofit= new Retrofit.Builder()
                .baseUrl(Constant.Root_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit.create(AppTokenService.class)
                .loadToken("/v1/m/security/apptoken")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TokenBean>() {
                    @Override
                    public void accept(TokenBean value) throws Exception {
                        if(value==null||value.getData()==null){
                            return;
                        }

                        String apptoken = value.getData().getApptoken();

                        long time = System.currentTimeMillis();

                        String desApptoken= EncryptUtil.decrypt(apptoken);

                        String headerApptoken=EncryptUtil.encrypt(time + desApptoken).replaceAll("\\n","").toUpperCase();

                        SharedPreferencesUtils.setParam(App.context,"xyxy_apptoken",headerApptoken+"."+time);
                    }
                });
    }
    public static RetrofitUtils getInstance() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    public static String getDateToString(long time) {
        Date d = new Date(time);
        return new SimpleDateFormat("MM-dd HH:mm").format(d);
    }
    public TeacherService getTeacher(){
        return retrofit.create(TeacherService.class);
    }
    public NoticeService getNoticeService(){
        return retrofit.create(NoticeService.class);
    }
    public PhoneCodeService getPhoneServiec(){
        return retrofit.create(PhoneCodeService.class);
    }
    public RegistService getRegistServiec(){
        return retrofit.create(RegistService.class);
    }
}
