package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.FindPassBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IResetPassService {

    @FormUrlEncoded
    @POST("/v1/m/user/save/password")
    Observable<FindPassBean> resetPass(@FieldMap Map<String,String> params);
}
