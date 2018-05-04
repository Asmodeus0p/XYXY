package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.NoticeBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;


public interface NoticeService {

    @POST("/v1/m/forthcoming/home")
    Observable<NoticeBean> laodNoticeDatas(@HeaderMap Map<String, String> headrs);
}
