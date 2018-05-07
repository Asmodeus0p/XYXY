package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.RegistBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PhoneCodeService {

    @FormUrlEncoded
    @POST("/v1/m/user/authcode")
    Observable<RegistBean> getPhoneCode(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("/v1/m/user/authcode")
    Observable<RegistBean> findPassPhoneCode(@Field("mobile") String mobile);


}
