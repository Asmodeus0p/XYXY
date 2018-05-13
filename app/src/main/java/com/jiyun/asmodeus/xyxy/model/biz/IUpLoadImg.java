package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.UpLoadImgBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IUpLoadImg {

    @Multipart
    @POST("/v1/m/qiniu/qiniuUpload")
    Observable<UpLoadImgBean> uploadImg(@Part List<MultipartBody.Part> file);
}