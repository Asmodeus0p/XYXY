package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.CenterBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;


public interface CenterService {

    @POST("v1/m/record/bean/setting")
    Observable<CenterBean> getRechargeCenter(@HeaderMap Map<String, String> headers);
}
