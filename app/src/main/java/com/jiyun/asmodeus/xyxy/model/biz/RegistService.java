package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.RegistBean;
import com.jiyun.asmodeus.xyxy.model.utils.Urls;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RegistService {

    @FormUrlEncoded
    @POST(Urls.REGIST)
    Observable<RegistBean> GotoRegister(@FieldMap Map<String,String> params);

    @FormUrlEncoded
    @POST("/v1/m/user/authcode")
    Observable<ResponseBody> getPhoneCode(@Field("mobile") String mobile);

}
