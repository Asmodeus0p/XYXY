package com.jiyun.asmodeus.xyxy.model.http;

import com.jiyun.asmodeus.xyxy.model.entity.HomeBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class IHoneToken {
    public interface IHomeMaster {

        @FormUrlEncoded
        @POST("/v1/m/home/list")
        Observable<HomeBean> gethomemasterdata(@Field("loginUserId") Integer str);

    }

}
