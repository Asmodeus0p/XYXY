package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.GetUpLoadModel;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface IGetUpLoadToken {

    @POST
    Observable<GetUpLoadModel> getUpLoadToken(@Url String url);
}
