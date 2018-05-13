package com.jiyun.asmodeus.xyxy.presenter;

import com.jiyun.asmodeus.xyxy.App;
import com.jiyun.asmodeus.xyxy.contract.FavoriteContract;
import com.jiyun.asmodeus.xyxy.contract.IMyselfFavoriteZhiBoContract;
import com.jiyun.asmodeus.xyxy.model.biz.IMyselfFavoriteZhiBoService;
import com.jiyun.asmodeus.xyxy.model.entity.FavoriteBean;
import com.jiyun.asmodeus.xyxy.model.entity.FavoriteListBean;
import com.jiyun.asmodeus.xyxy.model.utils.RetrofitUtils;
import com.jiyun.asmodeus.xyxy.model.utils.SharedPreferencesUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FavoriteListImp implements IMyselfFavoriteZhiBoContract.loadFavoriteListPresenter{


    private IMyselfFavoriteZhiBoService model;

    public FavoriteListImp(IMyselfFavoriteZhiBoContract.loadFavoriteListView view) {
        this.view = view;
        model= RetrofitUtils.getInstance().getIMyselfFavoriteZhiBoService();
    }

    private IMyselfFavoriteZhiBoContract.loadFavoriteListView view;
    @Override
    public void loadDatas(Integer loginsId, Integer type) {
        Map<String,String> headres=new HashMap<>();
        Map<String,Integer> intMap=new HashMap<>();

        String appToken = (String) SharedPreferencesUtils.getParam(App.context,"xyxy_apptoken","String");
        headres.put("appToken",appToken);
        intMap.put("loginUserId",loginsId);
        intMap.put("type",type);
        model.getFavoriteZhiB(intMap,headres)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FavoriteListBean>() {
                    @Override
                    public void accept(FavoriteListBean favoriteListBean) throws Exception {
                        view.laodListDatas(favoriteListBean);
                    }
                });
    }
}
