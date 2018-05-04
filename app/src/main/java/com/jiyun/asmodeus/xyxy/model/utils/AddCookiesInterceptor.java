package com.jiyun.asmodeus.xyxy.model.utils;

import android.content.Context;
import android.content.SharedPreferences;



import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by vicoltree on 17/10/26.
 */

public class AddCookiesInterceptor implements Interceptor {


    private Context context;

    public AddCookiesInterceptor(Context context) {
        super();
        this.context = context;

    }

    @Override
    public Response intercept(Chain chain) throws IOException {


        final Request.Builder builder = chain.request().newBuilder();
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Constant.CookieSP, Context.MODE_PRIVATE);
        builder.addHeader("apptoken", sharedPreferences.getString(Constant.AppToken, ""));
        builder.addHeader("user-agent", sharedPreferences.getString(Constant.User_Agent, ""));
        builder.addHeader("Authorization",sharedPreferences.getString(Constant.CookieName,""));
        builder.addHeader("did",sharedPreferences.getString(Constant.SoleName,""));
        builder.addHeader("cid", sharedPreferences.getString(Constant.CidName, ""));
        return chain.proceed(builder.build());
    }
}
