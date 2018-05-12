package com.jiyun.asmodeus.xyxy.model.biz;




import com.jiyun.asmodeus.xyxy.model.entity.AppTokenBean;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface IAppToken {

    @POST
    Observable<AppTokenBean> getAppToken(@Url String url);
}
