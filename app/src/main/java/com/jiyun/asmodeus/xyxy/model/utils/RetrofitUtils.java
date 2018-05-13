package com.jiyun.asmodeus.xyxy.model.utils;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.model.biz.AppTokenService;
import com.jiyun.asmodeus.xyxy.model.biz.CancleFavoriteService;
import com.jiyun.asmodeus.xyxy.model.biz.CenterService;
import com.jiyun.asmodeus.xyxy.model.biz.FavoriteService;
import com.jiyun.asmodeus.xyxy.model.biz.FindPassService;
import com.jiyun.asmodeus.xyxy.model.biz.HomeworkService;
import com.jiyun.asmodeus.xyxy.model.biz.IMyselfFavoriteZhiBoService;
import com.jiyun.asmodeus.xyxy.model.biz.IPhoneLoginService;
import com.jiyun.asmodeus.xyxy.model.biz.IResetPassService;
import com.jiyun.asmodeus.xyxy.model.biz.MessageListService;
import com.jiyun.asmodeus.xyxy.model.biz.NoticeInfoService;
import com.jiyun.asmodeus.xyxy.model.biz.NoticeService;
import com.jiyun.asmodeus.xyxy.model.biz.PhoneCodeService;
import com.jiyun.asmodeus.xyxy.model.biz.RegistService;
import com.jiyun.asmodeus.xyxy.model.biz.TeacherService;
import com.jiyun.asmodeus.xyxy.model.biz.ValuableService;
import com.jiyun.asmodeus.xyxy.model.entity.TokenBean;
import com.jiyun.asmodeus.xyxy.view.MainActivity;

import java.sql.Date;
import java.text.SimpleDateFormat;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitUtils {

    private static RetrofitUtils retrofitUtils;

    private final Retrofit retrofit;


    public static final int Sucess_code = 0;
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
    public HomeworkService getWorkService(){
        return retrofit.create(HomeworkService.class);
    }
    public IPhoneLoginService getIPhoneLoginService(){
        return retrofit.create(IPhoneLoginService.class);
    }
    public FindPassService getFindPassService(){
        return retrofit.create(FindPassService.class);
    }
    public IResetPassService getIResetPassService(){
        return retrofit.create(IResetPassService.class);
    }
    public ValuableService getValuableService(){
        return retrofit.create(ValuableService.class);
    }
    public CenterService getcenterService(){
        return retrofit.create(CenterService.class);
    }
    public MessageListService getMessageListService(){
        return retrofit.create(MessageListService.class);
    }
    public NoticeInfoService getNoticeInfoService(){
        return retrofit.create(NoticeInfoService.class);
    }
    public FavoriteService getFavoriteService(){
        return retrofit.create(FavoriteService.class);
    }
    public CancleFavoriteService getcancleFavoriteService(){
        return retrofit.create(CancleFavoriteService.class);
    }

    public IMyselfFavoriteZhiBoService getIMyselfFavoriteZhiBoService(){
            return retrofit.create(IMyselfFavoriteZhiBoService.class);
    }
}

