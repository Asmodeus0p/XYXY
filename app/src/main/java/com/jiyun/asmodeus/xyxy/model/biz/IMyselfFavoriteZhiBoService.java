package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.FavoriteListBean;
import com.jiyun.asmodeus.xyxy.model.entity.LivingListBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface IMyselfFavoriteZhiBoService {
    @FormUrlEncoded
    @POST("/v1/m/user/my/favorites")
    Observable<FavoriteListBean> getFavoriteZhiB(@FieldMap Map<String,Integer> intMap, @HeaderMap Map<String,String> headres);
}
