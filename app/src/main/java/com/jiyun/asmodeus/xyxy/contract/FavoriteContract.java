package com.jiyun.asmodeus.xyxy.contract;

import com.jiyun.asmodeus.xyxy.model.entity.FavoriteBean;

public interface FavoriteContract {

    interface NoticeInfoFavoritePresenter{
        void sendMessgae(String type,int id,Integer loginUserId);
        void sendcancelMessgae(String type,int id,Integer loginUserId);
    }
    interface NoticeInfoFavoriteView{
        void backMessage(FavoriteBean favoriteBean);
        void cancalBackMessage(FavoriteBean favoriteBean);
    }
}
