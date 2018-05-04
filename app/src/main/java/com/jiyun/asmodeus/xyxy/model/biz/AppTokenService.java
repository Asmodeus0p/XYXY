package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.TokenBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by asus on 2018/5/3.
 */

public interface AppTokenService {
    @POST
    Observable<TokenBean> loadToken(@Url String url);
}
