package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.FavoriteBean;
import com.jiyun.asmodeus.xyxy.model.entity.NoticeInfoBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface NoticeInfoService {

    @FormUrlEncoded
    @POST("/v1/m/forthcoming/detail")
    Observable<NoticeInfoBean> getNoticeDetail(@FieldMap Map<String,Integer> params, @HeaderMap Map<String,String> heads);


}
