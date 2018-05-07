package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.LoginRegsterSucessBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IPhoneLoginService {

    @FormUrlEncoded
    @POST("/v1/m/user/login/mobile")
    Observable<ResponseBody> getPhoneLogin(@FieldMap Map<String,String> params);
}
