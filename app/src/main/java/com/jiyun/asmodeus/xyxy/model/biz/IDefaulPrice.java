package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.DefaultPriceBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface IDefaulPrice {

    @POST
    Observable<DefaultPriceBean> getDefaultPrice(@Url String url);
}
