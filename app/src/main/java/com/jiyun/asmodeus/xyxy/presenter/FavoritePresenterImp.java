package com.jiyun.asmodeus.xyxy.presenter;

import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.contract.FavoriteContract;
import com.jiyun.asmodeus.xyxy.model.biz.FavoriteService;
import com.jiyun.asmodeus.xyxy.model.entity.FavoriteBean;
import com.jiyun.asmodeus.xyxy.model.utils.RetrofitUtils;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FavoritePresenterImp implements FavoriteContract.NoticeInfoFavoritePresenter {

    private FavoriteService model;
    private FavoriteContract.NoticeInfoFavoriteView view;

    public FavoritePresenterImp(FavoriteContract.NoticeInfoFavoriteView view) {
        this.view = view;
        model= RetrofitUtils.getInstance().getFavoriteService();
    }

    @Override
    public void sendMessgae(String type, int id, Integer loginUserId) {
        Map<String,String> params=new HashMap<>();
        Map<String,Integer> intMap=new HashMap<>();
        Map<String,String> heads=new HashMap<>();
        params.put("type",type);
        intMap.put("id",id);
        intMap.put("loginUserId",loginUserId);
        String appToken = (String) SharedPreferencesUtils.getParam(App.context,"xyxy_apptoken","String");
        heads.put("appToken",appToken);
        model.favorite(intMap,params,heads)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FavoriteBean>() {
                    @Override
                    public void accept(FavoriteBean favoriteBean) throws Exception {
                        view.backMessage(favoriteBean);
                    }
                });
    }

    @Override
    public void sendcancelMessgae(String type, int id, Integer loginUserId) {
        Map<String,String> params=new HashMap<>();
        Map<String,Integer> intMap=new HashMap<>();
        Map<String,String> heads=new HashMap<>();
        params.put("type",type);
        intMap.put("id",id);
        intMap.put("loginUserId",loginUserId);
        String appToken = (String) SharedPreferencesUtils.getParam(App.context,"xyxy_apptoken","String");
        heads.put("appToken",appToken);
        model.cancelFavorite(intMap,params,heads)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FavoriteBean>() {
                    @Override
                    public void accept(FavoriteBean favoriteBean) throws Exception {
                        view.cancalBackMessage(favoriteBean);
                    }
                });
    }

}
