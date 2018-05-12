package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.FavoriteBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface FavoriteService {

    @FormUrlEncoded
    @POST("/v1/m/user/favorite")
    Observable<FavoriteBean> favorite(@FieldMap Map<String,Integer> intMap, @FieldMap Map<String,String> params, @HeaderMap Map<String,String> heads);

    @FormUrlEncoded
    @POST("/v1/m/user/favorite/cancel")
    Observable<FavoriteBean> cancelFavorite(@FieldMap Map<String,Integer> intMap, @FieldMap Map<String,String> params, @HeaderMap Map<String,String> heads);
}
