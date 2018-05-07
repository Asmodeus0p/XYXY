package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.FindPassBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FindPassService {

    @FormUrlEncoded
    @POST("/v1/m/user/verify/authcode")
    Observable<FindPassBean> authcode(@FieldMap Map<String,String> params);
    //("mobile")String mobile,
    //                                      @Field("authCode")String authCode
}
