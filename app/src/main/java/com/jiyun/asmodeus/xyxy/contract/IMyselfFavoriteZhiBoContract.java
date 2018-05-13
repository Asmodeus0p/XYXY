package com.jiyun.asmodeus.xyxy.contract;

import com.jiyun.asmodeus.xyxy.model.entity.FavoriteListBean;

public interface IMyselfFavoriteZhiBoContract {

    interface loadFavoriteListPresenter {
        void loadDatas(Integer loginsId,Integer type);
    }

    interface loadFavoriteListView {
        void laodListDatas(FavoriteListBean favoriteListBean);
    }
}
