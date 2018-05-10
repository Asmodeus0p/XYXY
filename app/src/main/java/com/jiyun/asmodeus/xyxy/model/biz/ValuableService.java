package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.HomeValuanleBean;
import com.jiyun.asmodeus.xyxy.model.utils.Urls;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface ValuableService {
    @FormUrlEncoded
    @POST(Urls.VALUABLEURL)
    Observable<HomeValuanleBean> loadWorkPage(@FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);
}

