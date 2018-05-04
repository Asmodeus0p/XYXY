package com.jiyun.asmodeus.xyxy.model.biz;

/**
 * Created by asus on 2018/5/3.
 */
import com.jiyun.asmodeus.xyxy.model.entity.HomeBean;
import com.jiyun.asmodeus.xyxy.model.utils.Urls;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
/**
 * 名师页面的所有业务方法
 */
public interface TeacherService {
    @FormUrlEncoded
    @POST(Urls.MINGSHIURL)
    Observable<HomeBean> loadHomePage(@FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);
}
