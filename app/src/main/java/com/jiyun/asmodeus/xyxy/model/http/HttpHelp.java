package com.jiyun.asmodeus.xyxy.model.http;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import com.jiyun.asmodeus.xyxy.R;
import com.jiyun.asmodeus.xyxy.model.biz.IAppToken;
import com.jiyun.asmodeus.xyxy.model.entity.AppTokenBean;
import com.jiyun.asmodeus.xyxy.model.utils.AddCookiesInterceptor;
import com.jiyun.asmodeus.xyxy.model.utils.Constant;
import com.jiyun.asmodeus.xyxy.model.utils.ReceivedCookiesInterceptor;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 配置 cookie
 *
 * 设置支持https请求
 *
 * Created by vicoltree on 17/10/26.
 */

public class HttpHelp {

    public static final int Sucess_code = 0;

    public static Retrofit baseHttpRequest(Context context,String url) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Constant.ConnectTimeout, TimeUnit.SECONDS)
                .readTimeout(Constant.ReadTimeout,TimeUnit.SECONDS)
                .writeTimeout(Constant.WriteTimeout,TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)//目前关闭重复请求
                .addInterceptor(new ReceivedCookiesInterceptor(context))
                .addInterceptor(new AddCookiesInterceptor(context))
                .build();


        if(TextUtils.isEmpty(HttpHelp.getAppToken(context))){
            HttpHelp.loadApptoken(context);
        }


        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    public static void loadApptoken(final Context context){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Constant.ConnectTimeout, TimeUnit.SECONDS)
                .readTimeout(Constant.ReadTimeout,TimeUnit.SECONDS)
                .writeTimeout(Constant.WriteTimeout,TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
                .addInterceptor(new ReceivedCookiesInterceptor(context))
                .addInterceptor(new AddCookiesInterceptor(context))
                .build();

        Retrofit retrofit= new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constant.Root_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        retrofit.create(IAppToken.class)
                .getAppToken("/v1/m/security/apptoken")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AppTokenBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AppTokenBean value) {

                        if(value==null||value.getData()==null){
                            return;
                        }

                        String apptoken = value.getData().getApptoken();

                        long time = System.currentTimeMillis();

                        try {
                            String desApptoken= EncryptUtil.decrypt(apptoken);

                            String headerApptoken=EncryptUtil.encrypt(time + desApptoken).replaceAll("\\n","").toUpperCase();

                            HttpHelp.saveAppToken(context,headerApptoken,time);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }



    public static RequestBody createBody(String json){
        return RequestBody.create(okhttp3.MediaType.parse(Constant.Parse_json_key),json);
    }

    public static String  toJson(Object object){

        Gson gson = new Gson();

        return gson.toJson(object);
    }









    public static String getDateToString(long time) {
        Date d = new Date(time);
        return new SimpleDateFormat("MM-dd HH:mm").format(d);
    }

    public static boolean isLogin(Context context){

        if(context==null){
            return false ;
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constant.CookieSP, Context.MODE_PRIVATE);

        //TODO测试id
        return !TextUtils.isEmpty(sharedPreferences.getString(Constant.UserId, ""));
    }


    public static Integer getUserId(Context context){

        if(context==null){
            return 0 ;
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constant.CookieSP, Context.MODE_PRIVATE);

        String id = sharedPreferences.getString(Constant.UserId, "");
        if(TextUtils.isEmpty(id)){
            return 0;
        }

        Integer idInteger= Integer.parseInt(id);

        //TODU测试id
        return idInteger;

    }

    public static String getAppToken(Context context){

        if(context==null){
            return "" ;
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constant.CookieSP, Context.MODE_PRIVATE);

        String apptoken = sharedPreferences.getString(Constant.AppToken, "");
        if(TextUtils.isEmpty(apptoken)){
            return "";
        }

        //TODU测试id
        return apptoken;

    }


    public static void saveId(Context context,String id){
        if(context==null){
            return ;
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constant.CookieSP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.UserId, id);
        editor.commit();

    }

    public static void saveToken(Context context,String token){
        if(context==null){
            return ;
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constant.CookieSP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.CookieName,token);
        editor.commit();

    }

    public static void saveAppToken(Context context,String token ,long time){
        if(context==null){
            return ;
        }

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constant.CookieSP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constant.AppToken,token+"."+time);
        editor.commit();

    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
             ConnectivityManager mConnectivityManager = (ConnectivityManager) context
             .getSystemService(Context.CONNECTIVITY_SERVICE);
             NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
             if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
             }
             }
         return false;
        }

}
